package com.tcl.uf.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author youyun.xu
 * @ClassName: SchedulerApplication
 * @Description: 调度管理微服务
 * @date 2020/8/12 上午18:00
 */
@SpringBootApplication(scanBasePackages = "com.tcl")
@EnableEurekaClient
@ComponentScan(basePackages = {"com.tcl.uf.scheduler.**"})
@EnableFeignClients(basePackages = {"com.tcl.commonservice.service"})
@EnableScheduling
public class SchedulerApplication {

    public static void main(String[] args) {
        System.out.println("触发重启");
        SpringApplication.run(SchedulerApplication.class, args);
    }
}
