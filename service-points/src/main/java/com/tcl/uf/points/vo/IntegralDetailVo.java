package com.tcl.uf.points.vo;

/**
 * @author zhongfk on 2020/9/1.
 * @version 1.0
 */
public class IntegralDetailVo {

    private Long accountId;

    private String date;

    private String description;

    private String flowId;

    private int growValue;

    private String opType;

    private int score;

    private String scoreType;

    private String source;

    private String version;
    /**
     * 年月 2020-09
     */
    private String yearMonth;
    /**
     * 日/周几  23/周一
     */
    private String day;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public int getGrowValue() {
        return growValue;
    }

    public void setGrowValue(int growValue) {
        this.growValue = growValue;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
