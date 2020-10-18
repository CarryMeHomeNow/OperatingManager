package com.tcl.uf.activity.dto;

import java.util.Date;

/**
 * 兑换码查询条件
 */

public class ActivityCdkeyListDto {
    //活动名称
    private String name;
    //兑换类型
    private Integer cdType;
    //创建时间
    private Date startTime;

    private Date endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCdType() {
        return cdType;
    }

    public void setCdType(Integer cdType) {
        this.cdType = cdType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
