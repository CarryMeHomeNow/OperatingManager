package com.tcl.uf.content.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author youyun.xu
 * @ClassName: OfficialMallConfiguration
 * @Description: 电商系统配置(外部系统)
 * @date 2020/8/14 15:40
 */
@Configuration
public class OfficialMallConfiguration {
    @Value("${es-associate.official-mall.url}")
    private String requestUrl;

    @Value("${es-associate.official-mall.username}")
    private String username;

    @Value("${es-associate.official-mall.password}")
    private String password;

    @Value("${es-associate.official-mall.t-id}")
    private String tId;

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }
}
