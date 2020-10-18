package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcl.uf.tangram.enums.ModuleConstant;
import com.tcl.uf.tangram.enums.PageTypeConstant;
import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.service.SearchService;
import com.tcl.uf.tangram.service.TangramMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc :
 * @Author yxlong
 * @Date 2020/9/5 1:47 下午
 */
@Service
public class SearchServiceImpl implements SearchService {

    final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private TangramMainService tangramMainService;

    @Override
    public String getSearchPageData(String reqCode,String uiType) {
        Map<String, JSONArray> apiData = new HashMap<>(16);
        //获取热门搜索词数据
        String hotKeywords = commonService.getHotSearchKeyword("2");
        //获取热门商品榜数据
        String hotList = commonService.getPageManageByPageType(PageTypeConstant.SEARCH,uiType);
        JSONArray hotKeywordsJson = JSON.parseArray(hotKeywords);
        JSONArray goods = parseHotListData(hotList);
        apiData.put(ModuleConstant.HOTSEARCHKEYWORD,hotKeywordsJson);
        apiData.put(ModuleConstant.PRODUCTLIST,goods);
        String resultTangram = tangramMainService.execute(reqCode,apiData);

        return resultTangram;
    }

    @Override
    public String getNoResultData(String reqCode) {
        return null;
    }

    public JSONArray parseHotListData(String hotList){
        JSONArray afterParse = new JSONArray();
        try{
            JSONObject hotListJson = JSON.parseObject(hotList);
            JSONArray items = hotListJson.getJSONArray("items");
            for (int i = 0; i < items.size(); i++) {
                JSONObject item = items.getJSONObject(i);
                if("Goods".equals(item.getString("type"))){
                    afterParse = item.getJSONObject("data").getJSONArray("goodsList");
                    break;
                }
            }
        }catch (Exception e){
            logger.error("搜索页推荐接口返回数据json解析错误{}",e.getMessage());
            return afterParse;
        }
        return afterParse;
    }
}
