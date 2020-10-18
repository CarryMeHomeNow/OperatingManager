package com.tcl.commondb.version.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 应用外部版本model
 */
@Entity
@Table(name = "app_external_version")
public class ExternalVersionModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "platform_id", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '渠道ID'")
    private String platformId;

    @Column(name = "app_name", columnDefinition = "varchar(64) DEFAULT NULL COMMENT 'APP名称'")
    private String appName;

    @Column(name = "app_id", columnDefinition = "varchar(64) DEFAULT NULL COMMENT 'APP ID'")
    private String appId;

    @Column(name = "app_ver", columnDefinition = "varchar(64) DEFAULT NULL COMMENT 'APP版本号'")
    private String appVer;

    @Column(name = "internal_ver_id", columnDefinition = "int(32) DEFAULT NULL COMMENT '应用内部版本ID'")
    private Long internalVerId;

    @Column(name = "dl_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT 'APP下载链接'")
    private String dlUrl;

    @Column(name = "score_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT 'APP评价链接'")
    private String scoreUrl;

    @Column(name = "detail_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT 'APP展示链接'")
    private String detailUrl;

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

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
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


}

