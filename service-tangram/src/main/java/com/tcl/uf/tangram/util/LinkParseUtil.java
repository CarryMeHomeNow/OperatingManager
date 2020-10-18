package com.tcl.uf.tangram.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tcl.uf.tangram.enums.LinkTypeConstant;
import com.tcl.uf.tangram.vo.LinkData;

/**
 * @Desc :
 * @Author yxlong
 * @Date 2020/9/2 3:43 下午
 */
public class LinkParseUtil {

    public static String getActionUrlByLinkData(JSONObject linkData){
        String actionUrl = "";
        String typeValue = linkData.getString("typeValue");
        String objectValue = linkData.getString("objectValue");
        String objectLabel = linkData.getString("objectLabel");
        switch (typeValue){
            case LinkTypeConstant.GOODS:
                actionUrl="tclplus://mall/goodsDetail"+"?uuid="+objectValue+"&neeLogin=false";
                break;
            case LinkTypeConstant.CUSTOM:
                actionUrl = objectValue;
                break;
            case LinkTypeConstant.GOODSGROUP:
                actionUrl = "tclplus://category/productList?needLogin=false"+"&keyword="
                        +objectLabel+"&cateGroupUuid="+objectValue;
                break;
            default:
                break;
        }
        return actionUrl;

    }
}
