package com.tcl.uf.zuul.route;

import com.tcl.uf.zuul.manager.ZuulCache;
import com.tcl.uf.zuul.model.RouterRuleV;
import com.tcl.uf.zuul.repository.RouterRuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: DbRouteLocator
 * @Description:
 * @date 2018/1/30 下午8:29
 */

public class DbRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {


    private static final Logger logger = LoggerFactory.getLogger(DbRouteLocator.class);

    private RouterRuleRepository routerRuleRepository;

    public void setRouterRuleRepository(RouterRuleRepository routerRuleRepository) {
        this.routerRuleRepository = routerRuleRepository;
    }

    private ZuulProperties properties;

    public DbRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();

        // 配置文件中获取配置
        routesMap.putAll(super.locateRoutes());

        // DB表中获取配置
        routesMap.putAll(dbRoutes());

        ZuulCache.cache(routesMap);
        return routesMap;
    }

    /**
     * 从数据库中路由信息
     *
     * @return
     */
    private Map<String, ZuulProperties.ZuulRoute> dbRoutes() {

        LinkedHashMap<String, ZuulProperties.ZuulRoute> dbRoutesMap = new LinkedHashMap<>();
        List<RouterRuleV> all = routerRuleRepository.findAll();

        if (all == null || all.isEmpty()) {
            return dbRoutesMap;
        }

        for (RouterRuleV item : all) {

            String serviceId = item.getServiceId();
            if ("local".equals(serviceId)||"thirdpartyjoin".equals(serviceId)) {
                continue;
            }

            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            zuulRoute.setPath(item.getPath());
            zuulRoute.setServiceId(item.getServiceId());
            zuulRoute.setId(item.getServiceId());

            dbRoutesMap.put(item.getPath(), zuulRoute);
        }

        /**
         * 数据库路由规则缓存内存
         */
        ZuulCache.setRoutRuleMap(all);
        return dbRoutesMap;
    }
}
