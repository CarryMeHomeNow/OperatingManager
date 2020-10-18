package com.tcl.commondb.points.model;

import javax.persistence.*;

@Entity
@Table(name = "point_coupon_exchange_record")
public class PointCouponExchangeRecord {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "point_coupon_id", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT 'point_coupon表主键'")
    private Long pointCouponId;

    @Column(name = "account_id", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT '用户ssoid'")
    private Long accountId;
    @Column(name = "create_time", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT '领取时间'")
    private Long createTime;
    public Long getId() {
        return id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPointCouponId() {
        return pointCouponId;
    }

    public void setPointCouponId(Long pointCouponId) {
        this.pointCouponId = pointCouponId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
