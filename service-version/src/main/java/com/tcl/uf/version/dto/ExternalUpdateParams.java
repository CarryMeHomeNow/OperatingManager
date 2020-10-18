package com.tcl.uf.version.dto;

public class ExternalUpdateParams {

    private Long id;
    private String platformId;
    private String appName;
    private String appId;
    private String appVer;
    private Long internalVerId;
    private String dlUrl;
    private String scoreUrl;
    private String detailUrl;
    private String userId;

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

    public Long getInternalVerId() {
        return internalVerId;
    }

    public void setInternalVerId(Long internalVerId) {
        this.internalVerId = internalVerId;
    }

    public String getDlUrl() {
        return dlUrl;
    }

    public void setDlUrl(String dlUrl) {
        this.dlUrl = dlUrl;
    }

    public String getScoreUrl() {
        return scoreUrl;
    }

    public void setScoreUrl(String scoreUrl) {
        this.scoreUrl = scoreUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }
}
