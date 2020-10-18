package com.tcl.uf.gateway.route.impl;

import com.tcl.commondb.tangram.model.TangramTemplateConfig;
import com.tcl.uf.gateway.cache.TangramCache;
import com.tcl.uf.gateway.config.GatewayConstVal;
import com.tcl.uf.gateway.route.RouteLoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @Desc : tangram routes load
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/26 16:14
 */
@Service
public class TangramRouteLoadServiceImpl implements RouteLoadService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TangramCache tangramCache;

    @Override
    public List<RouteDefinition> load() {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();

        Collection<TangramTemplateConfig> tangramTemplateConfigs = tangramCache.getTangramCache().values();

        if (tangramTemplateConfigs.isEmpty()) {
            logger.warn("tangram无模板配置信息！");
            return routeDefinitions;
        }

        for (TangramTemplateConfig tangramConfig : tangramTemplateConfigs) {
            RouteDefinition routeDefinition = new RouteDefinition();

            PredicateDefinition pathPredicate = new PredicateDefinition();
            pathPredicate.setName("Path");
            pathPredicate.addArg("pattern", tangramConfig.getRequestPath());

            PredicateDefinition reqCodePredicate = new PredicateDefinition();
            reqCodePredicate.setName("Query");
            reqCodePredicate.addArg("param", "reqCode");
            reqCodePredicate.addArg("regexp", tangramConfig.getTangramCode());

            PredicateDefinition versionPredicate = new PredicateDefinition();
            versionPredicate.setName("Query");
            versionPredicate.addArg("param", "version");
            versionPredicate.addArg("regexp", tangramConfig.getVersion());

            routeDefinition.setPredicates(Arrays.asList(reqCodePredicate, versionPredicate));

            FilterDefinition filterDefinition = new FilterDefinition();
            filterDefinition.setName("RewritePath");
            filterDefinition.addArg("regexp", tangramConfig.getRequestPath());
            filterDefinition.addArg("replacement", tangramConfig.getRoutePath());

            routeDefinition.setFilters(Arrays.asList(filterDefinition));

            routeDefinition.setId(tangramConfig.getTangramCode() + GatewayConstVal.SEPARATOR + tangramConfig.getVersion());

            try {
                routeDefinition.setUri(new URI(tangramConfig.getRouteServe()));
            } catch (URISyntaxException e) {
                // TODO
                e.printStackTrace();
            }
            routeDefinitions.add(routeDefinition);
        }

        return routeDefinitions;
    }
}
