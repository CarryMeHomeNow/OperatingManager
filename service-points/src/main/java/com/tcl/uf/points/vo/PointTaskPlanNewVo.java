package com.tcl.uf.points.vo;


import java.util.Date;

public class PointTaskPlanNewVo {

    private Long id;
    //计划标题
    private String title;
    private Long startTime;
    private Long endTime;
    private  Integer type;
    private Long createTime;
    private Long uPdateTime;

    //任务id
    private Long[] taskId;



    public Integer getType() {
        return type;
    }

    public Long[] getTaskId() {
        return taskId;
    }

    public void setTaskId(Long[] taskId) {
        this.taskId = taskId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getuPdateTime() {
        return uPdateTime;
    }

    public void setuPdateTime(Long uPdateTime) {
        this.uPdateTime = uPdateTime;
    }
}

