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
 * @Desc : Nav组件数据处理
 * @Author yxlong
 * @Date 2020/8/27 11:27 上午
 */
@Service
public class NavModuleServiceImpl implements TangramModuleService<String, JSONArray> {
    @Override
    public String process(TangramTemplateModuleConfig moduleConfig, TangramRequestParam param) {
        return null;
    }

    @Override
    public String transData(JSONArray objects) {
        return null;
    }
    @Override
    public String transData(TangramTemplateModuleConfig config, JSONArray responseData) {
        //JSONObject source = JSON.parseObject(config.getTangramTemplate());
        JSONArray resultArray = new JSONArray();
        int len = responseData.size();
        len = (len>5&&len<10)?5:len;
        for (int i = 0; i < len; i++) {
            JSONObject org = responseData.getJSONObject(i);
            JSONObject newObj = JSON.parseObject(config.getTangramTemplate());
            newObj.put("id","nav_item"+i);
            JSONObject style1 = JSON.parseObject("{\"margin\":[\"10\",\"0\",\"20\",\"0\"]}");
            JSONObject style2 = JSON.parseObject("{\"margin\":[\"0\",\"0\",\"20\",\"0\"]}");
            newObj.getJSONObject("imgParams").put("imgUrl",org.getJSONObject("imageData").get("url"));
            newObj.getJSONObject("imgParams").put("imgWidth",org.getJSONObject("imageData").get("width"));
            newObj.getJSONObject("imgParams").put("imgHeight",org.getJSONObject("imageData").get("height"));
            newObj.getJSONObject("textParams").put("textContent",org.getString("title"));
            String actionUrl = LinkParseUtil.getActionUrlByLinkData(org.getJSONObject("linkData"));
            newObj.getJSONObject("actionParams").put("actionUrl",actionUrl);
            if(i<5){
                newObj.put("style",style1);
            }else{
                newObj.put("style",style2);
            }
            resultArray.add(newObj);
        }
        return resultArray.toJSONString();
    }
}
