package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.tangram.model.TangramTemplateModuleConfig;
import com.tcl.uf.tangram.service.TangramModuleService;
import com.tcl.uf.tangram.vo.TangramRequestParam;
import org.springframework.stereotype.Service;

/**
 * @Desc : 热门搜索关键词
 * @Author yxlong
 * @Date 2020/9/5 10:25 上午
 */
@Service
public class HotSearchWordModuleServiceImpl implements TangramModuleService<String, JSONArray> {
    @Override
    public String process(TangramTemplateModuleConfig moduleConfig, TangramRequestParam param) {
        return null;
    }

    @Override
    public String transData(JSONArray array) {
        return null;
    }

    @Override
    public String transData(TangramTemplateModuleConfig config, JSONArray array) {
        JSONArray resArray = new JSONArray();
        JSONObject resObj = JSONObject.parseObject(config.getTangramTemplate());
        //搜索关键词组件内需要的关键词数组
        JSONArray keywords = new JSONArray();
        if(array!=null&&array.size()>0){
            for(Object data:array){
                JSONObject dataObj = (JSONObject) data;
                keywords.add(dataObj.getString("searchWord"));
            }
        }
        resObj.put("items",keywords);
        resArray.add(resObj);
        return resArray.toJSONString();
    }
}
