package com.tcl.uf.advert.dto;

public class ResourceStatusParams {

    private Integer status;//用户状态

    private Long resourceId;//资源ID

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
