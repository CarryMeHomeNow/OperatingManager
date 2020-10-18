package com.tcl.uf.activity.dto;

public class ActivityManagerConfigureDto {

    private String activityConfigureId;//活动配置编码
    private String activityName;//活动名称
    private String activityType;//活动类型
    private String activityRule;//活动规则
    private String shareTitle;//分享标题
    private String shareDescription;//分享描述
    private String shareImage;//分享图 多个用逗号隔开
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String presentImage;//活动内容介绍图 多个用逗号隔开
    private String activityUrl;//活动地址

    public String getActivityConfigureId() {
        return activityConfigureId;
    }

    public void setActivityConfigureId(String activityConfigureId) {
        this.activityConfigureId = activityConfigureId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityRule() {
        return activityRule;
    }

    public void setActivityRule(String activityRule) {
        this.activityRule = activityRule;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDescription() {
        return shareDescription;
    }

    public void setShareDescription(String shareDescription) {
        this.shareDescription = shareDescription;
    }

    public String getShareImage() {
        return shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPresentImage() {
        return presentImage;
    }

    public void setPresentImage(String presentImage) {
        this.presentImage = presentImage;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }
}
