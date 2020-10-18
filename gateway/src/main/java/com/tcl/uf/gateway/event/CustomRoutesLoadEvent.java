package com.tcl.uf.gateway.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Desc : load routes event
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/27 16:30
 */
public class CustomRoutesLoadEvent extends ApplicationEvent {

    public CustomRoutesLoadEvent(Object source) {
        super(source);
    }
}
