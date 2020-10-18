package com.tcl.uf.management.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: SsoConfiguration
 * @Description:
 * @date 2018/9/23 下午6:59
 */

@Component
@ConfigurationProperties(prefix = "sso")
public class SsoConfiguration {

    private String appcode;

    private String appkey;

    private String host;

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
