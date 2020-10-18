package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.tangram.model.TangramTemplateConfig;
import com.tcl.commondb.tangram.model.TangramTemplateModuleConfig;
import com.tcl.commondb.tangram.repository.TangramTemplateConfigRepository;
import com.tcl.commondb.tangram.repository.TangramTemplateModuleConfigRepository;
import com.tcl.uf.tangram.enums.ModuleConstant;
import com.tcl.uf.tangram.enums.PageTypeConstant;
import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.service.HomeService;
import com.tcl.uf.tangram.service.TangramMainService;
import com.tcl.uf.tangram.service.ThirdShopContentService;
import com.tcl.uf.tangram.util.TangramTemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhongfk on 2020/8/14.
 * @version 1.0
 */
@Service
public class HomeServiceImpl implements HomeService {

    static final Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Autowired
    private TangramMainService tangramMainService;

    @Autowired
    private TangramTemplateConfigRepository tangramTemplateConfigRepository;

    @Autowired
    private TangramTemplateModuleConfigRepository tangramTemplateModuleConfigRepository;
    /**
     * 商城管理后台接口feign调用
     */
    @Autowired
    private ThirdShopContentService thirdShopContentService;

    @Autowired
    private CommonService commonService;

    @Override
    public String queryHomeData(String reqCode, String uiType) {
        //获取第三方数据
        JSONArray responseData = new JSONArray();
        String responseContent = commonService.getPageManageByPageType(PageTypeConstant.INDEX, uiType);
        JSONObject contentJson = JSON.parseObject(responseContent);
        responseData = contentJson.getJSONArray("items");
//        try{
//            //调商城首页接口+分组商品数据接口 获取数据
//            String result = thirdShopContentService.getIndexPageInfo(uiType);
//            JSONObject resObj = JSON.parseObject(result);
//            String content = resObj.getJSONObject("data").getString("content");
//            JSONObject contentJson = JSON.parseObject(content);
//            responseData =contentJson.getJSONArray("items").toJSONString();
//        }catch (Exception e){
//            //捕获调用异常，使用数据库默认组件数据替代
//            logger.error("");
//        }
        Map<String, JSONArray> apiData = preApiContentData(responseData);
        //根据商品分类接口获取默认的第一个分类的uuid，去请求商品列表数据
        String goodsgroupUuid = apiData.get(ModuleConstant.GROUPTYPE).getJSONObject(0).getString("goodsgroupUuid");
        JSONArray productItems = getProductGroupApiData(goodsgroupUuid, "1", "10");
        apiData.put(ModuleConstant.PRODUCTGRID, productItems);
        //替换商品列表组件里的分页加载接口url
        String result = tangramMainService.execute(reqCode, apiData);
        System.out.println("....result=" + result);
        JSONObject resJson = JSONObject.parseObject(result);
        int size = resJson.getJSONArray("cards").size();
        resJson.getJSONArray("cards").getJSONObject(size - 1).put("load", "/tangram?version=1.0&reqCode=1016&goodsgroupUuid=" + goodsgroupUuid);
        return resJson.toString();

    }

    /**
     * 转换商品组件的items数据
     *
     * @param goodsgroupUuid
     * @param pageNo
     * @param pageSize
     * @return 返回的是商品模版中items json数据，分页功能只需要items里的数据
     */
    @Override
    public String queryProductModulesContents(String goodsgroupUuid, String pageNo, String pageSize) {
        //请求第三方商城后台接口
        JSONArray apiData = getProductGroupApiData(goodsgroupUuid, pageNo, pageSize);
        //查找product对应的组件数据
        List<TangramTemplateModuleConfig> moduleConfigs = tangramTemplateModuleConfigRepository
                .findByModuleNameIn(Arrays.asList(ModuleConstant.PRODUCTGRID));
        if (apiData != null && apiData.size() > 0) {
            //对返回数据使用组件转换,
            //找到product组件信息
            ProductGridModuleServiceImpl productModuleService = new ProductGridModuleServiceImpl();
            String tangramResult = productModuleService.transData(moduleConfigs.get(0), apiData);
            return tangramResult;
        } else {
            //返回数据库默认数据
            return moduleConfigs.get(0).getTangramTemplate();
        }
    }

    @Override
    public String queryGroupProductContents(String reqCode, String goodsgroupUuid, String pageNo, String pageSize) {
        //根据模版编号获取数据模版数据
        TangramTemplateConfig templateConfig = tangramTemplateConfigRepository.findTangramTemplateConfigByTangramCode(reqCode);
        //调用接口拿到api返回数据，并生成组件数据
        String tangramResult = queryProductModulesContents(goodsgroupUuid, pageNo, pageSize);
        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put(ModuleConstant.PRODUCTGRID, tangramResult);
        String result = TangramTemplateUtil.replaceModuleValue(templateConfig.getTangramTemplate(), resultMap);
        JSONObject resJson = JSON.parseObject(result);
        resJson.getJSONArray("cards").getJSONObject(0).put("load", "/tangram?version=1.0&reqCode=1016&goodsgroupUuid=" + goodsgroupUuid);
        return JSON.parseObject(result).toJSONString();
    }

    /**
     * 预处理商城首页返回的content页面数据
     *
     * @param responseData
     * @return
     */
    private Map<String, JSONArray> preApiContentData(JSONArray responseData) {
        Map<String, JSONArray> map = new HashMap<>(16);
        for (int i = 0; i < responseData.size(); i++) {
            JSONObject obj = responseData.getJSONObject(i);
            //1*1 cube使用imageAd组件实现，这里做特殊处理
            if (ModuleConstant.IMAGEAD.equals(obj.getString("type")) &&
                    obj.getJSONObject("data").getInteger("style") == 0) {
                obj.put("type", ModuleConstant.CUBE);
                obj.getJSONObject("data").put("cubeType", -1);
            }
            String newType = obj.getString("type");
            JSONArray arrayValue = new JSONArray();
            if (ModuleConstant.NAV.equals(newType)) {
                JSONArray old = map.get(ModuleConstant.NAV);
                arrayValue = obj.getJSONObject("data").getJSONArray("list");
                if (old != null) {
                    old.addAll(arrayValue);
                } else {
                    old = arrayValue;
                }
                map.put(ModuleConstant.NAV, old);
            } else if (ModuleConstant.CUBE.equals(newType)) {
                JSONArray old = map.get("Cube");
                if (old != null) {
                    arrayValue = old;
                }
                arrayValue.add(obj);
                map.put(ModuleConstant.CUBE, arrayValue);
            } else {
                map.put(newType, obj.getJSONObject("data").getJSONArray("list"));
            }
        }
        return map;
    }

    private JSONArray getProductGroupApiData(String goodsgroupUuid, String pageNo, String pageSize) {
        //请求第三方商城后台接口
        JSONArray apiData = null;
        try {
            String apiDataStr = thirdShopContentService.searchProductsByGroupId(goodsgroupUuid, pageNo,
                    pageSize, "sortWeight", "1");
            JSONObject jsonData = JSON.parseObject(apiDataStr);
            if ("200".equals(jsonData.getString("code"))) {
                apiData = jsonData.getJSONObject("data").getJSONArray("list");
            } else {
                //接口返回错误，记录日志 返回默认数据
                logger.error("调用第三方商城后台接口获取商品列表返回出错,code={},msg={}",
                        jsonData.get("code"), jsonData.get("message"));
            }
        } catch (Exception e) {
            //接口返回错误，记录日志 返回默认数据
            logger.error("调用第三方商城后台接口获取商品列表异常,msg={}",
                    e.getMessage());
        }
        return apiData;
    }

}
