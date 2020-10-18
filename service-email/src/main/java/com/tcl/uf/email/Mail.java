package com.tcl.uf.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动入口
 * 
 * @ClassName: Mail
 * @Description:
 * @author heyy@kuyumall.com
 * @date 2018年1月9日 上午11:29:27
 */
@SpringBootApplication(scanBasePackages = "com.tcl")
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.tcl.commonservice.service"})
@EntityScan(basePackages = {"com.tcl.uf.email.dto"})
@EnableJpaRepositories(basePackages = {"com.tcl.uf.email.repository"})
@ComponentScan(basePackages = {"com.tcl.uf.common.base.**","com.tcl.uf.email.**"})
public class Mail {
    public static void main(String[] args) {
        SpringApplication.run(Mail.class, args);
    }
}
