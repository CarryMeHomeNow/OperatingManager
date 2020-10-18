package com.tcl.uf.tangram.enums;

/**
 * @author zhongfk on 2020/8/22.
 * @version 1.0
 */
public enum TemplateEnums {


    //服务首页
    TAB_INTEGRAL("积分明细"),
    TAB_AFTERSALES("售后服务"),
    TAB_TABCONTENTS("文章板块列表"),

    //图文详情页
    ARTICLE_DETAIL("aticleDetail"),
    ARTICLE_RAAND_lIST("articleList"),

    POINT_DETAIL("integralDetail"),
    //总积分 以及vip等级
    POINT_INTEGRALANDlV("integral"),
    POINT_ALL_ACTION("allAction"),
    POINT_RECEVICE_ACTION("receviceAction"),
    POINT_PAY_ACTION("payAction"),

    MEMBER_RULES("会员规则"),

    //订单列表
    ORDER_LOAD_ACTION("loadAction"),
    ORDER_COMPLATE_ACTION("completeAction"),
    ORDER_RECORD_ACTION("allRecordAction"),
    ORDER_lIST("orderList"),

    //优惠券
    COUPONS_INTERNAl("couponInternal"),
    COUPONS_SHOP("couponShop"),
    COUPONS_LIST("couponList"),

    //广告位
    COMMON_ADVERT("advert"),


    //积分商城
    SHOP_GOODS_BANNER("shopBanner"),
    SHOP_HOT_ACTION("hotAction"),
    SHOP_PRICE_ACTION("priceAction"),
    SHOP_LATEST_ACTION("latestAction"),
    SHOP_EXCHANGE_ACTION("exchangeAction"),
    SHOP_TOTAL_INTEGRAL("integral"),
    SHOP_GOODS_lIST("goodsList"),

    ARTICLE_lIST("文章列表"),
    ARTICLE_RAND("文章推荐列表"),
    //图文详情页
    ARTICLE_CONTENT("文章内容"),

    //积分任务
    POINT_NEWTASK("newTasks"),
    POINT_DAILYTASK("dailyTasks"),
    //积分详情
    HEAD_IMGURL("headImageURl"),
    HEAD_TITLE("headTitle"),
    HEAD_CONTENT("headContent"),
    HEAD_DESCCOM("headDescCom"),
    HEAD_ACTIONPARAMS("headActionParams"),

    CENTER_IMGURL("centerImgUrl"),
    CENTER_CONTENT1("centerContent1"),
    CENTER_CONTENT("centerContent2"),
    FOOTER_ACTIONPARAMS("footActionParams"),

    //会员等级
    MEMBER_LEVEL("会员等级"),
    MEMBER_RIGHTSET_DETAIL("权益信息"),
    MEMBER_RIGHTSET_CONDITION("获取条件"),
    MEMBER_RIGHTSET_DESC("权益介绍"),
    MEMBER_RIGHTSET_URL("权益图标地址"),
    MEMBER_RIGHTSET_JUMPURL("领取链接"),
    //权益信息
    MEMBER_RIGHTSET("会员权益");





    private String value;

    public String getValue() {
        return value;
    }

    TemplateEnums(String value) {
        this.value = value;
    }
}
