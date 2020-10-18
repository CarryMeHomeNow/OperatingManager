package com.tcl.uf.common.base.constant;

/**
 * @author gaofan
 * @date 2020/8/27 10:37
 * rides key名称
 */
public class RedisConstant {

    private RedisConstant() {}
    /**
     * 会员等级列表
     */
    public static final String REDIS_MEMBER_GRADES = "tclPlus:member:grades";

    /**
     * app最新版本更新
     */
    public static final String REDIS_VERSION_NEW = "tclPlus:version:new";

    /**
     * 会员权益列表
     */
    public static final String REDIS_MEMBER_RIGHTS_LIST = "tclPlus:member:rights";
}
