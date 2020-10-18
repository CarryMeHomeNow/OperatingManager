package com.tcl.uf.advert.dto;

public class LocationStatusParams {

    private Integer status;//广告位状态

    private Long locId;//广告位ID

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }
}
