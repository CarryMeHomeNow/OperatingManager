package com.tcl.commondb.version.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 应用发布渠道model
 */
@Entity
@Table(name = "app_version_feedback")
public class VersionFeedbackModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "platform_id", columnDefinition = "VARCHAR(64) COMMENT '渠道ID'")
    private String platformId;

    @Column(name = "app_ver", columnDefinition = "VARCHAR(64) COMMENT 'APP渠道版本号'")
    private String appVer;

    @Column(name = "score", columnDefinition = "TINYINT COMMENT '评分'")
    private Integer score;

    @Column(name = "remark", columnDefinition = "TEXT COMMENT '反馈意见'")
    private String remark;

    @Column(name = "ssoid", columnDefinition = "BIGINT(20) COMMENT '用户ssoid'")
    private Long ssoid;

    @Column(name = "internal_ver", columnDefinition = "VARCHAR(64) COMMENT '应用内部版本号'")
    private String internalVer;


    @JsonFormat(timezone = "GMT+8", locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createdTime;

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

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getSsoid() {
        return ssoid;
    }

    public void setSsoid(Long ssoid) {
        this.ssoid = ssoid;
    }

    public String getInternalVer() {
        return internalVer;
    }

    public void setInternalVer(String internalVer) {
        this.internalVer = internalVer;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
