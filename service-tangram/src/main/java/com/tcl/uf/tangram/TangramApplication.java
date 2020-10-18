package com.tcl.uf.tangram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Desc :  tangram静态化管理
 * @Author Mr.HePeng
 * @Date 2020/8/11 17:54
 */
@SpringBootApplication(scanBasePackages = "com.tcl")
@EnableEurekaClient
@EntityScan(basePackages = {"com.tcl.commondb.tangram.model"})
@EnableJpaRepositories(basePackages = {"com.tcl.commondb.tangram.repository"})
@ComponentScan(basePackages = {"com.tcl.uf.common.base.util.spring","com.tcl.uf.tangram.**"})
@EnableFeignClients(basePackages= {"com.tcl.commonservice.service","com.tcl.uf.tangram.**"})
public class TangramApplication {

    public static void main(String[] args) {
        SpringApplication.run(TangramApplication.class, args);
    }

}
