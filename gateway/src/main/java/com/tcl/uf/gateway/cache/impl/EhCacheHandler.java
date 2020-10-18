package com.tcl.uf.gateway.cache.impl;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @Desc : EhCache
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/30 23:09
 */
@Service
public class EhCacheHandler {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ResourcePools resourcePools;

    public Object query(String cacheConfigKey, String cacheKey) {
        Cache cache = cacheManager.getCache(cacheConfigKey, String.class, String.class);
        return cache.get(cacheKey);
    }

    public void createCache(String cacheConfigKey, Long expiredAge) {
        // withExpiry() 设置信息在缓存中的存放的时间，有两种：1、TTL缓存自创建之时起至失效时的时间间隔；2、TTI缓存创建以后，最后一次访问缓存之时至失效之时的时间间隔
        CacheConfiguration configuration = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, resourcePools)
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(expiredAge))).build();

        cacheManager.createCache(cacheConfigKey, configuration);
    }

    public void put(String cacheConfigKey, String cacheKey, String value) {
        Cache cache = cacheManager.getCache(cacheConfigKey, String.class, String.class);
        cache.put(cacheKey, value);
    }

    public boolean exist(String cacheConfigKey) {
        Cache cache = cacheManager.getCache(cacheConfigKey, String.class, String.class);
        return cache == null ? false : true;
    }
}
