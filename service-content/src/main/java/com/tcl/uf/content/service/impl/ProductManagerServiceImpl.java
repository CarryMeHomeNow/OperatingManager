package com.tcl.uf.content.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tcl.commonservice.service.vo.OfficialMallProductCategoryReponseDataVo;
import com.tcl.commonservice.service.vo.OfficialMallProductReponseDataVo;
import com.tcl.uf.common.base.ex.ProcessControlException;
import com.tcl.uf.common.base.util.http.HttpClientUtils;
import com.tcl.uf.content.configuration.OfficialMallConfiguration;
import com.tcl.uf.content.consts.OfficialMallConstants;
import com.tcl.uf.content.service.OfficialOauthService;
import com.tcl.uf.content.service.ProductManagerService;
import com.tcl.uf.content.vo.OfficialOauthEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youyun.xu
 * @ClassName: ProductManagerServiceImpl
 * @Description: 商品管理Service
 * @date 2020/8/11 17:46
 */
@Service("productManagerService")
public class ProductManagerServiceImpl implements ProductManagerService {

    @Autowired
    private OfficialMallConfiguration officialMallConfiguration;

    @Autowired
    private OfficialOauthService officialOauthService;

    @Override
    public OfficialMallProductReponseDataVo findProductList(String nowPage, String pageShow, String terminalType, String buyState, String productType, String productName, String productNo, String orderBy, String platformUuid,String categoryUuid) throws ProcessControlException{
        OfficialOauthEntity officialOauthEntity= officialOauthService.obtainLoginToken();

        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", HttpClientUtils.APPLICATION_JSON);
        header.put("ec-token",officialOauthEntity.getToken());

        Map<String, Object> params = new HashMap<>();
        params.put("nowPage", nowPage);
        params.put("pageShow", pageShow);
        params.put("terminalType", terminalType);
        params.put("buyState", buyState);
        params.put("productType", productType);
        params.put("productName", productName);
        params.put("productNo", productNo);
        params.put("orderBy", orderBy);
        params.put("platformUuid", platformUuid);
        params.put("categoryUuid", categoryUuid);
        String responseText;
        try {
            String requestUrl = HttpClientUtils.nameValuePair(officialMallConfiguration.getRequestUrl() +OfficialMallConstants.PRODUCT_LIST_URL,params);
            responseText = HttpClientUtils.sendGet( requestUrl, header);
        } catch (Exception e) {
            throw new ProcessControlException("调用官方商城查询商品接口异常");
        }
        JSONObject responseData = JSONObject.parseObject(responseText);
        if (!OfficialMallConstants.RESPONSE_CODE.equals(responseData.getString("code"))) {
            throw new ProcessControlException(responseData.getString("message"));
        }
        //执行业务代码
        JSONObject businessData = responseData.getJSONObject("data");
        return JSONObject.toJavaObject(businessData, OfficialMallProductReponseDataVo.class);
    }

    @Override
    public OfficialMallProductCategoryReponseDataVo findIntegralMallCategoryList(String categoryUuid) throws ProcessControlException {
        OfficialOauthEntity officialOauthEntity= officialOauthService.obtainLoginToken();

        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", HttpClientUtils.APPLICATION_JSON);
        header.put("ec-token",officialOauthEntity.getToken());

        Map<String, Object> params = new HashMap<>();
        params.put("categoryUuid", categoryUuid);

        String responseText = null;
        try {
            String requestUrl = HttpClientUtils.nameValuePair(officialMallConfiguration.getRequestUrl() +OfficialMallConstants.CATEGORY_LIST_URL,params);
            responseText = HttpClientUtils.sendGet( requestUrl, header);
        } catch (Exception e) {
            throw new ProcessControlException("调用官方商城查询商品分类接口异常");
        }
        JSONObject responseData = JSONObject.parseObject(responseText);
        if (!OfficialMallConstants.RESPONSE_CODE.equals(responseData.getString("code"))) {
            throw new ProcessControlException(responseData.getString("message"));
        }
        return JSONObject.toJavaObject(responseData, OfficialMallProductCategoryReponseDataVo.class);
    }
}
