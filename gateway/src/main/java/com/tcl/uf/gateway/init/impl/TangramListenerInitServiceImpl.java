package com.tcl.uf.gateway.init.impl;

import com.tcl.uf.gateway.config.GatewayConstVal;
import com.tcl.uf.gateway.init.GatewayInitService;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

/**
 * @Desc : tangram监听器初始化
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/24 20:21
 */
@Service
public class TangramListenerInitServiceImpl implements GatewayInitService, Ordered {

    @Override
    public void init() {
        System.out.println("===TangramListenerInitServiceImpl");
    }

    @Override
    public int getOrder() {
        return GatewayConstVal.CACHE_LISTENER_INIT_ORDER;
    }
}
