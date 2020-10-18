package com.tcl.commondb.advert.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "advert_location_group")
public class AdvertLocationGroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(100) NOT NULL COMMENT '广告组标题'")
    private String title;

    @Column(name = "code", columnDefinition = "varchar(100) NOT NULL COMMENT '广告组编码'")
    private String code;

    @Column(name = "pic_url", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '示例图'")
    private String picUrl;

    @Column(name = "pic_width", columnDefinition = "int(8) DEFAULT NULL COMMENT '图片宽度'")
    private Integer picWidth;

    @Column(name = "pic_height", columnDefinition = "int(8) DEFAULT NULL COMMENT '图片高度'")
    private Integer picHeight;

    @Column(name = "pic_size", columnDefinition = "int(8) DEFAULT NULL COMMENT '图片大小'")
    private Integer picSize;

    @Column(name = "frame_num", columnDefinition = "tinyint(1) NOT NULL DEFAULT 1 COMMENT '包含帧数'")
    private Integer frameNum;

    @Column(name = "status", columnDefinition = "tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态： 0禁用 1启用 '")
    private Integer status;

    @Column(name = "creator", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '创建人'")
    private String creator;

    @Column(name = "app_ver", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '应用内部版本号'")
    private String appVer;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getPicWidth() {
        return picWidth;
    }

    public void setPicWidth(Integer picWidth) {
        this.picWidth = picWidth;
    }

    public Integer getPicHeight() {
        return picHeight;
    }

    public void setPicHeight(Integer picHeight) {
        this.picHeight = picHeight;
    }

    public Integer getPicSize() {
        return picSize;
    }

    public void setPicSize(Integer picSize) {
        this.picSize = picSize;
    }

    public Integer getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(Integer frameNum) {
        this.frameNum = frameNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
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
