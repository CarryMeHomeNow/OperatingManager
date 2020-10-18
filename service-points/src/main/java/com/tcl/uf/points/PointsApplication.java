package com.tcl.uf.points;

import com.tcl.uf.common.base.manage.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication(scanBasePackages = "com.tcl")
@EnableFeignClients(basePackages = {"com.tcl.commonservice.service"})
@EntityScan(basePackages = {"com.tcl.commondb.points.model"})
@EnableJpaRepositories(basePackages = {"com.tcl.commondb.points.repository"})
@ComponentScan(basePackages = {"com.tcl.uf.common.base.**","com.tcl.uf.points.**"})
public class PointsApplication {
    @Autowired
    StringRedisTemplate redisTemplate;
    public static void main(String[] args) {
        SpringApplication.run(PointsApplication.class, args);
    }

    @Bean
    public RedisManager redisManager() {
        return new RedisManager(redisTemplate);
    }
}
