package com.tcl.uf.activity.vo;


import java.util.Date;

public class ActivityCdkeySaveVo {

    //兑换码id
    private Long id;
    //活动名称
    private String name;
    //兑换类型 1 积分 2优惠券 3商品
    private Integer cdType;
    //发放数量
    private Integer total;
    //已兑换
    private Integer grant;
    //生效时间
    private Date startTime;
    //失效时间
    private Date endTime;
    //创建时间
    private Date createTime;
    //修改时间
    private Date uodateTime;
    //当前状态 2 生效 1失效
    private Integer status;
    //商品名称
    private String productName;
    //商品链接
    private String productUrl;
    //可兑换积分
    private Integer cdPoints;
    //可兑换的优惠券类型  接口查询
    private Integer coupon;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUodateTime() {
        return uodateTime;
    }

    public void setUodateTime(Date uodateTime) {
        this.uodateTime = uodateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCdType() {
        return cdType;
    }

    public void setCdType(Integer cdType) {
        this.cdType = cdType;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getGrant() {
        return grant;
    }

    public void setGrant(Integer grant) {
        this.grant = grant;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public Integer getCdPoints() {
        return cdPoints;
    }

    public void setCdPoints(Integer cdPoints) {
        this.cdPoints = cdPoints;
    }

    public Integer getCoupon() {
        return coupon;
    }

    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }
}
