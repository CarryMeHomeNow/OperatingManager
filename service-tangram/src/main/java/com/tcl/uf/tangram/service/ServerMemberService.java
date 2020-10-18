package com.tcl.uf.tangram.service;

import com.tcl.uf.tangram.vo.TangramRequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhongfk on 2020/8/22.
 * @version 1.0
 */
public interface ServerMemberService {
    /**
     * 服务首页-会员等级
     * @param paramDTO
     * @return
     */
    String getMemberLevel(TangramRequestParam paramDTO,HttpServletRequest request);
}
