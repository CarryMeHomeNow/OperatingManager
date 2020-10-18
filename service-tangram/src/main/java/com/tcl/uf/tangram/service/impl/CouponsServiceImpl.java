package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.points.model.PointCoupon;
import com.tcl.commonservice.service.AdvertService;
import com.tcl.commonservice.service.PointsService;
import com.tcl.commonservice.service.dto.ResourceAppListParams;
import com.tcl.commonservice.service.vo.ResourceAppListVo;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.http.HttpClientUtils;
import com.tcl.uf.tangram.enums.TemplateEnums;
import com.tcl.uf.tangram.enums.URLConstant;
import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.service.CouponsService;
import com.tcl.uf.tangram.util.TangramTemplateUtil;
import com.tcl.uf.tangram.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongfk on 2020/8/29.
 * @version 1.0
 */
@Service
public class CouponsServiceImpl implements CouponsService {
    @Autowired
    public CommonService commonService;
    @Autowired
    public PointsService pointsService;
    public AdvertService advertService;

    @Override
    public String getCoupons(TangramRequestParam paramDTO) {
        String template = commonService.findTemplateById(paramDTO.getReqCode());

        //券包广告
        ResourceAppListParams appListParams = new ResourceAppListParams();
        appListParams.setLocationCode("COUPONS_BANNER");
        ResponseData<List<ResourceAppListVo>> listResponseData = advertService.intAppList(appListParams, paramDTO.getAccountId());
        List<ResourceAppListVo> advert = listResponseData == null ? null : listResponseData.getData();

        Map<String, Integer> pageParam = new HashMap<>();
        pageParam.put("page", paramDTO.getPage());
        pageParam.put("size", paramDTO.getSize());
        //积分优惠券
        ResponseData responseData = pointsService.list(pageParam);
        Page<PointCoupon> coupons = (Page<PointCoupon>) responseData.getData();

        //商城优惠券
        List<ShopCouponVO> shopCouPon = getShopCouPon(paramDTO);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(TemplateEnums.COUPONS_INTERNAl.getValue(), coupons);
        dataMap.put(TemplateEnums.COMMON_ADVERT.getValue(), advert);
        dataMap.put(TemplateEnums.COUPONS_SHOP.getValue(),shopCouPon);

        //无券
        if (null == coupons) {
            return commonService.findTemplateById("1009");
        }

        return parseData(template, dataMap);
    }

    /**
     * @param template
     * @param dataMap
     * @return
     */
    private String parseData(String template, Map<String, Object> dataMap) {
        Map<String, Object> parseMap = new HashMap<>();
        Page<PointCoupon> coupons = (Page<PointCoupon>) parseMap.get(TemplateEnums.COUPONS_INTERNAl.getValue());

        Map<String, Object> advertMap = commonService.turnAdvert(dataMap);
        Map<String, Object> couponMap = turnCoupon(coupons);

        parseMap.put(TemplateEnums.COMMON_ADVERT.getValue(), advertMap);
        parseMap.put(TemplateEnums.COUPONS_LIST.getValue(), couponMap);

        return TangramTemplateUtil.replaceModuleValue(template, parseMap);
    }

    public List<ShopCouponVO> getShopCouPon(TangramRequestParam paramDTO) {
        String result = null;
        List<ShopCouponVO> shopCouponVOList = new ArrayList<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String apttoken = request.getHeader("APTTOKEN");
        //商城优惠券
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("platform", "platform_tcl_shop");
        headerMap.put("storeUuid", " tclplus");
        headerMap.put("t-id", "TCL");
        headerMap.put("terminalType", "02");
        headerMap.put("accessToken", apttoken);
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("state", "1");
        bodyMap.put("nowPage", String.valueOf(paramDTO.getPage()));
        bodyMap.put("pageShow", String.valueOf(paramDTO.getSize()));
        try {
            result = HttpClientUtils.sendPost(URLConstant.SHOP_URL + URLConstant.COUPON_URI, bodyMap, headerMap);
            JSONObject object = JSONObject.parseObject(result);
            JSONObject dataObj = (JSONObject) object.get("data");
            JSONArray list = dataObj.getJSONArray("list");
            return JSONObject.parseArray(list.toJSONString(),ShopCouponVO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shopCouponVOList;
    }

    /**
     * 优惠券列表
     *
     * @param coupons
     * @return
     */
    private Map<String, Object> turnCoupon(Page<PointCoupon> coupons) {
        Map<String, Object> parseMap = new HashMap<>();
        List<CouponsVO> list = new ArrayList<>();
        for (PointCoupon coupon : coupons) {
            CouponsVO couponsVO = new CouponsVO();
            couponsVO.setType("coupon");
            Style style = new Style();
            style.setBgColor("#ffffff");
            couponsVO.setStyle(style);
            StatusParams statusParams = new StatusParams();
            statusParams.setTextContent("待领取");
            statusParams.setTextColor("#000000");
            statusParams.setFontSize(15.0);
            statusParams.setLineSpace(4);
            couponsVO.setStatusParams(statusParams);
            TitleParams titleParams = new TitleParams();
            titleParams.setTextContent(coupon.getName());
            titleParams.setTextColor("#000000");
            titleParams.setFontSize(15);
            titleParams.setLineSpace(4);
            couponsVO.setTitleParams(titleParams);
            ExpireTimeParams expireTimeParams = new ExpireTimeParams();
            expireTimeParams.setTextContent("有效时间（积分优惠券暂无设置） 后调");
            expireTimeParams.setTextColor("#000000");
            expireTimeParams.setFontSize(15);
            expireTimeParams.setLineSpace(4);
            couponsVO.setExpireTimeParams(expireTimeParams);
            RangParams rangParams = new RangParams();
            rangParams.setTextContent(coupon.getContent());
            rangParams.setTextColor("#000000");
            rangParams.setFontSize(15);
            rangParams.setLineSpace(4);
            couponsVO.setRangParams(rangParams);
            CouponTypeParams couponTypeParams = new CouponTypeParams();
            couponTypeParams.setTextContent("优惠券");
            couponTypeParams.setTextColor("#000000");
            couponTypeParams.setFontSize(15);
            couponTypeParams.setLineSpace(4);
            couponsVO.setCouponTypeParams(couponTypeParams);
            ButtonParams buttonParams = new ButtonParams();
            buttonParams.setTextContent("领取");
            buttonParams.setTextColor("#000000");
            buttonParams.setFontSize(15);
            buttonParams.setLineSpace(4);
            ActionParams actionParams = new ActionParams();
            actionParams.setCouponId(coupon.getDcCouponId());
            buttonParams.setActionParams(actionParams);
            couponsVO.setButtonParams(buttonParams);
            couponsVO.setReadPointStatus(true);
            ActionParams couponAction = new ActionParams();
            couponAction.setActionType("jump");
            couponAction.setActionUrl("http://www.baidu.com");
            couponAction.setCouponId(coupon.getDcCouponId());
            list.add(couponsVO);
        }
        parseMap.put(TemplateEnums.COUPONS_LIST.getValue(), JSON.toJSON(coupons));
        return parseMap;
    }
}
