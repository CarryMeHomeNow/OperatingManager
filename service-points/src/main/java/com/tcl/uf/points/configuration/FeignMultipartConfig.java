package com.tcl.uf.points.configuration;

import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;

import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;



@Configuration
public class FeignMultipartConfig {
    @Autowired
    private ObjectFactory<HttpMessageConverters> msg;
    
    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipart(){
        return new SpringFormEncoder(new SpringEncoder(msg));
    }

    @Bean
    public  feign.Logger.Level multipartLoggerLevel(){
        return Logger.Level.FULL;
    }
}
