package com.tcl.uf.gateway.filter;

import com.tcl.uf.common.base.manage.RedisManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @Desc : token处理器
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/9/4 15:52
 */
@Component
public class TokenHandler {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${tcl.account.publicKey}")
    private String publicKeyUrl;

    public boolean checkAppToken(String token) {
        String tokenValue = RedisManager.get(token);

        if (StringUtils.isEmpty(tokenValue)) {
            try {
                String jwtPubSecret = restTemplate.getForObject(publicKeyUrl, String.class, "");
                X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(jwtPubSecret));
                RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpecX509);
                Jws<Claims> ca = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token);
                Claims claims = ca.getBody();
                RedisManager.set(token, claims.get("userInfo").toString(), 60 * 60 * 24);
            } catch (Exception e) {
                // TODO 异常处理
                // logger
                return false;
            }
        }
        return true;
    }
}
