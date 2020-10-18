package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/9/1.
 * @version 1.0
 */
public class ItemsDetail {
    private String typeValues;

    private String description;

    private String time;

    private PointsParams pointsParams;

    public String getTypeValues() {
        return typeValues;
    }

    public void setTypeValues(String typeValues) {
        this.typeValues = typeValues;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public PointsParams getPointsParams() {
        return pointsParams;
    }

    public void setPointsParams(PointsParams pointsParams) {
        this.pointsParams = pointsParams;
    }
}
