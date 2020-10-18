package com.tcl.uf.gateway.filter;

import com.tcl.commondb.tangram.model.TangramTemplateConfig;
import com.tcl.uf.gateway.cache.TangramCache;
import com.tcl.uf.gateway.config.GatewayConstVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Desc : check token，(Global)
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/9/1 18:48
 */
@Component
public class TokenFilter implements GlobalFilter {

    @Autowired
    private TangramCache tangramCache;

    @Autowired
    private TokenHandler tokenHandler;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Object reqCode = exchange.getRequest().getQueryParams().get("reqCode");

        if (reqCode != null) {
            TangramTemplateConfig tangramTemplateConfig = tangramCache.getTangramConfigByTangramCode((String) reqCode);
            List<String> headerList = exchange.getRequest().getHeaders().get("token");
            if (headerList.isEmpty()) {
                // whether must
                if (GatewayConstVal.TRUE == tangramTemplateConfig.getCheckToken()) {
                    // TODO 异常 token不能为空
                } else {
                    return chain.filter(exchange);
                }
            }

            String token = headerList.get(0);
            tokenHandler.checkAppToken(token);
        }
        return chain.filter(exchange);
    }

}
