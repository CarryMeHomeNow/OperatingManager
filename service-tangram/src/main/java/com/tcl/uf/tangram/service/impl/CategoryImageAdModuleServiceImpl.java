package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.tangram.model.TangramTemplateModuleConfig;
import com.tcl.uf.tangram.service.TangramModuleService;
import com.tcl.uf.tangram.vo.TangramRequestParam;
import org.springframework.stereotype.Service;

/**
 * @Desc :
 * @Author yxlong
 * @Date 2020/9/1 7:03 下午
 */
@Service
public class CategoryImageAdModuleServiceImpl implements TangramModuleService<String, JSONArray> {
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
        String template = config.getTangramTemplate();
        JSONObject source = JSON.parseObject(template);
        JSONArray resultArray = new JSONArray();
        for (int i = 0; i < array.size(); i++) {
            JSONObject org = array.getJSONObject(i);
            JSONObject newObj = (JSONObject) source.clone();
            JSONObject actionParams = new JSONObject();
            newObj.put("imgUrl",org.getString("OneLevelImg"));
            newObj.put("imgPlaceHolder",org.getString("OneLevelTitle"));
            actionParams.put("actionType","jump");
            actionParams.put("actionUrl","http://");
            newObj.put("actionParams",actionParams);
            resultArray.add(newObj);
        }
        return resultArray.toJSONString();
    }
}
