package com.tcl.uf.version;

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
 * @ClassName: VersionApplication
 * @Description: 应用版本管理
 * @date 2020/8/12 9:31
 */
@SpringBootApplication(scanBasePackages = "com.tcl")
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.tcl.commonservice.service"})
@EntityScan(basePackages = {"com.tcl.commondb.version.model"})
@EnableJpaRepositories(basePackages = {"com.tcl.commondb.version.repository"})
@ComponentScan(basePackages = {"com.tcl.uf.version.**"})
public class VersionApplication {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(VersionApplication.class, args);
    }

    @Bean
    public RedisManager redisManager() {
        return new RedisManager(redisTemplate);
    }
}