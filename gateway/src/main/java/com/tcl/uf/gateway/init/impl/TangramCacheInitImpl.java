package com.tcl.uf.gateway.init.impl;

import com.tcl.commondb.tangram.model.TangramTemplateConfig;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.gateway.cache.CacheConfig;
import com.tcl.uf.gateway.cache.TangramCache;
import com.tcl.uf.gateway.cache.GatewayCacheHandler;
import com.tcl.uf.gateway.cache.impl.TangramServerHandler;
import com.tcl.uf.gateway.config.GatewayConstVal;
import com.tcl.uf.gateway.init.GatewayInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @Desc : tangram数据初始化
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/24 20:18
 */
@Service
public class TangramCacheInitImpl implements GatewayInitService, Ordered {

    @Autowired
    private TangramCache tangramCache;

    @Autowired
    private TangramServerHandler tangramServerHandler;

    @Autowired
    private GatewayCacheHandler gatewayCacheHandler;

    @Override
    public void init() {
        // 获取配置信息
        Collection<TangramTemplateConfig> tangramTemplateConfigs = tangramCache.getTangramCache().values();

        for (TangramTemplateConfig config : tangramTemplateConfigs) {
            if (GatewayConstVal.TRUE.equals(config.getIsGwCache())) {
                if (config.getCacheType() == null) {
                    throw new JMException("CODE:" + config.getTangramCode() + ",cacheType is null");
                }

                String cacheConfigKey = config.getTangramCode() + GatewayConstVal.SEPARATOR + config.getVersion();
                String cacheKey = config.getCacheRequestHeader() + GatewayConstVal.SEPARATOR + config.getCacheRequestBody();

                // 模板data
                String value = tangramServerHandler.invokeTangramServer(config);

                // 注册缓存配置
                CacheConfig cacheConfig = new CacheConfig();
                cacheConfig.setGwAge(config.getGwAge());
                cacheConfig.setCacheType(config.getCacheType());
                tangramCache.registerCache(cacheConfigKey, cacheConfig);

                gatewayCacheHandler.put(cacheConfigKey, cacheKey, value);
            }
        }
    }

    @Override
    public int getOrder() {
        return GatewayConstVal.CACHE_INIT_ORDER;
    }
}
