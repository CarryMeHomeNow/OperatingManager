package com.tcl.uf.points.vo;


import java.util.Date;

public class PointTaskPlanVo {
    private Long id;
    //计划标题
    private String title;
    //任务数量
    private Integer num;
    //投放时间
    private Long startTime;
    //投放结束时间
    private Long endTime;
    //当前状态  1 生效 2，失效
    private Integer status;
    //任务类型  1,新手任务 2，日常任务
    private Integer type;

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
