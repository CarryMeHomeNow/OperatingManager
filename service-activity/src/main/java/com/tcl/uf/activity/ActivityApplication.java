package com.tcl.uf.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author youyun.xu
 * @ClassName: ActivityApplication
 * @Description: 活动管理微服务
 * @date 2020/7/21 上午09:00
 */
@SpringBootApplication(scanBasePackages = "com.tcl")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.tcl.commonservice.service"})
@EntityScan(basePackages = {"com.tcl.commondb.activity.model"})
@EnableJpaRepositories(basePackages = {"com.tcl.commondb.activity.repository"})
public class ActivityApplication {

    public static void main(String[] args) {
        System.out.print("触发重启");
        SpringApplication.run(ActivityApplication.class, args);
    }
}
