package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcl.uf.tangram.service.ProductCategoryService;
import com.tcl.uf.tangram.service.TangramMainService;
import com.tcl.uf.tangram.service.ThirdShopContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc :
 * @Author yxlong
 * @Date 2020/8/31 3:28 下午
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    final static Logger logger = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);
    @Autowired
    private ThirdShopContentService thirdShopContentService;

    @Autowired
    private TangramMainService tangramMainService;

    @Override
    public List<Map<String, Object>> getProductCategory() {
        List<Map<String, Object>> list = new ArrayList<>();
        JSONArray resJson = getApiResponseData("APP");
        if (resJson != null) {
            for (Object obj : resJson) {
                Map<String, Object> map = new HashMap<>(16);
                JSONObject objJson = (JSONObject) obj;
                map.put("categoryName", objJson.getString("OneLevelTitle"));
                list.add(map);
            }
        }
        return list;
    }

    @Override
    public String getCategoryTangramData(String categroyName, String reqCode) {
        Map<String, JSONArray> apiData = parseData(categroyName);
        return tangramMainService.execute(reqCode, apiData);
    }

    private Map<String, JSONArray> parseData(String catagoryName) {
        Map<String, JSONArray> resMap = new HashMap<>(16);
        JSONArray resJson = getApiResponseData("APP");
        //根据参数传进来的分类name过滤掉返回的数据
        if (resJson != null && resJson.size() > 0) {
            JSONObject categoryObj = null;
            for (Object obj : resJson) {
                JSONObject jsonObj = (JSONObject) obj;
                if (catagoryName.equals(jsonObj.getString("OneLevelTitle"))) {
                    categoryObj = jsonObj;
                    break;
                }
            }
            JSONArray first = new JSONArray();
            first.add(categoryObj);
            resMap.put("CategoryImageAd", first);
            resMap.put("HotCategory", categoryObj.getJSONArray("TwoLevel"));
            resMap.put("HotProduct", categoryObj.getJSONArray("goodsList"));
        }
        return resMap;
    }

    /**
     * 获取商品分类的api返回数据
     *
     * @return
     */
    private JSONArray getApiResponseData(String uiType) {
        JSONArray apiData = null;
        try {
            String apiDataStr = thirdShopContentService.getProductCategoryDetails(uiType, "productGroup_page");
            JSONObject jsonData = JSON.parseObject(apiDataStr);
            if ("200".equals(jsonData.getString("code"))) {
                apiData = JSONArray.parseArray(jsonData.getJSONObject("data").getString("content"));
            } else {
                logger.error("调用第三方商城后台接口获取商品分类返回出错,code={},msg={}",
                        jsonData.get("code"), jsonData.get("message"));
            }
        } catch (Exception e) {
            logger.error("调用第三方商城后台接口获取商品分类异常,msg={}",
                    e.getMessage());
        }
        return apiData;
    }
}
