package com.tcl.uf.gateway.cache;

/**
 * @Desc : cache config
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/29 14:05
 */
public class CacheConfig {

    /**
     * 缓存类型
     * @see com.tcl.uf.gateway.config.GatewayConstVal
     */
    private String cacheType;

    /**
     * 缓存时长，单位s
     */
    private Integer gwAge;

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    public Integer getGwAge() {
        return gwAge;
    }

    public void setGwAge(Integer gwAge) {
        this.gwAge = gwAge;
    }
}
