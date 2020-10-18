package com.tcl.uf.zuul.filter.pre;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.manage.RedisManager;
import com.tcl.uf.zuul.common.dto.TokenData;
import com.tcl.uf.zuul.manager.ZuulCache;
import com.tcl.uf.zuul.model.RouterRuleV;
import com.tcl.uf.zuul.repository.RouterRuleRepository;
import com.tcl.uf.zuul.route.DbRouteLocator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static com.tcl.uf.common.base.constant.RequestHeader.X_AUTH_ID;
import static com.tcl.uf.zuul.common.KeyAttribute.*;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: LoginStatusFilter
 * @Description:
 * @date 2018/1/17 上午11:42
 */

@Component
public class LoginStatusFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(LoginStatusFilter.class);
    @Value("${tclaccount.publickey}")
    private String publickeyUrl;
//    @Autowired
//    SystemService systemService;

    @Autowired
    DbRouteLocator routeLocator;

    @Autowired
    RouterRuleRepository routerRuleRepository;

    public LoginStatusFilter() {

    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        Route matchingRoute = routeLocator.getMatchingRoute(request.getRequestURI());
        String serviceId = matchingRoute.getId();
        if (serviceId == null) {
            return null;
        }

        // 获取匹配信息的匹配KEY.
        String path = matchingRoute.getFullPath();

        // 登入态校验
        // login 信息从内存/缓存中读取
        RouterRuleV service = ZuulCache.getRouterRuleV(path);
        if (service == null) {
            return null;
        }

        boolean login = service.getLogin();
        String token = null;
        int loginType = 1;
        if (login) {

            token = request.getHeader(CTX_REQUEST_UBTOKEN);
            if(StringUtils.isEmpty(token)){
                token = request.getHeader(APP_REQUEST_APTOKEN);
                loginType = 2;
            }

            if (StringUtils.isEmpty(token)) {
                errorToken(ctx);
                return null;
            }


            String tokenValue = RedisManager.get(token);
            switch (loginType){
                case CTX_REQUEST_UBTOKEN_LOGIN_TYPE:
                    if (StringUtils.isEmpty(tokenValue)) {
                        errorToken(ctx);
                        return null;
                    }
                    TokenData td = JSON.parseObject(tokenValue, TokenData.class);
                    if (td == null) {
                        errorToken(ctx, "用户信息不存在", "member is not exist");
                        return null;
                    }
                    Long uid = td.getMid();
                    ctx.addZuulRequestHeader(X_AUTH_ID, uid.toString());
                    break;
                case APP_REQUEST_APTOKEN_LOGIN_TYPE:
                    try {
                        if(StringUtils.isEmpty(tokenValue)){
                            String jwtPubSecret = HttpUtil.get(publickeyUrl);
                            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(jwtPubSecret));
                            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpecX509);
                            Jws<Claims> ca = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token);
//                            String[] split = token.split("\\.");

                            Claims claims = ca.getBody();
                            RedisManager.set(token,claims.get("userInfo").toString(),60*60*24);
                        }
                    }catch (Exception  e){
                        errorToken(ctx);
                    }
                    break;
            }
            return null;
        }
        return null;
    }


    private void errorToken(RequestContext ctx) {
        errorToken(ctx, "token无效", "invalid token");
    }

    private void errorToken(RequestContext ctx, String msgZ, String msgE) {
        ctx.setResponseStatusCode(200);
        ctx.set(CTX_RESPONSE_JSON, new ResponseData<>(103, msgZ, msgE));
        ctx.setSendZuulResponse(false);
    }
}
