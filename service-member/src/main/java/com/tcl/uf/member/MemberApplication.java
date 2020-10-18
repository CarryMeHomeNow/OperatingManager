package com.tcl.uf.member;

import com.tcl.uf.common.base.manage.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author youyun.xu
 * @ClassName: MemberApplication
 * @Description: 会员权益管理(生日礼包、积分权益、津贴、清洗)
 * @date 2020/7/30 9:31
 */
@SpringBootApplication(scanBasePackages = "com.tcl")
@EnableEurekaClient
@EntityScan(basePackages = {"com.tcl.commondb.member.model"})
@EnableJpaRepositories(basePackages = {"com.tcl.commondb.member.repository"})
@EnableFeignClients(basePackages= {"com.tcl.commonservice.service"})
public class MemberApplication {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }

    @Bean
    public RedisManager redisManager() {
        return new RedisManager(redisTemplate);
    }

}