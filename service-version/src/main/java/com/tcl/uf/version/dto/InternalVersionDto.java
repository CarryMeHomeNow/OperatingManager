package com.tcl.uf.version.dto;

public class InternalVersionDto {

    private Long id;
    private String appName;//app名称
    private String internalVersion;//app版本号
    private String tabEcVersion;//电商版本号
    private String tabSvVersion;//服务版本号
    private String tabLotVersion;//IOT版本号
    private String tabSeVersion;//场景版本号
    private String tabMyVersion;//我的版本号
    private String acSdkVersion;//账号SDK版本号
    private String tabEcUrl;//商城跳转地址
    private String tabSvUrl;//服务跳转地址
    private String tabIotUrl;//IOT跳转地址
    private String tabSeUrl;//场景跳转地址
    private String tabMyUrl;//我的跳转地址
    private String acSdkUrl;//账号SDK跳转地址
    private String officialDownloadUrl;//官方下载链接

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTabEcVersion() {
        return tabEcVersion;
    }

    public void setTabEcVersion(String tabEcVersion) {
        this.tabEcVersion = tabEcVersion;
    }

    public String getTabSvVersion() {
        return tabSvVersion;
    }

    public void setTabSvVersion(String tabSvVersion) {
        this.tabSvVersion = tabSvVersion;
    }

    public String getTabLotVersion() {
        return tabLotVersion;
    }

    public void setTabLotVersion(String tabLotVersion) {
        this.tabLotVersion = tabLotVersion;
    }

    public String getTabSeVersion() {
        return tabSeVersion;
    }

    public void setTabSeVersion(String tabSeVersion) {
        this.tabSeVersion = tabSeVersion;
    }

    public String getTabMyVersion() {
        return tabMyVersion;
    }

    public void setTabMyVersion(String tabMyVersion) {
        this.tabMyVersion = tabMyVersion;
    }

    public String getAcSdkVersion() {
        return acSdkVersion;
    }

    public void setAcSdkVersion(String acSdkVersion) {
        this.acSdkVersion = acSdkVersion;
    }

    public String getTabEcUrl() {
        return tabEcUrl;
    }

    public void setTabEcUrl(String tabEcUrl) {
        this.tabEcUrl = tabEcUrl;
    }

    public String getTabSvUrl() {
        return tabSvUrl;
    }

    public void setTabSvUrl(String tabSvUrl) {
        this.tabSvUrl = tabSvUrl;
    }

    public String getTabIotUrl() {
        return tabIotUrl;
    }

    public void setTabIotUrl(String tabIotUrl) {
        this.tabIotUrl = tabIotUrl;
    }

    public String getTabSeUrl() {
        return tabSeUrl;
    }

    public void setTabSeUrl(String tabSeUrl) {
        this.tabSeUrl = tabSeUrl;
    }

    public String getTabMyUrl() {
        return tabMyUrl;
    }

    public void setTabMyUrl(String tabMyUrl) {
        this.tabMyUrl = tabMyUrl;
    }

    public String getAcSdkUrl() {
        return acSdkUrl;
    }

    public void setAcSdkUrl(String acSdkUrl) {
        this.acSdkUrl = acSdkUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getInternalVersion() {
        return internalVersion;
    }

    public void setInternalVersion(String internalVersion) {
        this.internalVersion = internalVersion;
    }

    public String getOfficialDownloadUrl() {
        return officialDownloadUrl;
    }

    public void setOfficialDownloadUrl(String officialDownloadUrl) {
        this.officialDownloadUrl = officialDownloadUrl;
    }
}
