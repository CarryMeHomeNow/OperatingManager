package com.tcl.uf.gateway.route;

import com.tcl.uf.gateway.event.CustomRoutesLoadEvent;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @Desc : route publisher
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/27 19:05
 */
@Service
public class RoutePublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishRoutes(Object source) {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(source));
    }

    public void publishCustomRoutesLoad(Object source) {
        this.applicationEventPublisher.publishEvent(new CustomRoutesLoadEvent(source));
    }

    public void publishCustomRoutesLoad(CustomRoutesLoadEvent customRoutesLoadEvent) {
        this.applicationEventPublisher.publishEvent(customRoutesLoadEvent);
    }
}
