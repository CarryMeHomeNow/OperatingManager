package com.tcl.commondb.version.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "app_internal_version")
public class InternalVersionModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "app_name", columnDefinition = "varchar(150) DEFAULT NULL COMMENT 'APP名称'")
    private String appName;

    @Column(name = "internal_ver", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '内部版本号'")
    private String internalVersion;

    @Column(name = "tab_ec_ver", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '电商版本号'")
    private String tabEcVersion;

    @Column(name = "tab_sv_ver", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '服务版本号'")
    private String tabSvVersion;

    @Column(name = "tab_iot_ver", columnDefinition = "varchar(64) DEFAULT NULL COMMENT 'IOT版本号'")
    private String tabLotVersion;

    @Column(name = "tab_se_ver", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '场景版本号'")
    private String tabSeVersion;

    @Column(name = "tab_my_ver", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '我的版本号'")
    private String tabMyVersion;

    @Column(name = "ac_sdk_ver", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '账号SDK版本号'")
    private String acSdkVersion;

    @Column(name = "tab_ec_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '商城跳转地址'")
    private String tabEcUrl;

    @Column(name = "tab_sv_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '服务跳转地址'")
    private String tabSvUrl;

    @Column(name = "tab_iot_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT 'IOT跳转地址'")
    private String tabIotUrl;

    @Column(name = "tab_se_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '场景跳转地址'")
    private String tabSeUrl;

    @Column(name = "tab_my_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '我的跳转地址'")
    private String tabMyUrl;

    @Column(name = "ac_sdk_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '账号SDK跳转地址'")
    private String acSdkUrl;

    @Column(name = "official_download_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '官方下载链接'")
    private String officialDownloadUrl;

    @Column(name = "editor", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '编辑者'")
    private String editor;

    @Column(name = "creator", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '创建者'")
    private String creator;

    @Column(name = "create_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @Column(name = "update_time", columnDefinition = "datetime DEFAULT NULL COMMENT '更新时间'")
    private Date updateTime;

    @Column(name = "use_status", columnDefinition = "int(1) DEFAULT 1 COMMENT '伪删除 1:使用, 0:删除'")
    private Integer useStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInternalVersion() {
        return internalVersion;
    }

    public void setInternalVersion(String internalVersion) {
        this.internalVersion = internalVersion;
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

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getOfficialDownloadUrl() {
        return officialDownloadUrl;
    }

    public void setOfficialDownloadUrl(String officialDownloadUrl) {
        this.officialDownloadUrl = officialDownloadUrl;
    }
}
