package com.tcl.uf.zuul;

import com.tcl.uf.common.base.manage.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



@EnableZuulProxy
@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.tcl.commonservice.service"})
@EnableJpaRepositories(basePackages = {"com.tcl.uf.zuul.repository"})
@EntityScan(basePackages = {"com.tcl.uf.zuul.model"})
public class ZuulApplication {

    @Value("${spring.profiles.active}")
    private String active;

    @Autowired
    StringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
    
    @Bean
    public RedisManager redisManager() {
        return new RedisManager(redisTemplate);
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
//        if (!active.equals("prod")) {
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addExposedHeader("Content-Type");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("Accept");
        config.addExposedHeader("Origin");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
//        }
        return new CorsFilter(source);
    }

}
