package com.tcl.uf.zuul.common.dto;

import com.tcl.uf.common.base.util.RandomGenerator;


/**
 * @author chiwm@kuyumall.com
 * @ClassName: TokenData
 * @Description:
 * @date 2018/2/6 下午2:10
 */

public class TokenData {
    private Long mid;
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
