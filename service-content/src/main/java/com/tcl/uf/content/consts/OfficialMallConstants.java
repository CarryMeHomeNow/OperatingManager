package com.tcl.uf.content.consts;

/**
 * @author youyun.xu
 * @ClassName: OfficialMallConstants
 * @Description: 文章内容管理
 * @date 2020/8/14 16:12
 */
public class OfficialMallConstants {

    //响应码
    public  static String RESPONSE_CODE = "200";

    //优惠券(发放优惠券、查询优惠券)
    public  static String SEND_COUPON_URL ="/openapi/shop/tclplus/promotion/coupontype/sendCoupon";
    public  static String COUPONTYPE_QUERYLIST_URL ="/openapi/shop/tclplus/promotion/coupontype/list";

    //商品信息(查询商品列表、商品详情、分类)
    public  static String PRODUCT_DETAIL_URL ="/openapi/shop/tclplus/product/detail";
    public  static String PRODUCT_LIST_URL ="/openapi/shop/tclplus/product/list";
    public  static String CATEGORY_LIST_URL ="/openapi/shop/product/category/list";

    //登录鉴权(公钥、令牌)
    public  static String SECURITY_CODE_URL ="/openapi/auth/securityCode";
    public  static String LOGIN_TOKEN_URL ="/openapi/auth/login";

    //缓存键值
    public static final String OFFICIALOAUTH_TOKEN_CACHE = "OFFICIALOAUTH_TOKEN_CACHE";
}


