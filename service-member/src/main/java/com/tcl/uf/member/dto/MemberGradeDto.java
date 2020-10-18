package com.tcl.uf.member.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author gaofan
 */
public class MemberGradeDto implements Serializable{

    /**
     * 会员等级id
     */
    private Integer id;

    /**
     * 会员折扣 0:不打折 1至99 :0.01至0.99折
     */
    private Integer cosumeDis;
    /**
     * 积分奖励 *0.01得到积分的奖励比例
     */
    private Integer rewardPoints;
    /**
     * 优惠卷包
     */
    private List<CouponsDto> coupons;

    /**
     * 会员等级名称
     */
    private String gradeName;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCosumeDis() {
        return cosumeDis;
    }

    public void setCosumeDis(Integer cosumeDis) {
        this.cosumeDis = cosumeDis;
    }

    public Integer getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public List<CouponsDto> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponsDto> coupons) {
        this.coupons = coupons;
    }
}
