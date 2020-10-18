package com.tcl.uf.gateway.config;

import com.tcl.uf.gateway.exception.GatewayExceptionHandler;
import org.ehcache.CacheManager;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.stream.Collectors;

/**
 * @Desc : Gateway Config
 * @Author Mr.HePeng
 * @Date 2020/8/4 18:19
 */
@Configuration
@EnableConfigurationProperties({ServerProperties.class, ResourceProperties.class})
public class GatewayConfig {

    @Value("${ehcache.resource.heap.size}")
    private Long ehCacheHeapSize;

    @Value("${enable.tangram.default.data}")
    private boolean enableTangramDefaultData;

    private final ServerProperties serverProperties;

    public GatewayConfig(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Bean
    @ConditionalOnMissingBean(value = ErrorWebExceptionHandler.class, search = SearchStrategy.CURRENT)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes,
                                                             ResourceProperties resourceProperties, ObjectProvider<ViewResolver> viewResolvers,
                                                             ServerCodecConfigurer serverCodecConfigurer, ApplicationContext applicationContext) {
        GatewayExceptionHandler exceptionHandler = new GatewayExceptionHandler(errorAttributes,
                resourceProperties, this.serverProperties.getError(), applicationContext);
        exceptionHandler.setViewResolvers(viewResolvers.orderedStream().collect(Collectors.toList()));
        exceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        exceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());

        // 测试用
        exceptionHandler.setEnableDefaultTangramData(enableTangramDefaultData);

        return exceptionHandler;
    }

    @Bean
    @ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
    public DefaultErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes();
    }

    @Bean
    public CacheManager cacheManager() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
        return cacheManager;
    }

    @Bean
    public ResourcePoolsBuilder resourcePoolsBuilder() {
        return ResourcePoolsBuilder.newResourcePoolsBuilder();
    }

    @Bean
    public ResourcePools resourcePools(ResourcePoolsBuilder resourcePoolsBuilder) {
        return resourcePoolsBuilder.heap(ehCacheHeapSize, MemoryUnit.MB).build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}