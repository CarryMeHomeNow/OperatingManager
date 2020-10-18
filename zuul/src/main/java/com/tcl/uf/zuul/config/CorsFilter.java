/**
 * 
 */
package com.tcl.uf.zuul.config;

/**
 * @describe 跨域设置
 * @author zhikai.chen
 * @date 2018年6月26日 下午2:41:41
 */

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {  
  
    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);  
 
    public void doFilter(ServletRequest req, ServletResponse res,  FilterChain chain) throws IOException, ServletException {  
        HttpServletResponse response = (HttpServletResponse) res;  
        // TODO 支持跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,ubtoken");
        response.setHeader("Access-Control-Expose-Headers", "*");
        response.setHeader("contentType", "application/x-www-form-urlencoded;charset=UTF-8");
        logger.debug("过滤器被使用 -> "+req.getRemoteHost());
        chain.doFilter(req, res);  
    }  
    public void init(FilterConfig filterConfig) {}  
    public void destroy() {}  
}  
