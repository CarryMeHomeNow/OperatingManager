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
 * @Date 2020/9/1 5:37 下午
 */
@Service
public class HotProductModuleServiceImpl implements TangramModuleService<String, JSONArray> {


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
        JSONArray resultArray = new JSONArray();
        for (int i = 0; i < array.size(); i++) {
            JSONObject cloneObj = JSON.parseObject(template);
            JSONObject obj = array.getJSONObject(i);
            //克隆模版对象，修改值，然后add进数组
            cloneObj.getJSONObject("titleParams").put("textContent",obj.getString("productName"));
            cloneObj.getJSONObject("imgParams").put("imgUrl",obj.getString("imageUrl"));
            cloneObj.getJSONObject("currentPriceParams").put("textContent",obj.getString("sellingPrice"));
            cloneObj.getJSONObject("originalPriceParams").put("textContent",obj.getString("sellingPrice"));
            cloneObj.getJSONObject("actionParams").put("uuid",obj.getString("uuid"));
            cloneObj.getJSONObject("actionParams").put("actionUrl","tclplus://mall/goodsDetail"+"?uuid="+obj.getString("uuid")+"&neeLogin=false");
            resultArray.add(cloneObj);
        }
        return resultArray.toString();
    }
}
