package com.tcl.uf.activity.dto;

/**
 * @author youyun.xu
 * @ClassName: ${CLASSNAME}
 * @Description: 文章内容管理
 * @date 2020/8/17 18:50
 */
public class ActivityManagerConfigureParams {

    private String activityName;//活动名称
    private String activityType;//活动类型、
    private String startTime;//开始时间
    private String endTime;//结束时间

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
}
