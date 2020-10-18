package com.tcl.uf.member.vo;

import java.util.List;

/**
 * @author gaofan
 */
public class MemberGradeVo {


    /**
     * 会员等级id
     */
    private Integer id;

    /**
     * 会员等级
     */
    private String grade;
    /**
     * 积分奖励
     */
    private String rewardPoints;
    /**
     * 集合
     */
    private List<CouponsVo> coupons;
    /**
     * 消费折扣
     */
    private String cosumeDis;
    /**
     * 等级名称
     */
    private String gradeName;


    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public List<CouponsVo> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponsVo> coupons) {
        this.coupons = coupons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    public String getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(String rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public String getCosumeDis() {
        return cosumeDis;
    }

    public void setCosumeDis(String cosumeDis) {
        this.cosumeDis = cosumeDis;
    }
}
