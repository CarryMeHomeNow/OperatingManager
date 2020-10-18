package com.tcl.uf.zuul.config;

import com.tcl.uf.zuul.repository.RouterRuleRepository;
import com.tcl.uf.zuul.route.DbRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: ZuulConfig
 * @Description: 初始化 使用 DbRouterRule 配置 Zuul
 * @date 2018/1/30 下午9:57
 */
@Configuration
public class ZuulConfig {

    @Autowired
    ZuulProperties zuulProperties;

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    RouterRuleRepository routerRuleRepository;


    @Bean
    public DbRouteLocator routeLocator() {
//        DbRouteLocator routeLocator = new DbRouteLocator(se
        // upgrade (hepeng)
        DbRouteLocator routeLocator = new DbRouteLocator(serverProperties.getServlet().getContextPath(), zuulProperties);
        routeLocator.setRouterRuleRepository(routerRuleRepository);
        return routeLocator;
    }
}
