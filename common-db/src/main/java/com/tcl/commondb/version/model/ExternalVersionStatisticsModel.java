package com.tcl.commondb.version.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "app_external_version_statistics")
public class ExternalVersionStatisticsModel {

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

    @Column(name = "total_download", columnDefinition = "bigint(20) DEFAULT NULL COMMENT '累计下载量'")
    private Long totalDownload;

    @Column(name = "total_activation", columnDefinition = "bigint(20) DEFAULT NULL COMMENT '累计激活量'")
    private Long totalActivation;

    @Column(name = "total_register", columnDefinition = "bigint(20) DEFAULT NULL COMMENT '累计注册量'")
    private Long totalRegister;

    @Column(name = "total_active_user", columnDefinition = "bigint(20) DEFAULT NULL COMMENT '累计活跃用户量'")
    private Long totalActiveUser;

    @Column(name = "update_time", columnDefinition = "datetime DEFAULT NULL COMMENT '更新时间'")
    private Date updateTime;

    @Column(name = "create_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @Column(name = "statistics_time", columnDefinition = "datetime DEFAULT NULL COMMENT '数据统计时间'")
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
