package com.tcl.uf.gateway.route;

import com.tcl.uf.gateway.event.CustomRoutesLoadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc : custom routes
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/24 19:44
 */
@Service
public class CustomRouteRepository implements RouteDefinitionRepository {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private List<RouteLoadService> routeLoadServices;

    @Autowired
    private RoutePublisher routePublisher;

    List<RouteDefinition> routeDefinitions = new ArrayList<>();

    private void loadRoutes() {
        for (RouteLoadService routeLoadService : routeLoadServices) {
            // Prevents thread-safety issues when reloading
            routeDefinitions = new ArrayList<>();
            routeDefinitions.addAll(routeLoadService.load());
        }
    }

    @EventListener
    public void eventLister(CustomRoutesLoadEvent customRoutesLoadEvent) {
        this.loadRoutes();
        routePublisher.publishRoutes(this);
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        logger.info("save route");
        // TODO
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        logger.info("delete route");
        // TODO
        return null;
    }

}
