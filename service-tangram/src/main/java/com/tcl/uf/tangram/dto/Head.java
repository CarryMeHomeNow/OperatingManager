package com.tcl.uf.tangram.dto;

/**
 * @author zhongfk on 2020/8/17.
 * @version 1.0
 * 请求头参数
 */
public class Head {
    /**
     * 平台类别 默认值：platform_tcl_shop（2C商城）
     */
    private String platform;

    /**
     * 商户ID
     */
    private String storeUuid;

    /**
     * 租户id t-id
     */
    private String tId;

    /**
     * 终端类型 默认01
     */
    private String terminalType;

    /**
     * 用户登录token
     */
    private String accessToken;

}
