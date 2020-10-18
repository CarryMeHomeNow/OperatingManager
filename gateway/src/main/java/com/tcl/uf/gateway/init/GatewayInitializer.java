package com.tcl.uf.gateway.init;

import com.tcl.uf.gateway.route.RoutePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Desc : 网关初始化工作
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/24 19:53
 */
@Component
public class GatewayInitializer implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private List<GatewayInitService> gatewayInitServices;

    @Autowired
    private RoutePublisher routePublisher;

    @Override
    public void run(String... args) throws Exception {
        logger.info("==================gateway init start ......=================");

        for(GatewayInitService gatewayInitService : gatewayInitServices) {
            gatewayInitService.init();
        }

        routePublisher.publishCustomRoutesLoad(this);

        logger.info("==================gateway init end ......=================");
    }
}

