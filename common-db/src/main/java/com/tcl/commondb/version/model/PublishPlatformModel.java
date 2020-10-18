package com.tcl.commondb.version.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 应用发布渠道model
 */
@Entity
@Table(name = "app_publish_platform")
public class PublishPlatformModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "platform_name", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '渠道名称'")
    private String platformName;

    @Column(name = "platform_id", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '渠道ID'")
    private String platformId;

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

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
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
