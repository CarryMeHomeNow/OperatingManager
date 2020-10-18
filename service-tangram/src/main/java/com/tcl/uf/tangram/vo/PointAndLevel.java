package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/9/1.
 * @version 1.0
 * 积分明细 总积分与vip等级
 */
public class PointAndLevel {

    private String type;

    private PointsParams pointsParams;

    private String vipGrade;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PointsParams getPointsParams() {
        return pointsParams;
    }

    public void setPointsParams(PointsParams pointsParams) {
        this.pointsParams = pointsParams;
    }

    public String getVipGrade() {
        return vipGrade;
    }

    public void setVipGrade(String vipGrade) {
        this.vipGrade = vipGrade;
    }
}
