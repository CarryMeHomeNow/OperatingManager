package com.tcl.uf.common.base.constant;

public enum  CommonErrorConstant {
    /**                    ----公共                                */
    COMMON_SUCCESS(1,"成功"),
    COMMON_FAIL(-1,"失败"),
    COMMON_ERROR(100001,"服务器内部错误"),
    COMMON_DATA_MISSING(100002,"数据缺失"),
    COMMON_REQ_MISSING(100003,"参数缺失"),

    /**                    ----广告advert:11****                   */
    ADVERT_CODE_MISSING(110101,"广告组编码不存在"),


    /**                    ----活动activity:12****                 */
    ACTIVITY_xxx(120001,"xxx"),



    /**                    ----网关zuul/gateway:13****             */


    /**                    ----商城shop:14****                     */
    SHOP_QUERY_LIST(140001,"获取列表失败"),
    SHOP_QUERY_DETAIL(140002,"获取商品详情失败"),

    /**                    ----tangram:15****                      */



    /**                    ----内容content:16****                  */



    /**                    ----会员member:17****                   */




    /**                    ----积分point:18****                    */
    POINT_EXCHANGE_COUPON_MISSING(180001,"积分兑换券不存在"),
    POINT_NOT_ENOUGH(180002,"积分余额不足"),
    POINT_EXCHANGE_NUM_MAX(180003,"积分兑换券次数已达上限"),
    POINT_EXCHANGE_FAIL(180004,"兑换失败"),

    /**                    ----应用版本version:19****               */
    VERSION_NOT_SCORE(190001,"请评分"),
    VERSION_NOT_FEEDBACK(190002,"请输入反馈意见"),


    /**                    ----运营管理management:20****            */



    FINLISH(1,"");
    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    CommonErrorConstant(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }
}
