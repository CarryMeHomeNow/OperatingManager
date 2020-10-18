package com.tcl.uf.points.vo;


public class PointTaskTypeVo {
    //任务类型 1新手任务 2日常任务
    private Integer type;
    private PointTaskVo PointTaskVo;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public com.tcl.uf.points.vo.PointTaskVo getPointTaskVo() {
        return PointTaskVo;
    }

    public void setPointTaskVo(com.tcl.uf.points.vo.PointTaskVo pointTaskVo) {
        PointTaskVo = pointTaskVo;
    }
}
