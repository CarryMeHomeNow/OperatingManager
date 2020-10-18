package com.tcl.uf.points.vo;


import java.util.Date;
import java.util.List;

public class PointTaskPlanListVo {
    private Long id;
    //计划标题
    private String title;
    //投放时间
    private Long startTime;
    //投放结束时间
    private Long endTime;

    private Integer type;
    private List<PointTaskVo> pointTaskVoList;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<PointTaskVo> getPointTaskVoList() {
        return pointTaskVoList;
    }

    public void setPointTaskVoList(List<PointTaskVo> pointTaskVoList) {
        this.pointTaskVoList = pointTaskVoList;
    }
}
