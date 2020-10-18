package com.tcl.uf.advert.dto;

public class UserStatusParams {

    private Integer status;//用户状态

    private Long userId;//用户ID

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
