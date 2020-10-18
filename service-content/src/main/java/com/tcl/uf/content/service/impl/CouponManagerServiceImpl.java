package com.tcl.uf.content.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tcl.commonservice.service.vo.OfficialMallCouponReponseDataVo;
import com.tcl.uf.common.base.ex.ProcessControlException;
import com.tcl.uf.common.base.util.http.HttpClientUtils;
import com.tcl.uf.content.configuration.OfficialMallConfiguration;
import com.tcl.uf.content.consts.OfficialMallConstants;
import com.tcl.uf.content.service.CouponManagerService;
import com.tcl.uf.content.service.OfficialOauthService;
import com.tcl.uf.content.vo.OfficialOauthEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author youyun.xu
 * @ClassName: CouponManagerServiceImpl
 * @Description: 优惠券管理Service
 * @date 2020/7/23 15:23
 */
@Service("couponManagerService")
public class CouponManagerServiceImpl implements CouponManagerService {

    @Autowired
    private OfficialMallConfiguration officialMallConfiguration;

    @Autowired
    private OfficialOauthService officialOauthService;

    @Override
    public OfficialMallCouponReponseDataVo findCouponList(String currentPage, String pageSize, String couponTypeName, String collectType, String cansend, String startTime, String endTime) throws ProcessControlException {
        OfficialOauthEntity officialOauthEntity= officialOauthService.obtainLoginToken();
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", HttpClientUtils.APPLICATION_JSON);
        header.put("ec-token", officialOauthEntity.getToken());

        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("pageSize", pageSize);
        params.put("couponTypeName", couponTypeName);
        params.put("collectType", collectType);
        params.put("cansend", cansend);
        params.put("startTime", startTime);
        params.put("endTime", endTime);

        String responseText = null;
        try {
            String url  = HttpClientUtils.nameValuePair(officialMallConfiguration.getRequestUrl() + OfficialMallConstants.COUPONTYPE_QUERYLIST_URL,params);
            responseText = HttpClientUtils.sendGet(url, header);
        } catch (IOException e) {
            throw new ProcessControlException("调用官方商城查询商品接口异常");
        }
        JSONObject responseData = JSONObject.parseObject(responseText);
        if (!OfficialMallConstants.RESPONSE_CODE.equals(responseData.getString("code"))) {
            throw new ProcessControlException(responseData.getString("message"));
        }
        //执行业务代码
        JSONObject businessData = responseData.getJSONObject("data");
        return JSONObject.toJavaObject(businessData, OfficialMallCouponReponseDataVo.class);
    }


    @Override
    public String sendCoupon(String ssoids, String couponTypeUuid) throws ProcessControlException {
        OfficialOauthEntity officialOauthEntity= officialOauthService.obtainLoginToken();
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", HttpClientUtils.APPLICATION_JSON);
        header.put("ec-token", officialOauthEntity.getToken());//(待接口自动获取)

        Map<String, Object> params = new HashMap<>();
        params.put("ssoids", ssoids);
        params.put("couponTypeUuid", couponTypeUuid);

        String responseText = null;
        try {
            String url  = HttpClientUtils.nameValuePair(officialMallConfiguration.getRequestUrl() + OfficialMallConstants.SEND_COUPON_URL ,params);
            responseText = HttpClientUtils.sendGet( url , header);
        } catch (IOException e) {
            throw new ProcessControlException("调用官方商城发放卡券接口异常");
        }
        JSONObject resJson = JSONObject.parseObject(responseText);
        String responseCode = resJson.getString("code");
        String transId = resJson.getString("transId");
        if (!OfficialMallConstants.RESPONSE_CODE.equals(responseCode)) {
            String responseMessage = resJson.getString("message");
            throw new ProcessControlException(responseMessage);
        }
        return transId;
    }
}
