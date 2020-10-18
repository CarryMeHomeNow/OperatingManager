package com.tcl.uf.gateway.route;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * @Desc : route load interface
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/26 16:09
 */
public interface RouteLoadService {

    /**
     * 初始化路由
     * @return 路由定义集合
     */
    List<RouteDefinition> load();
}
