package com.tcl.uf.management.service;

import com.tcl.uf.management.param.user.UserAuthReq;
import org.springframework.data.domain.Pageable;

/**
 * 账户相关服务
 */
public interface UserService {
    /**
     * 搜索关键字
     * @param searchKey
     * @param umappid
     * @return
     */
    public Object listBySearchKey(String searchKey, String umappid,Pageable pageable);

    /**
     * 搜索已授权
     * @param searchKey
     * @param umappid
     * @param pageable
     * @return
     */
    public Object listAuthBySearchKey(String searchKey, String umappid,String departMentId, Pageable pageable);

    /**
     * 授权账号
     * @param username
     * @return
     */
    public boolean authUser(UserAuthReq userAuthReq);

    public boolean validMemberPassword(String username,String password);
    public boolean IDMLogin(String username,String password);

    public void delUser(Long id);
}
