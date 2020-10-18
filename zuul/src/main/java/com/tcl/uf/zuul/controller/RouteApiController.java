package com.tcl.uf.zuul.controller;

import com.alibaba.fastjson.JSON;
import com.tcl.commonservice.service.MailService;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.zuul.controller.dto.RouteInfo;
import com.tcl.uf.zuul.controller.dto.RouterRule;
import com.tcl.uf.zuul.manager.ZuulCache;
import com.tcl.uf.zuul.model.RouterRuleV;
import com.tcl.uf.zuul.repository.RouterRuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: RefreshController
 * @Description: 更新或者增加网关的路由使用
 * @date 2018/1/30 上午5:32
 */

@RestController
@RequestMapping("/router")
public class RouteApiController {

    private static final Logger log = LoggerFactory.getLogger(RouteApiController.class);

    @Autowired
    RouterRuleRepository routerRuleRepository;

    @Autowired
    RouteLocator routeLocator;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private MailService mailService;

    @RequestMapping("/t")
    public void t(){
        ResponseData byId = mailService.findById(1L);
        log.info("qweqweqweqweqweqw");
        log.warn("sadasdaasdasdsdaddadsasd");
        log.error("zxczxczxczxczxczxczxc");
    }
    /**
     * Dashboard页面
     * @param response
     * @throws IOException
     */
    @RequestMapping("/dashboard")
    public void dashboardHtml(HttpServletResponse response) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("template/dashboard.html");
        ServletOutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }

        response.setHeader("Content-type", "text/html;charset=utf-8");
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }

    /**
     * 获取路由配置规则
     */
    @RequestMapping("/get")
    public List<RouteInfo> getRouterInfo() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> zuulRoute = ZuulCache.getZuulRoute();

        List<RouterRuleV> all = routerRuleRepository.findAllByOrderByIdDesc();

        List<RouteInfo> list = new ArrayList<>();

        for (String key : zuulRoute.keySet()) {
            ZuulProperties.ZuulRoute item = zuulRoute.get(key);

            RouteInfo info = new RouteInfo();
            info.setId(item.getId());
            info.setPath(item.getPath());

            if (all != null && !all.isEmpty()) {
                for (RouterRuleV v : all) {
                    if (key.equals(v.getPath())) {
                        info.setApiName(v.getApiName());
                        info.setLogin(v.getLogin());
                    }
                }
            }
            list.add(info);
        }

        log.info(JSON.toJSONString(list));
        return list;
    }

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public ResponseData updateRouter(@RequestBody List<RouterRule> body) {
        List<RouterRuleV> list = new ArrayList<>();
        for (RouterRule item : body) {
            if (StringUtils.isEmpty(item.getPath()) || StringUtils.isEmpty(item.getId())) {
                return new ResponseData(2, "设置的路由规则不合法", "");
            }
            RouterRuleV v = new RouterRuleV();
            v.setApiName(item.getApiName());
            v.setPath(item.getPath());
            v.setServiceId(item.getId());
            v.setLogin(item.getLogin());
            list.add(v);
        }
        routerRuleRepository.deleteAll();
        routerRuleRepository.saveAll(list);
        routerRuleRepository.flush();

        // TODO 2. refresh current gateway to new rule
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        applicationEventPublisher.publishEvent(routesRefreshedEvent);

        return new ResponseData();
    }

}
