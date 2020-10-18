package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.tangram.model.TangramTemplateModuleConfig;
import com.tcl.uf.tangram.service.TangramModuleService;
import com.tcl.uf.tangram.util.LinkParseUtil;
import com.tcl.uf.tangram.vo.TangramRequestParam;
import org.springframework.stereotype.Service;

/**
 * @Desc :
 * @Author yxlong
 * @Date 2020/9/1 7:00 下午
 */
@Service
public class HotCategoryModuleServiceImpl implements TangramModuleService<String, JSONArray> {
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
        JSONArray resultArray = new JSONArray();
        int len = array.size();
        for (int i = 0; i < len; i++) {
            JSONObject org = array.getJSONObject(i);
            JSONObject newObj = JSON.parseObject(config.getTangramTemplate());
            newObj.getJSONObject("imgParams").put("imgUrl",org.getString("TwoLevelImg"));
            newObj.getJSONObject("textParams").put("textContent",org.getString("TwoLevelTitle"));
            String actionUrl = LinkParseUtil.getActionUrlByLinkData(org);
            newObj.getJSONObject("actionParams").put("actionUrl",actionUrl);
            JSONObject style1 = JSON.parseObject("{\"margin\":[\"10\",\"0\",\"0\",\"0\"]}");
            JSONObject style2 = JSON.parseObject("{\"margin\":[\"0\",\"0\",\"20\",\"0\"]}");
            if(i<=3){
                newObj.put("style",style1);
            }else{
                newObj.put("style",style2);
            }
            resultArray.add(newObj);
        }
        return resultArray.toJSONString();
    }
}
