package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.tangram.model.TangramTemplateModuleConfig;
import com.tcl.uf.tangram.service.TangramModuleService;
import com.tcl.uf.tangram.vo.TangramRequestParam;
import org.springframework.stereotype.Service;

/**
 * @Desc :商品分组
 * @Author yxlong
 * @Date 2020/8/28 10:51 上午
 */
@Service
public class GroupTypeModuleServiceImpl implements TangramModuleService<String, JSONArray> {
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
        JSONObject templateJson = JSON.parseObject(template);
        JSONArray resultArray = new JSONArray();
        //替换模板中tabcontents的值
        JSONArray tabContents = new JSONArray();
        //模版中具体数据
        JSONObject tabContent = templateJson.getJSONArray("tabContents").getJSONObject(0);
        for (int i = 0; i < array.size(); i++) {
            //api原始list数据
            JSONObject source = array.getJSONObject(i);
            //拷贝一个模版中的数据
            JSONObject cloneData = (JSONObject) tabContent.clone();
            cloneData.put("titleContent",source.getString("categoryName"));
            cloneData.put("subtitleContent","");
            JSONObject actionParams = new JSONObject();
            actionParams.put("actionType","refresh");
            actionParams.put("cardId","card6");
            actionParams.put("actionUrl","/tangram?reqCode=1012&version=1.0&goodsgroupUuid="+source.getString("goodsgroupUuid"));
            cloneData.put("actionParams",actionParams);
            tabContents.add(cloneData);
        }
        templateJson.put("tabContents",tabContents);
        resultArray.add(templateJson);
        return resultArray.toJSONString();
    }
}
