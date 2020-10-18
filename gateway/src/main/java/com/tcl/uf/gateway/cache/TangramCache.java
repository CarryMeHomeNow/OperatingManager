package com.tcl.uf.gateway.cache;

import com.tcl.commondb.tangram.model.TangramTemplateConfig;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Desc : some local cache
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/24 20:16
 */
@Component
public class TangramCache {

    /**
     * key-tangramCode value-instance
     */
    private final Map<String, TangramTemplateConfig> tangramConfigMap = new ConcurrentHashMap<>(16);

    /**
     * key-cache config k value-cacheServiceImpl
     */
    private final Map<String, CacheConfig> cacheConfigMap = new ConcurrentHashMap<>(16);

    public TangramTemplateConfig getTangramConfigByTangramCode(String tangramCode) {
        return this.tangramConfigMap.get(tangramCode);
    }

    public void putTangramConfig(String tangramCode, TangramTemplateConfig tangramTemplateConfig) {
        this.tangramConfigMap.put(tangramCode, tangramTemplateConfig);
    }

    public Map<String, TangramTemplateConfig> getTangramCache() {
        return this.tangramConfigMap;
    }

    /**
     * 注册缓存
     * @param key 自定义，注册的key和使用时的key需保持一致
     * @param cacheVo 缓存配置
     */
    public void registerCache(String key, CacheConfig cacheVo) {
        cacheConfigMap.put(key, cacheVo);
    }

    public CacheConfig getCacheConfig(String key) {
        return cacheConfigMap.get(key);
    }
}
