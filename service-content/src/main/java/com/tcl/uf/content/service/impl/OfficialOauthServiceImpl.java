package com.tcl.uf.content.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tcl.uf.common.base.ex.ProcessControlException;
import com.tcl.uf.common.base.util.RSAUtil;
import com.tcl.uf.common.base.util.http.HttpClientUtils;
import com.tcl.uf.content.configuration.OfficialMallConfiguration;
import com.tcl.uf.content.consts.OfficialMallConstants;
import com.tcl.uf.content.service.OfficialOauthService;
import com.tcl.uf.content.utils.RedisUtils;
import com.tcl.uf.content.vo.OfficialOauthEntity;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service("officialOauthService")
public class OfficialOauthServiceImpl implements OfficialOauthService {

    @Autowired
    private OfficialMallConfiguration officialMallConfiguration;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public String obtainSecurityCode() throws ProcessControlException {
        String username = officialMallConfiguration.getUsername();
        String publicKey = null;
        try {
            JSONObject params = new JSONObject();
            params.put("account", username);

            Map<String, String> header =new HashMap<String,String>();
            header.put("t-id",officialMallConfiguration.gettId());

            String responseText = HttpClientUtils.sendPost( officialMallConfiguration.getRequestUrl()+OfficialMallConstants.SECURITY_CODE_URL, JSON.toJSONString(params), header);
            if (!StringUtil.isBlank(responseText)) {
                JSONObject responseObject = JSONObject.parseObject(responseText);
                publicKey = responseObject.getString("data");
            }
        } catch (Exception e) {
            throw new ProcessControlException("调用获取公钥方法发生异常,原因请查看日志");
        }
        return publicKey;
    }

    @Override
    public OfficialOauthEntity obtainLoginToken() throws ProcessControlException {
        OfficialOauthEntity officialOauthEntity= (OfficialOauthEntity)redisUtils.get(OfficialMallConstants.OFFICIALOAUTH_TOKEN_CACHE);
        if(officialOauthEntity != null){
            return officialOauthEntity;
        }
        String publicKey = obtainSecurityCode();
        if (StringUtil.isBlank(publicKey)) {
            throw new ProcessControlException("获取公钥失败");
        }
        String responseText = null;
        try {
            String username = officialMallConfiguration.getUsername();
            String password = officialMallConfiguration.getPassword();

            JSONObject params = new JSONObject();
            params.put("account", username);
            params.put("password", rsaEncode(publicKey, password));

            Map<String, String> header =new HashMap<String,String>();
            header.put("t-id",officialMallConfiguration.gettId());
            responseText = HttpClientUtils.sendPost( officialMallConfiguration.getRequestUrl()+OfficialMallConstants.LOGIN_TOKEN_URL, JSON.toJSONString(params), header);
        } catch (Exception e) {
            throw new ProcessControlException("获取令牌失败,错误详情请查看日志文件");
        }
        if (!StringUtil.isBlank(responseText)) {
            JSONObject responseObject = JSONObject.parseObject(responseText);
            if (!"200".equals(responseObject.getString("code"))) {
                throw new ProcessControlException(responseObject.getString("message"));
            }
            officialOauthEntity = JSONObject.toJavaObject(responseObject.getJSONObject("data"), OfficialOauthEntity.class);
            if(officialOauthEntity != null){
                redisUtils.set(OfficialMallConstants.OFFICIALOAUTH_TOKEN_CACHE,officialOauthEntity,6500);
            }
        }
        return officialOauthEntity;
    }

    private String rsaEncode(String key, String message) {
        String en = null;
        try {
            en = RSAUtil.encrypt(RSAUtil.decodePublicKey(key), message);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return en;
    }
}
