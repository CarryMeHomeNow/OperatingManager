package com.tcl.uf.management.config;

import com.tcl.uf.common.base.interceptor.RedisRockInterceptor;
import com.tcl.uf.common.base.util.redis.RedisLockBoot;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
//    @Bean
//    public RedisRockInterceptor redisRockInterceptor(){
//        return new RedisRockInterceptor();
//    }
//    @Bean(initMethod = "initSingleServer",destroyMethod = "destroy")
//    public RedisLockBoot redisLockBoot(){
//        RedisLockBoot lockBoot = new RedisLockBoot();
//        lockBoot.setHost(host+":"+port);
//        return lockBoot;
//    }

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxTotal(300);
        // 设置最大空闲数
        jedisPoolConfig.setMaxIdle(8);
        // 设置最大等待时间
        jedisPoolConfig.setMaxWaitMillis(1000 * 100);
        // 在BORROW一个JEDIS实例时，是否需要验证，若为true，则所有JEDIS实例均是可用的
        jedisPoolConfig.setTestOnBorrow(true);
        if(StringUtils.isNotEmpty(password) && (!password.startsWith("${spring"))){
            return new JedisPool(jedisPoolConfig, host,port, 3000,password);
        }
        return new JedisPool(jedisPoolConfig, host,port, 3000);
    }

}
