package com.tcl.uf.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author youyun.xu
 * @ClassName: ContentApplication
 * @Description: 內容管理微服务
 * @date 2020/7/23 上午09:00
 */
@SpringBootApplication(scanBasePackages = "com.tcl")
@EnableEurekaClient
@EntityScan(basePackages = {"com.tcl.commondb.content.model"})
@EnableJpaRepositories(basePackages = {"com.tcl.commondb.content.repository"})
@ComponentScan(basePackages = {"com.tcl.uf.content.**"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class, args);
    }
}
