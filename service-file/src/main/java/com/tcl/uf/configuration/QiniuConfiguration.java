package com.tcl.uf.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author youyun.xu
 * @ClassName: QiniuConfiguration
 * @Description:
 * @date 2020/07/22 下午14:40
 */
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiniuConfiguration {

    private String accesskey;
    private String secretkey;
    private String publicBucket;
    private String publicHostname;
    private String privateBucket;
    private String privateHostname;
    private long privateUptokenExpire;

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public String getPublicBucket() {
        return publicBucket;
    }

    public void setPublicBucket(String publicBucket) {
        this.publicBucket = publicBucket;
    }

    public String getPrivateBucket() {
        return privateBucket;
    }

    public void setPrivateBucket(String privateBucket) {
        this.privateBucket = privateBucket;
    }

    public String getPublicHostname() {
        return publicHostname;
    }

    public void setPublicHostname(String publicHostname) {
        this.publicHostname = publicHostname;
    }

    public String getPrivateHostname() {
        return privateHostname;
    }

    public void setPrivateHostname(String privateHostname) {
        this.privateHostname = privateHostname;
    }

    public long getPrivateUptokenExpire() {
        return privateUptokenExpire;
    }

    public void setPrivateUptokenExpire(long privateUptokenExpire) {
        this.privateUptokenExpire = privateUptokenExpire;
    }
}
