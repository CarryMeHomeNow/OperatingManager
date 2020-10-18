package com.tcl.uf.member.vo;

public class CouponsVo {
    private String uuid;    //优惠卷唯一标识

    private String couponTypeName;  //优惠卷名称

    private String couponMoney;    //满减金额

    private Integer couponSum; //优惠卷数量

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCouponTypeName() {
        return couponTypeName;
    }

    public void setCouponTypeName(String couponTypeName) {
        this.couponTypeName = couponTypeName;
    }

    public String getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(String couponMoney) {
        this.couponMoney = couponMoney;
    }

    public Integer getCouponSum() {
        return couponSum;
    }

    public void setCouponSum(Integer couponSum) {
        this.couponSum = couponSum;
    }
}
