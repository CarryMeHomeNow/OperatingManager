package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/26.
 * @version 1.0
 */
public class SignParams {

    //
    private Integer currentDay;
    //
    private Integer currentPoint;
    //是否签到
    private Boolean isTodaySign;

    public Integer getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(Integer currentDay) {
        this.currentDay = currentDay;
    }

    public Integer getCurrenPoint() {
        return currentPoint;
    }

    public void setCurrenPoint(Integer currenPoint) {
        this.currentPoint = currenPoint;
    }

    public Boolean getTodaySign() {
        return isTodaySign;
    }

    public void setTodaySign(Boolean todaySign) {
        isTodaySign = todaySign;
    }

}
