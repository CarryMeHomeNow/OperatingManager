package com.tcl.uf.gateway.init.impl;

import com.tcl.commondb.tangram.model.TangramTemplateConfig;
import com.tcl.commondb.tangram.repository.TangramTemplateConfigRepository;
import com.tcl.uf.gateway.cache.TangramCache;
import com.tcl.uf.gateway.config.GatewayConstVal;
import com.tcl.uf.gateway.init.GatewayInitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desc : init data from db
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/8/27 16:58
 */
@Service
public class TangramDbInitServiceImpl implements GatewayInitService, Ordered {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TangramTemplateConfigRepository tangramTemplateConfigRepository;

    @Autowired
    private TangramCache tangramCache;

    @Override
    public void init() {
        logger.info("================开始加载tangram配置信息（from the database）......================");

        List<TangramTemplateConfig> tangramTemplateConfigs = tangramTemplateConfigRepository.findAll();

        for (TangramTemplateConfig config : tangramTemplateConfigs) {
            String uniqueKey = config.getTangramCode() + config.getVersion();

            tangramCache.putTangramConfig(uniqueKey, config);

            logger.info("模板编号[{}]，模板名称[{}]，版本[{}]，匹配请求地址[{}]，路由服务名[{}]，路由地址[{}]",
                    config.getTangramCode(), config.getTemplateName(),
                    config.getVersion(), config.getRequestPath(),
                    config.getRouteServe(), config.getRoutePath());
        }

        logger.info("================加载Tangram配置信息结束，共加载[{}]个路由================", tangramTemplateConfigs.size());
    }

    @Override
    public int getOrder() {
        return GatewayConstVal.DB_INIT_ORDER;
    }

}
