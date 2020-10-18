package com.tcl.commondb.member.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "member_grade_configuration")
public class MemberGrade {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "grade", columnDefinition = "int(10) COMMENT '会员等级'")
    private Integer grade;

    @Column(name = "cosume_dis", columnDefinition = "int(10) COMMENT '消费折扣'")
    private Integer cosumeDis;

    @Column(name = "min_growth", columnDefinition = "int(20) COMMENT '最小成长值'")
    private Integer minGrowth;

    @Column(name = "max_growth",columnDefinition = "int(20) COMMENT '最大成长值'")
    private Integer maxGrowth;

    @Column(name = "reward_points",columnDefinition = "int(4) COMMENT '积分奖励'")
    private Integer rewardPoints;

    @Column(name = "coupons",columnDefinition = "text COMMENT '优惠卷集合json字符串'")
    private String coupons;

    @Column(name = "grade_name",columnDefinition = "varchar(255) COMMENT '等级名称'")
    private String gradeName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    @Column(name = "create_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    @Column(name = "update_time",columnDefinition = "timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间'")
    private Timestamp updateTime;

    @Column(name = "editor", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '编辑者'")
    private String editor;

    @Column(name = "creator", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '创建者'")
    private String creator;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getCoupons() {
        return coupons;
    }

    public void setCoupons(String coupons) {
        this.coupons = coupons;
    }

    public Integer getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCosumeDis() {
        return cosumeDis;
    }

    public void setCosumeDis(Integer cosumeDis) {
        this.cosumeDis = cosumeDis;
    }

    public Integer getMinGrowth() {
        return minGrowth;
    }

    public void setMinGrowth(Integer minGrowth) {
        this.minGrowth = minGrowth;
    }

    public Integer getMaxGrowth() {
        return maxGrowth;
    }

    public void setMaxGrowth(Integer maxGrowth) {
        this.maxGrowth = maxGrowth;
    }
}
