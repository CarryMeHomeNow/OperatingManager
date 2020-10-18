package com.tcl.uf.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Desc : 启动类
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/20 11:00
 */
@SpringBootApplication(scanBasePackages = "com.tcl")
@EntityScan(basePackages = {"com.tcl.commondb.tangram.model"})
@EnableEurekaClient
@EnableJpaRepositories(basePackages = {"com.tcl.commondb.tangram.repository"})
@ComponentScan(basePackages = {"com.tcl.uf.common.base.util.spring","com.tcl.uf.gateway.**"})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
