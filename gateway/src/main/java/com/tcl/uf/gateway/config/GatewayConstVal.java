package com.tcl.uf.gateway.config;

/**
 * @Desc : constant val
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/21 16:52
 */
public class GatewayConstVal {

    /** 网关状态 true-可以接入，false-不能接入 */
    public static volatile boolean GATE_WAY_IS_READY = false;

    public static final int DB_INIT_ORDER = 0;
    public static final int CACHE_INIT_ORDER = 1;
    public static final int CACHE_LISTENER_INIT_ORDER = 2;

    /** cache type 1-ehcache 2-redis 3-custom */
    public static final String CACHE_EHCACHE = "1";
    public static final String CACHE_REDIS = "2";
    public static final String CACHE_CUSTOM = "3";

    /** 0-否，1-是 */
    public static final String FALSE = "0";
    public static final String TRUE = "1";

    public static final String SEPARATOR = "|";
}
