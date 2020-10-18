package com.tcl.uf.advert.vo;

public class AdvertResAuditVo {

    private Long resId;//广告资源ID
    private String title; //资源标题
    private String locTitle;//广告位
    private String importantLevel;//重要级别
    private String department;//业务方
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String auditStatus;//审核状态

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocTitle() {
        return locTitle;
    }

    public void setLocTitle(String locTitle) {
        this.locTitle = locTitle;
    }

    public String getImportantLevel() {
        return importantLevel;
    }

    public void setImportantLevel(String importantLevel) {
        this.importantLevel = importantLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
}
