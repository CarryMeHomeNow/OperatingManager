package com.tcl.uf.version.vo;

import javax.persistence.Entity;

/**
 * @author gaofan
 */
@Entity
public class NewVersionVo {

    /**
     * 渠道id
     */
    private String platformId;
    /**
     * 渠道名称
     */

    private String platformName;
    /**
     * app名称
     */
    private String appName;
    /**
     * app班本
     */
    private String appVer;
    /**
     * id
     */
    private String id;
    /**
     * 应用内部版本号
     */
    private String internalVer;
    /**
     * 下载链接
     */
    private String downloadUrl;
    /**
     * 评价链接
     */
    private String scoreUrl;
    /**
     * APP展示链接
     */
    private String detailUrl;
    /**
     * 创建时间
     */
    private String createdTime;

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInternalVer() {
        return internalVer;
    }

    public void setInternalVer(String internalVer) {
        this.internalVer = internalVer;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
