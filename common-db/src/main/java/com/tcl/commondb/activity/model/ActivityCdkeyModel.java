package com.tcl.commondb.activity.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 兑换码
 */
@Entity
@Table(name = "activity_cdkey")
public class ActivityCdkeyModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(64)  COMMENT '活动名称'")
    private String name;

    @Column(name = "cd_type", columnDefinition = "int(11) DEFAULT 1 COMMENT '兑换类型 1 积分 2优惠券 3商品'")
    private Integer cdType;

    @Column(name = "start_time", columnDefinition = "bigint(11) DEFAULT 0 COMMENT '生效开始时间'")
    private Date startTime;

    @Column(name = "end_time", columnDefinition = "bigint(11) DEFAULT 0 COMMENT '生效结束时间'")

    private Date endTime;

    @Column(name = "total", columnDefinition = "int(11) DEFAULT 0 COMMENT '发放总数'")
    private Integer total;

    @Column(name = "coupon", columnDefinition = "int(11)  COMMENT '可兑换的优惠券'")
    private Integer coupon;

    @Column(name = "product_name", columnDefinition = "varchar(64)  COMMENT '商品名称'")
    private String productName;

    @Column(name = "product_url", columnDefinition = "varchar(64)  COMMENT '商品链接'")
    private String productUrl;

    @Column(name = "cd_points", columnDefinition = "int(11) DEFAULT 0 COMMENT '可兑换积分'")
    private Integer cdPoints;

    public Integer getCdPoints() {
        return cdPoints;
    }

    public void setCdPoints(Integer cdPoints) {
        this.cdPoints = cdPoints;
    }

    @Column(name = "grant_status", columnDefinition = "int(11) DEFAULT 0 COMMENT '已兑换'")
    private Integer grantStatus;

    @Column(name = "status", columnDefinition = "int(11) DEFAULT 0 COMMENT '状态 1 失效 2生效'")
    private Integer status;

    @Column(name = "is_delete", columnDefinition = "tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否删除，1否 2是'")
    private Byte isDelete;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间'")
    private Date updateTime;
    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCoupon() {
        return coupon;
    }

    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
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

    public Integer getGrantStatus() {
        return grantStatus;
    }

    public void setGrantStatus(Integer grantStatus) {
        this.grantStatus = grantStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
