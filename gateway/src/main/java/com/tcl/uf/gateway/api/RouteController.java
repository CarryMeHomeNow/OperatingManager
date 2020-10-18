package com.tcl.uf.gateway.api;

import com.tcl.uf.gateway.cache.TangramCache;
import com.tcl.uf.gateway.init.GatewayInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc : ROUTE API
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/27 19:31
 */
@RestController
@RequestMapping("/gateway/route")
public class RouteController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TangramCache tangramCache;

    @Autowired
    private GatewayInitializer gatewayInitializer;

    /**
     * 重载（全部）
     */
    @RequestMapping("/reload")
    public void reLoad() {
        tangramCache.getTangramCache().clear();
        try {
            gatewayInitializer.run(null);
        } catch (Exception e) {
            // TODO
//            e.printStackTrace();
            logger.error(e.getMessage());
            return;
        }
        logger.info("SUCCESS");
    }

    @RequestMapping("/delete")
    public void deleteRoute() {
        // TODO
    }

    @RequestMapping("/save")
    public void saveRoute() {
        // TODO
    }

}
