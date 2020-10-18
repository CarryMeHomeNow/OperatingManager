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
 * @Desc : ImageAd组件服务
 * @Author yxlong
 * @Date 2020/8/27 11:06 上午
 */
@Service
public class ImageAdModuleServiceImpl implements TangramModuleService<String,JSONArray> {

    @Override
    public String process(TangramTemplateModuleConfig moduleConfig, TangramRequestParam param) {
        return null;
    }

    @Override
    public String transData(JSONArray template) {
        return null;
    }

    /**
     *
     * @param config 模版配置参数
     * @param responseData
     * @return
     */
    @Override
    public String transData(TangramTemplateModuleConfig config, JSONArray responseData) {
        String template = config.getTangramTemplate();
        JSONArray resultArray = new JSONArray();
        for (int i = 0; i < responseData.size(); i++) {
            JSONObject org = responseData.getJSONObject(i);
            JSONObject newObj = JSONObject.parseObject(template);
            newObj.put("id","banner_item_"+i);
            newObj.put("imgUrl",org.getJSONObject("imageData").get("url"));
            newObj.put("imgWidth",org.getJSONObject("imageData").get("width"));
            newObj.put("imgHeight",org.getJSONObject("imageData").get("height"));
            String actionUrl = LinkParseUtil.getActionUrlByLinkData(org.getJSONObject("linkData"));
            newObj.getJSONObject("actionParams").put("actionUrl",actionUrl);
            resultArray.add(newObj);
        }
        return resultArray.toJSONString();
    }
}
