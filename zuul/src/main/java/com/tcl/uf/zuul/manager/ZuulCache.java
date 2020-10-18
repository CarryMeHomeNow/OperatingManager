package com.tcl.uf.zuul.manager;

import com.tcl.uf.zuul.model.RouterRuleV;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: ZuulCache
 * @Description:
 * @date 2018/1/31 上午10:59
 */

public class ZuulCache {

    private static LinkedHashMap<String, ZuulProperties.ZuulRoute> zuulCache;


    public static LinkedHashMap<String, ZuulProperties.ZuulRoute> getZuulRoute() {
        return zuulCache;
    }

    public static void cache(LinkedHashMap<String, ZuulProperties.ZuulRoute> z) {
        zuulCache = z;
    }


    /**
     * db路由的映射关系
     */
    private static LinkedHashMap<String, RouterRuleV> routRuleMap;

    public static void setRoutRuleMap(List<RouterRuleV> list) {
        LinkedHashMap<String, RouterRuleV> temp = new LinkedHashMap<>();
        for (RouterRuleV item : list) {
            temp.put(item.getPath(), item);
        }
        routRuleMap = temp;
    }

    public static RouterRuleV getRouterRuleV(String path) {
        RouterRuleV route = routRuleMap.get(path);
        if (route != null) {
            return route;
        }

        String[] paths = path.split("/");
        List<String> strings = new ArrayList<>(Arrays.asList(paths));
        int len = strings.size();
        String temp = null;
        for (int i = len - 1; i > 0; i--) {
            strings.remove(i);
            temp = StringUtils.join(strings, '/');

            route = routRuleMap.get(temp + "/*");
            if (route != null) {
                return route;
            }

            route = routRuleMap.get(temp + "/**");
            if (route != null) {
                return route;
            }
        }

        return null;
    }
}
