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
 * @Desc : cube组件数据处理
 * @Author yxlong
 * @Date 2020/8/27 11:40 上午
 */
@Service
public class CubeModuleServiceImpl implements TangramModuleService<String, JSONArray> {
    @Override
    public String process(TangramTemplateModuleConfig moduleConfig, TangramRequestParam param) {
        return null;
    }

    @Override
    public String transData(JSONArray objects) {
        return null;
    }

    @Override
    public String transData(TangramTemplateModuleConfig config, JSONArray objects) {
        StringBuffer sb = new StringBuffer();
        int len = objects.size();
        for (int i = 0; i <len ; i++) {
//            int top = (i==0)?10:5;
//            int right=5,left = 5;
//            int bottom = (i==(len-1))?10:((i==0)?5:0);
//            String margin = "["+top+","+right+","+left+","+bottom+"]";
            JSONObject cardMargin = JSONObject.parseObject("{\"margin\":[\"10\",\"6\",\"10\",\"6\"]}");
            JSONObject obj = objects.getJSONObject(i);
            String type = cubeTypeToColumn(obj.getJSONObject("data").getInteger("cubeType"));
            JSONObject buju = new JSONObject();
            buju.put("type",type);
            buju.put("id","cube_card"+i);
            buju.put("style", JSON.parse("{\"bgColor\": \"#F4F4F5\",\"margin\":"+cardMargin+"}"));
            JSONArray items = new JSONArray();
            JSONArray arrayData = obj.getJSONObject("data").getJSONArray("list");
            for (int j = 0; j <arrayData.size() ; j++) {
                JSONObject item = new JSONObject();
                JSONObject source = arrayData.getJSONObject(j);
                item.put("type","image");
                item.put("cornerRadius","4");
                JSONObject itemStyle = JSONObject.parseObject("{\"margin\":[\"6\",\"6\",\"0\",\"6\"]}");
                item.put("style",itemStyle);
                item.put("imgUrl",source.getJSONObject("imageData").getString("url"));
                item.put("imgWidth",source.getJSONObject("imageData").getIntValue("width"));
                item.put("imgHeight",source.getJSONObject("imageData").getIntValue("height"));
                JSONObject actionParams = new JSONObject();
                String actionUrl = LinkParseUtil.getActionUrlByLinkData(source.getJSONObject("linkData"));
                actionParams.put("actionUrl",actionUrl);
                actionParams.put("actionType","jump");
                item.put("actionParams",actionParams);
                items.add(item);
            }
            buju.put("items",items);
            sb.append(buju.toJSONString());
            if(i!=len-1){
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private static String cubeTypeToColumn(int cubeType){
        String type = "container-oneColumn";
        switch (cubeType){
            //转换后的1*1魔方，对应tangram type =container
            case -1:
                type = "container-oneColumn";
                break;
            case 1:
                type = "container-twoColumn";
                break;
            case 2:
                type = "container-threeColumn";
                break;
            default:
                break;
        }
        return type;
    }
}
