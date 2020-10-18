package com.tcl.commonservice.service.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author youyun.xu
 * @ClassName: FeignConfiguration
 * @Description: Feign 转发请求头(header参数)
 * @date 2020/8/27 11:09
 */
@Configuration
public class FeignConfiguration implements RequestInterceptor {
    private static final Logger _log = LoggerFactory.getLogger(FeignConfiguration.class);

    /**
     *使用说明：在@FeignClient注解里面的属性加上configuration = FeignConfiguration.class
     *使用示例：@FeignClient(name = "service-activity",  configuration = FeignConfiguration.class)
     */
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                template.header(name, values);
            }
            _log.info("feign interceptor header:{}", template);
        }
    }
}
