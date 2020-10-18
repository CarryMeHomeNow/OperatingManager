package com.tcl.uf.common.base.constant;

/**
 * @author gaofan
 * @date 2020/8/27 16:43
 * uf
 */
public class MessageConstant {

    private MessageConstant() {
    }
    /**
     * 后台用户未登录
     */
    public static final String MSG_USER_ERROR  = "请先登录";

    /**
     * 为获取到数据
     */
    public static final String MSG_NULL_OBJECT = "无数据";
    /**
     * app根据ssoid未获取到用户信息
     */
    public static final String MSG_NULL_SSOID =  "未找到用户信息";
    /**
     * 会员权益数据校验
     */
    public static final String MSG_NULL_RIGHTS = "会员权益数据不完整";

    public static final String MSG_FINDBYID_NULL_RIGHTS = "根据id未找到权益信息";
    /**
     * 会员等级规则数据校验
     */
    public static final String MSG_NULL_GRADE = "会员等级数据不完整";
}
