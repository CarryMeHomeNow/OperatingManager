package com.tcl.uf.gateway.cache;

import com.tcl.uf.gateway.cache.impl.EhCacheHandler;
import com.tcl.uf.gateway.cache.impl.RedisHandler;
import com.tcl.uf.gateway.config.GatewayConstVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Desc : 缓存策略处理
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/29 13:50
 */
@Component
public class GatewayCacheHandler {

    @Autowired
    private TangramCache tangramCache;

    @Autowired
    private EhCacheHandler ehCacheHandler;

    @Autowired
    private RedisHandler redisHandler;

    /**
     * cache strategy
     *
     * @param cacheConfigKey 缓存配置的key
     * @param cacheKey       cache key
     * @return result
     */
    public Object query(String cacheConfigKey, String cacheKey) {
        CacheConfig cacheConfig = tangramCache.getCacheConfig(cacheConfigKey);
        String cacheType = cacheConfig.getCacheType();

        // EhCache
        if (GatewayConstVal.CACHE_EHCACHE.equals(cacheType)) {
            return ehCacheHandler.query(cacheConfigKey, cacheKey);
        }

        // Redis
        if (GatewayConstVal.CACHE_REDIS.equals(cacheType)) {
            return redisHandler.query(cacheKey);
        }

        return null;
    }

    public void put(String cacheConfigKey, String cacheKey, String value) {
        CacheConfig cacheConfig = tangramCache.getCacheConfig(cacheConfigKey);
        String cacheType = cacheConfig.getCacheType();

        // EhCache
        if (GatewayConstVal.CACHE_EHCACHE.equals(cacheType)) {
            if (!ehCacheHandler.exist(cacheConfigKey)) {
                ehCacheHandler.createCache(cacheConfigKey, cacheConfig.getGwAge().longValue());
            }
            ehCacheHandler.put(cacheConfigKey, cacheKey, value);
        }

        // Redis
        if (GatewayConstVal.CACHE_REDIS.equals(cacheType)) {
            redisHandler.put(cacheKey, value);
        }
    }

    public void remove(String key) {

    }
}
