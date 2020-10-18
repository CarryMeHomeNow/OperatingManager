package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/20.
 * @version 1.0
 * @Desc 跳转参数
 */
public class ActionParams {
    /**
     * 跳转类型
     */
    private String actionType = "jump";
    private String actionUrl;
    private String keyWords;
    private String categoryName;
    private String categoryId;
    private String commodityId;
    private String equityId;
    private String couponId;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getEquityId() {
        return equityId;
    }

    public void setEquityId(String equityId) {
        this.equityId = equityId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    @Override
    public String toString() {
        return "ActionParams{" +
                "actionType='" + actionType + '\'' +
                ", actionUrl='" + actionUrl + '\'' +
                ", keyWords='" + keyWords + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", commodityId='" + commodityId + '\'' +
                ", equityId='" + equityId + '\'' +
                ", couponId='" + couponId + '\'' +
                '}';
    }
}
