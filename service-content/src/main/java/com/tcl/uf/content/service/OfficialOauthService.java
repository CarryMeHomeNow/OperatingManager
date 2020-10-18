package com.tcl.uf.content.service;

import com.tcl.uf.common.base.ex.ProcessControlException;
import com.tcl.uf.content.vo.OfficialOauthEntity;

public interface OfficialOauthService {


    /**
     * 获取登录密钥接口(公钥)
     * @return String
     * */
    String obtainSecurityCode() throws ProcessControlException;

    /**
     * 登录获取令牌接口
     * @return OfficialOauthEntity
     * */
    OfficialOauthEntity obtainLoginToken() throws ProcessControlException;

}
