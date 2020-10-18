package com.tcl.uf.common.base.dto;

import com.alibaba.fastjson.JSON;
import com.tcl.uf.common.base.constant.RequestHeader;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.manage.RedisManager;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * abstract Controller
 */
public abstract class AbstractBaseController {
    private static final String LOGIN_TITLE="请先登录";
    /**
     * 成功
     *
     * @return
     */
    public ResponseData success() {
        return new ResponseData();
    }
    /**
     * 成功
     *
     * @param data
     * @return
     */
    public ResponseData success(Object data) {
        ResponseData resultDto = new ResponseData();
        resultDto.setData(data);
        return resultDto;
    }
    /**
     * 失败
     * @param msg
     * @return
     */
    public ResponseData fail(String msg){
        ResponseData resultDto = new ResponseData();
        resultDto.setMsgE(msg);
        resultDto.setMsgZ(msg);
        resultDto.setCode(-1);
        return resultDto;
    }
    public ResponseData fail(int code,String msg) {
        ResponseData resultDto = new ResponseData();
        resultDto.setCode(code);
        resultDto.setMsgZ(msg);
        resultDto.setMsgE(msg);
        return resultDto;
    }

    /**
     * 获取管理后台当前用户
     * @param request
     * @return
     */
    public TokenData getManageUserInfo(HttpServletRequest request)throws JMException{
        String ubtoken = request.getHeader(RequestHeader.CTX_REQUEST_UBTOKEN);
        if(StringUtils.isEmpty(ubtoken)){throw new JMException(LOGIN_TITLE);}
        try {
            String user = RedisManager.get(ubtoken);
            TokenData td = JSON.parseObject(user, TokenData.class);
            if(null == td){throw new JMException(LOGIN_TITLE);}
            return td;
        }catch (Exception e){
            e.printStackTrace();
            throw new JMException(LOGIN_TITLE);
        }

    }

    /**
     * 获取APP当前登录用户
     * @param request
     * @return
     */
    public TokenAppUserInfo getAppUserInfo(HttpServletRequest request)throws JMException{
        String apptoken = request.getHeader(RequestHeader.APP_REQUEST_APTOKEN);
        if(StringUtils.isEmpty(apptoken)){throw new JMException(LOGIN_TITLE);}
        try {
            String user = RedisManager.get(apptoken);
            TokenAppUserInfo userinfo = JSON.parseObject(user, TokenAppUserInfo.class);
            userinfo.setToken(apptoken);
            return userinfo;
        }catch (Exception e){
            e.printStackTrace();
            throw new JMException(LOGIN_TITLE);
        }
    }

    /**
     *
     * @param token APP登录授权的accesstoken
     * @return
     */
    public TokenAppUserInfo getAppUserInfo(String token)throws JMException{
        try {
            String user = RedisManager.get(token);
            TokenAppUserInfo userinfo = JSON.parseObject(user, TokenAppUserInfo.class);
            return userinfo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
