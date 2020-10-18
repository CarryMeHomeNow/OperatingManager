package com.tcl.uf.version.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ExternalStatisticsVo {

    private Long id;
    private String platformId;
    private String platformName;
    private String appName;
    private String appId;
    private String appVer;
    private Long totalDownload;
    private Long totalActivation;
    private Long totalRegister;
    private Long totalActiveUser;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date statisticsTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public Long getTotalDownload() {
        return totalDownload;
    }

    public void setTotalDownload(Long totalDownload) {
        this.totalDownload = totalDownload;
    }

    public Long getTotalActivation() {
        return totalActivation;
    }

    public void setTotalActivation(Long totalActivation) {
        this.totalActivation = totalActivation;
    }

    public Long getTotalRegister() {
        return totalRegister;
    }

    public void setTotalRegister(Long totalRegister) {
        this.totalRegister = totalRegister;
    }

    public Long getTotalActiveUser() {
        return totalActiveUser;
    }

    public void setTotalActiveUser(Long totalActiveUser) {
        this.totalActiveUser = totalActiveUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStatisticsTime() {
        return statisticsTime;
    }

    public void setStatisticsTime(Date statisticsTime) {
        this.statisticsTime = statisticsTime;
    }
}
