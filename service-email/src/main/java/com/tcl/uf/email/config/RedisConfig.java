package com.tcl.uf.email.config;

import com.tcl.uf.common.base.interceptor.RedisRockInterceptor;
import com.tcl.uf.common.base.util.redis.RedisLockBoot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;
    @Bean
    public RedisRockInterceptor redisRockInterceptor(){
        return new RedisRockInterceptor();
    }
    @Bean(initMethod = "initSingleServer",destroyMethod = "destroy")
    public RedisLockBoot redisLockBoot(){
        RedisLockBoot lockBoot = new RedisLockBoot();
        lockBoot.setHost(host+":"+port);
        return lockBoot;
    }

}
