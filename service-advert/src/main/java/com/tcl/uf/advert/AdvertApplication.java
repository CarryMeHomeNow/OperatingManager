package com.tcl.uf.advert;

import com.tcl.uf.common.base.manage.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author linhuimin
 * @ClassName: AdvertApplication
 * @Description: 广告投放管理
 * @date 2020/7/31 9:31
 */
@SpringBootApplication(scanBasePackages = "com.tcl")
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.tcl.commonservice.service"})
@EntityScan(basePackages = {"com.tcl.commondb.advert.model","com.tcl.commondb.management.model"})
@EnableJpaRepositories(basePackages = {"com.tcl.commondb.advert.repository","com.tcl.commondb.management.repository"})
@ComponentScan(basePackages = {"com.tcl.uf.common.base.**","com.tcl.uf.advert.**"})
public class AdvertApplication {

    @Autowired
    StringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AdvertApplication.class, args);
    }

    @Bean
    public RedisManager redisManager() {
        return new RedisManager(redisTemplate);
    }

}