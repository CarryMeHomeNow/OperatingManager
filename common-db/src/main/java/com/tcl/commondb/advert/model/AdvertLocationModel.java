package com.tcl.commondb.advert.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "advert_location_configure")
public class AdvertLocationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '广告位标题'")
    private String title;

    @Column(name = "gid", columnDefinition = "int(32) NOT NULL COMMENT '广告组ID'")
    private Long gid;

    @Column(name = "frame_id", columnDefinition = "tinyint(4) NOT NULL COMMENT '帧ID'")
    private Integer frameId;

    @Column(name = "default_pic", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '默认图片'")
    private String defaultPic;

    @Column(name = "default_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '默认图片'")
    private String defaultUrl;

    @Column(name = "online_time", columnDefinition = "TIMESTAMP NULL DEFAULT NULL COMMENT '上线时间'")
    private Timestamp onlineTime;

    @Column(name = "offline_time", columnDefinition = "TIMESTAMP NULL DEFAULT NULL COMMENT '下线时间'")
    private Timestamp offlineTime;

    @Column(name = "status", columnDefinition = "tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态： 0草稿 1审核中 2上线 3下线'")
    private Integer status;

    @Column(name = "audit_status", columnDefinition = "tinyint(4) NOT NULL DEFAULT 0 COMMENT '审核状态：0未审核 1已通过 2已驳回'")
    private Integer auditStatus;

    @Column(name = "editor", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '创建人'")
    private String editor;

    @Column(name = "creator", columnDefinition = "varchar(100) NOT NULL COMMENT '创建人'")
    private String creator;

    @Column(name = "is_deleted", columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除 0:否 1:是'")
    private Integer isDeleted;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    @Column(name = "update_time", columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    private Timestamp updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Integer getFrameId() {
        return frameId;
    }

    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    public String getDefaultPic() {
        return defaultPic;
    }

    public void setDefaultPic(String defaultPic) {
        this.defaultPic = defaultPic;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public Timestamp getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Timestamp onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Timestamp getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Timestamp offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
