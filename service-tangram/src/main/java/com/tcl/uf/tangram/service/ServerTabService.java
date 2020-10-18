package com.tcl.uf.tangram.service;

import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;

/**
 * @author zhongfk on 2020/8/19.
 * @version 1.0
 */
public interface ServerTabService {

    /**
     * 获取服务首页信息
     * @param  page, Integer size, String reqCode, TokenAppUserInfo appUserInfo
     * @return
     */
    String queryAll(Integer page, Integer size, String reqCode,TokenAppUserInfo appUserInfo);

    /**
     * 根据板块id 查询文章列表
     * @param sectionId
     * @return
     */
    String queryArticleBySectionId(Integer page,Integer size,Long sectionId);
}
