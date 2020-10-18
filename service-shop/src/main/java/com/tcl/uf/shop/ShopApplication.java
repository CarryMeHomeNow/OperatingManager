package com.tcl.uf.shop;

import com.tcl.uf.common.base.manage.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author maym
 * @ClassName: ShopApplication
 * @Description: 积分商城
 * @date 2020/7/30 9:31
 */
@SpringBootApplication(scanBasePackages = "com.tcl")
@EnableEurekaClient
@EntityScan(basePackages = {"com.tcl.commondb.shop.model"})
@EnableJpaRepositories(basePackages = {"com.tcl.commondb.shop.repository"})
@EnableFeignClients(basePackages = {"com.tcl.commonservice.service"})
@ComponentScan(basePackages = {"com.tcl.uf.common.base.**","com.tcl.uf.shop.**"})
public class ShopApplication {

    @Autowired
    StringRedisTemplate redisTemplate;
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
    @Bean
    public RedisManager redisManager() {
        return new RedisManager(redisTemplate);
    }

}