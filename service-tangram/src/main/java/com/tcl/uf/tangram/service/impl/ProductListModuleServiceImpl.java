package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.tangram.model.TangramTemplateModuleConfig;
import com.tcl.uf.tangram.service.TangramModuleService;
import com.tcl.uf.tangram.vo.TangramRequestParam;
import org.springframework.stereotype.Service;

/**
 * @Desc :产品列表组件实现类
 * @Author yxlong
 * @Date 2020/9/5 12:36 下午
 */
@Service
public class ProductListModuleServiceImpl implements TangramModuleService<String, JSONArray> {
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
            cloneObj.getJSONObject("titleParams").put("textContent",obj.getString("productName"));
            cloneObj.getJSONObject("imgParams").put("imgUrl",obj.getString("imageUrl"));
            cloneObj.getJSONObject("imgParams").put("smallIcon",obj.getString("labelImageUrl"));
            //TODO 当前价数值先对应后台的基础价，原始价数值对应后台的划线价字段，待确认
            cloneObj.getJSONObject("currentPriceParams").put("textContent",obj.getString("price"));
            cloneObj.getJSONObject("originalPriceParams").put("textContent",obj.getString("sellingPrice"));
            String actionUrl="tclplus://mall/goodsDetail"+"?uuid="+obj.getString("goodsUuid")+"&neeLogin=false";
            cloneObj.getJSONObject("actionParams").put("actionUrl",actionUrl);
            resultArray.add(cloneObj);
        }
        return resultArray.toJSONString();
    }
}
