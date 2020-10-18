package com.tcl.uf.version.dto;

/**
 * @author gaofan
 */
public class ScoreAndRemarkDto {

    /**
     * 应用渠道Id
     */
    private String platformId;

    /**
     * 应用渠道版本
     */
    private String appVer;

    /**
     * 评分值
     */
    private Integer score;

    /**
     * 反馈意见
     */
    private String remark;

    /**
     * 应用内部版本号
     */
    private String internalVer;


    public String getInternalVer() {
        return internalVer;
    }

    public void setInternalVer(String internalVer) {
        this.internalVer = internalVer;
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
}
