package com.tcl.uf.shop.consts;

public class ShopConstants {
    //定义商品模块常量
    public static final String COME_FROM_GOODS = "goods";
    public static final String GOODS_UP = "上架";
    public static final String GOODS_DOWN = "下架";
    public static final String GOODS_DELETE = "删除";

    public static final Integer ORDER_STATUS_UNPAY          = 0;  //待付款
    public static final Integer ORDER_STATUS_WAITDELIVERED  = 1;  //等待卖家发货
    public static final Integer ORDER_STATUS_DELIVERED      = 2;  //已发货（待收货确认）
    public static final Integer ORDER_STATUS_SUCCESSED      = 3;  //交易成功
    public static final Integer ORDER_STATUS_CLOSED         = 4;  //交易关闭（买家取消,退款完成或卖家关闭交易）
    public static final Integer ORDER_STATUS_CANCELED       = 5;  //暂时没用上
    public static final Integer ORDER_STATUS_REFUNDING      = 6;  //退款处理中
    public static final Integer ORDER_STATUS_SERVICING      = 7;  //售后处理中
    public static final Integer ORDER_STATUS_REFUNDED       = 8;  //已退款

    public static final String GOODS_TIP_NOID ="商品ID不能为空!";
    public static final String ORDER_TIP_UNNUM ="购买商品数量不能为空!";
    public static final String ORDER_TIP_ERRPRICE ="订单金额有误!";
    public static final String ORDER_TIP_UNBUYER ="购买人不能为空!";
    public static final String ORDER_TIP_UNBUYERTEL ="购买人电话不能为空!";
    public static final String ORDER_TIP_UNINCOICETEL ="发票电话不能为空!";
    public static final String ORDER_TIP_ERRMOBILE ="手机号格式不正确!";
    public static final String ORDER_TIP_NULLADDR ="收货地址不能为空!";
    public static final String ORDER_TIP_LIMITNUM ="抱歉，购买数量不能超过限购数量!";
    public static final String ORDER_TIP_MINPRICE ="订单金额不能小于最低成交金额!";
    public static final String ORDER_TIP_ERRSTORGE ="抱歉,该商品库存不足!";
    public static final String ORDER_TIP_ERRTCODE ="抱歉该T卡是无效，已被使用完!";
    public static final String ORDER_TIP_SYSBUSY ="抱歉系统繁忙，请稍后再试!";
    public static final String ORDER_TIP_NOOREDERID ="订单ID不能为空!";
    public static final String ORDER_TIP_NOOREDERSN ="订单号不能为空!";
    public static final String ORDER_TIP_ERROREDERSN ="无效订单号!";
    public static final String ORDER_TIP_ORDERNOUSER ="订单所属用户异常!";
    public static final String ORDER_TIP_UNORDER ="抱歉，没有找到相关订单信息!";
    public static final String ORDER_TIP_NOOREDERSTT ="订单状态值不能为空!";
    public static final String ORDER_STATUS_UNNOPAY ="目前订单不是待支付状态!";
}
