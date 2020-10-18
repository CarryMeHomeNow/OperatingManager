package com.tcl.uf.advert.dto;

public class ResourceStatusListParams {
    private Long groupId; //广告组ID
    private Integer frameId;//帧ID
    private String startTime;//筛选投放开始时间
    private String endTime;//筛选投放结束时间
    private Integer listType;//列表类型

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getFrameId() {
        return frameId;
    }

    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getListType() {
        return listType;
    }

    public void setListType(Integer listType) {
        this.listType = listType;
    }
}
