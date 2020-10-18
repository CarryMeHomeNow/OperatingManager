package com.tcl.uf.member.vo;

public class MemberGrowthDetailVo {

    private String memberPhoneNumber;   //会员手机号

    private String memberGrade;         //会员等级

    private String memberSource;        //会员来源

    private String createTime;          //操作时间

    private String memberGrowth;        //会员成长值

    private Integer type;               //1:加；2:减


    public MemberGrowthDetailVo() {
    }

    public MemberGrowthDetailVo(String memberPhoneNumber, String memberGrade, String memberSource, String createTime, String memberGrowth, Integer type) {
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberGrade = memberGrade;
        this.memberSource = memberSource;
        this.createTime = createTime;
        this.memberGrowth = memberGrowth;
        this.type = type;
    }


    public String getMemberPhoneNumber() {
        return memberPhoneNumber;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public String getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(String memberGrade) {
        this.memberGrade = memberGrade;
    }

    public String getMemberSource() {
        return memberSource;
    }

    public void setMemberSource(String memberSource) {
        this.memberSource = memberSource;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMemberGrowth() {
        return memberGrowth;
    }

    public void setMemberGrowth(String memberGrowth) {
        this.memberGrowth = memberGrowth;
    }
}
