package com.tcl.uf.version.dto;

public class ExternalListParams {

    private String appId;
    private String appName;
    private String appVer;
    private Integer internalVerId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public Integer getInternalVerId() {
        return internalVerId;
    }

    public void setInternalVerId(Integer internalVerId) {
        this.internalVerId = internalVerId;
    }
}
