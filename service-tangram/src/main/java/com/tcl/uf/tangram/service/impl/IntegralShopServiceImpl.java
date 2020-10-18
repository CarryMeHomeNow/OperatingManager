package com.tcl.uf.tangram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commonservice.service.AdvertService;
import com.tcl.commonservice.service.ShopService;
import com.tcl.commonservice.service.dto.ResourceAppListParams;
import com.tcl.commonservice.service.vo.IntegralShopGoodsFo;
import com.tcl.commonservice.service.vo.IntegralShopOrderFo;
import com.tcl.commonservice.service.vo.ResourceAppListVo;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.http.HttpClientUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.tangram.enums.TemplateEnums;
import com.tcl.uf.tangram.service.CommonService;
import com.tcl.uf.tangram.service.IntegralShopService;
import com.tcl.uf.tangram.util.TangramTemplateUtil;
import com.tcl.uf.tangram.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tcl.uf.tangram.service.impl.ServerTabServiceImpl.poingURL;

/**
 * @author zhongfk on 2020/8/27.
 * @version 1.0
 */
@Service
public class IntegralShopServiceImpl implements IntegralShopService {
    @Autowired
    public CommonService commonService;
    @Autowired
    public ShopService shopService;
    @Autowired
    public AdvertService advertService;

    @Override
    public String getIntegralShop(TangramRequestParam paramDTO) {
        String template = commonService.findTemplateById(paramDTO.getReqCode());

        //广告位
        ResourceAppListParams resourceAppListParams = new ResourceAppListParams();
        ResponseData<List<ResourceAppListVo>> listResponseData = advertService.intAppList(resourceAppListParams, paramDTO.getAccountId());
        List<ResourceAppListVo> data = listResponseData == null ? null : listResponseData.getData();

        //积分商城列表
        ListWithPage<IntegralShopGoodsFo> desc = shopService.getGoodslist(0, 10, "new", "", 1);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(TemplateEnums.SHOP_GOODS_BANNER.getValue(), data);
        dataMap.put(TemplateEnums.SHOP_GOODS_lIST.getValue(), desc);
        //总积分  accountId
        try {
            String message = HttpClientUtils.sendGet(poingURL + "1282580499042140200");
            JSONObject object = JSON.parseObject(message);
            Integer score = (Integer) object.get("score");
            //积分
            dataMap.put(TemplateEnums.SHOP_TOTAL_INTEGRAL.getValue(), score);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseData(template, dataMap);
    }

    private String parseData(String template, Map<String, Object> dataMap) {
        Map<String, Object> parseMap = new HashMap<>();
        List<ResourceAppListVo> data = (List<ResourceAppListVo>) dataMap.get(TemplateEnums.SHOP_GOODS_BANNER.getValue());
        ListWithPage<IntegralShopGoodsFo> desc = (ListWithPage<IntegralShopGoodsFo>) dataMap.get(TemplateEnums.SHOP_GOODS_lIST.getValue());

        Map<String, Object> advertMap = turnAdvert(data);
        Map<String, Object> shopMap = turnShopList(desc);
        Map<String, Object> actionMap = turnAction(desc);

        parseMap.putAll(advertMap);
        parseMap.putAll(shopMap);
        parseMap.putAll(actionMap);
        parseMap.put(TemplateEnums.SHOP_TOTAL_INTEGRAL.getValue(), dataMap.get(TemplateEnums.SHOP_TOTAL_INTEGRAL.getValue()));

        return TangramTemplateUtil.replaceModuleValue(template, dataMap);
    }


    private Map<String, Object> turnAdvert(List<ResourceAppListVo> data) {
        int i = 0;
        Map<String, Object> advertMap = new HashMap<>();
        List<AdvertiseVO> list = new ArrayList<AdvertiseVO>();
        if (null != data) {
            for (ResourceAppListVo vo : data) {
                i++;
                AdvertiseVO advertiseVO = new AdvertiseVO();
                advertiseVO.setType("image");
                advertiseVO.setId("banner_item_" + i);
                advertiseVO.setImgUrl(vo.getAdPicUrl());
                advertiseVO.setImgPlaceHolder("TCLPlaceHolder");
                advertiseVO.setImgWidth(524);
                advertiseVO.setImgWidth(200);
                ActionParams actionParams = new ActionParams();
                actionParams.setActionUrl(vo.getAdLinkUrl());
                actionParams.setActionType("jump");
                list.add(advertiseVO);
            }
        }
        advertMap.put(TemplateEnums.SHOP_GOODS_BANNER.getValue(), JSON.toJSONString(list));

        return advertMap;
    }

    private Map<String, Object> turnShopList(ListWithPage<IntegralShopGoodsFo> desc) {
        Map<String, Object> shopMap = new HashMap<>();
        List<IntegralShopGoodsFo> list = desc.getList();
        StringBuilder sb = new StringBuilder();
        for (IntegralShopGoodsFo vo : list) {
            ImgParams imgParams = new ImgParams();
            imgParams.setImgUrl(vo.getImage());
            imgParams.setImgPlaceHolder("TCLPlaceHolder");
            imgParams.setImgWidth(300);
            imgParams.setImgHeight(300);
            sb.append(JSON.toJSONString(imgParams) + ",");
            TitleParams titleParams = new TitleParams();
            titleParams.setTextContent(vo.getGoodsName());
            titleParams.setTextAlignment("left");
            titleParams.setTextColor("#000000");
            titleParams.setFontSize(12);
            titleParams.setLineSpace(4);
            sb.append(JSON.toJSONString(titleParams)+",");
            PriceParams priceParams = new PriceParams();
            priceParams.setTextContent(String.valueOf(vo.getPrice()));
            priceParams.setTextAliment("left");
            priceParams.setFontSize(12);
            priceParams.setLineSpace(4);
            sb.append(JSON.toJSONString(priceParams)+",");
            PointsParams pointsParams = new PointsParams();
            pointsParams.setTextContent(String.valueOf(vo.getPointmoney()));
            pointsParams.setTextAliment("left");
            pointsParams.setTextColor("#000000");
            pointsParams.setFontSize(12);
            pointsParams.setLineSpace(4);
            sb.append(JSON.toJSONString(pointsParams)+",");
            ButtonParams buttonParams = new ButtonParams();
            buttonParams.setTextContent("兑换");
            buttonParams.setTextAliment("right");
            buttonParams.setTextColor("#000000");
            buttonParams.setFontSize(12);
            buttonParams.setLineSpace(4);
            sb.append(JSON.toJSONString(buttonParams)+",");
            ButtonDescParams buttonDescParams = new ButtonDescParams();
            buttonDescParams.setTextContent("限兑一次");
            buttonDescParams.setTextAlignment("right");
            buttonDescParams.setTextConlor("#000000");
            buttonDescParams.setFontSize(12);
            buttonDescParams.setLineSpace(4);
            sb.append(JSON.toJSONString(buttonDescParams));
        }
        shopMap.put(TemplateEnums.SHOP_GOODS_lIST.getValue(),sb);
        return shopMap;
    }

    private Map<String, Object> turnAction(ListWithPage<IntegralShopGoodsFo> desc) {
        Map<String, Object> shopMap = new HashMap<>();
        ActionParams hot = new ActionParams();
        hot.setActionUrl("http://tangram/xxx" + "new");
        hot.setActionType("jump");
        shopMap.put(TemplateEnums.SHOP_HOT_ACTION.getValue(), hot);
        ActionParams latest = new ActionParams();
        hot.setActionUrl("http://tangram/xxx" + "latest");
        hot.setActionType("jump");
        shopMap.put(TemplateEnums.SHOP_LATEST_ACTION.getValue(), hot);
        ActionParams price = new ActionParams();
        hot.setActionUrl("http://tangram/xxx" + "price");
        hot.setActionType("jump");
        shopMap.put(TemplateEnums.SHOP_PRICE_ACTION.getValue(), hot);
        ActionParams exchange = new ActionParams();
        hot.setActionUrl("http://tangram/xxx" + "exchange");
        hot.setActionType("jump");
        shopMap.put(TemplateEnums.SHOP_EXCHANGE_ACTION.getValue(), hot);
        return shopMap;
    }

    @Override
    public String getOrderList(TangramRequestParam paramDTO) {
        String template = commonService.findTemplateById(paramDTO.getReqCode());
        Map<String, Object> parseMap = new HashMap<>();
        ListWithPage<IntegralShopOrderFo> shopOrderlist = shopService.getShopOrderlist(paramDTO.getPage(), paramDTO.getSize(), "1502344234242", null, 1);
        List<IntegralShopOrderFo> list = shopOrderlist == null ? null : shopOrderlist.getList();
        Map<String, Object> turnAction = turnOrderAction();
        Map<String, Object> listMap = turnOrderList(list);

        parseMap.putAll(turnAction);
        parseMap.putAll(listMap);

        return TangramTemplateUtil.replaceModuleValue(template,parseMap);
    }

    private Map<String, Object> turnOrderList(List<IntegralShopOrderFo> list) {
        Map<String, Object> parseMap = new HashMap<>();
        List<OrderListVO> listVOS = new ArrayList<>();
        for (IntegralShopOrderFo vo : list) {
            OrderListVO listVO = new OrderListVO();
            listVO.setType("pointsOrder");
            Style style = new Style();
            style.setBgColor("#ffffff");
            OrderParams orderParams = new OrderParams();
            orderParams.setTextContent(vo.getOrdersn());
            orderParams.setTextAliment("left");
            orderParams.setTextColor("#000000");
            orderParams.setFontSize(12);
            orderParams.setLineSpace(4);
            ImgParams imgParams = new ImgParams();
            imgParams.setImgUrl(vo.getGoodsimage());
            imgParams.setImgPlaceHolder("TCLPlaceHolder");
            imgParams.setImgWidth(542);
            imgParams.setImgHeight(340);
            listVO.setImgParams(imgParams);
            TimeParams timeParams = new TimeParams();
            timeParams.setTextContent(vo.getFinishtime());
            timeParams.setTextAliment("right");
            timeParams.setTextColor("#000000");
            timeParams.setFontSize(12);
            timeParams.setLineSpace(4);
            listVO.setTimeParams(timeParams);
            StatusParams statusParams = new StatusParams();
            statusParams.setTextContent("状态3");
            statusParams.setTextAliment("left");
            statusParams.setTextColor("#000000");
            statusParams.setFontSize(Double.valueOf(12));
            statusParams.setLineSpace(4);
            listVO.setStatusParams(statusParams);
            PointsParams pointsParams = new PointsParams();
            pointsParams.setTextContent(String.valueOf(vo.getPointnum()));
            pointsParams.setTextAliment("right");
            pointsParams.setTextColor("#000000");
            pointsParams.setFontSize(12);
            pointsParams.setLineSpace(4);
            listVO.setPointsParams(pointsParams);
            listVO.setNum(1);
            ButtonParams buttonParams = new ButtonParams();
            buttonParams.setTextContent("复制快递单");
            buttonParams.setTextAliment("right");
            buttonParams.setTextContent("#000000");
            buttonParams.setFontSize(12);
            buttonParams.setLineSpace(4);
            listVO.setButtonParams(buttonParams);
        }
        parseMap.put(TemplateEnums.ORDER_lIST.getValue(),JSON.toJSONString(list));
        return parseMap;
    }

    private Map<String,Object> turnOrderAction() {
        Map<String,Object> parseMap = new HashMap<>();
        ActionParams actionParams = new ActionParams();
        actionParams.setActionType("jump");
        actionParams.setActionUrl("http://sserwq");
        parseMap.put(TemplateEnums.ORDER_LOAD_ACTION.getValue(),JSON.toJSONString(actionParams));
        ActionParams completeAction = new ActionParams();
        completeAction.setActionType("jump");
        completeAction.setActionUrl("http://sserwq");
        parseMap.put(TemplateEnums.ORDER_COMPLATE_ACTION.getValue(),JSON.toJSONString(completeAction));
        ActionParams  record = new ActionParams();
        record.setActionType("jump");
        record.setActionUrl("http://sserwq");
        parseMap.put(TemplateEnums.ORDER_LOAD_ACTION.getValue(),JSON.toJSONString(record));
        return parseMap;
    }
}
