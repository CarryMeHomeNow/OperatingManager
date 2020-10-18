package com.tcl.uf.common.base.dto;

import com.tcl.uf.common.base.util.RandomGenerator;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: TokenData
 * @Description: token json对象实体, 用于下发到客户端保存token
 * @date 2017/5/28 下午2:15
 */

public class TokenData {
    //用户id
    private Long mid;
    //用户名
    private String username;
    private String clientUid;
    private Long createTime;

    public TokenData() {
        createTime = System.currentTimeMillis();
        clientUid = RandomGenerator.getStr(10);
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getClientUid() {
        return clientUid;
    }

    public void setClientUid(String clientUid) {
        this.clientUid = clientUid;
    }
}
