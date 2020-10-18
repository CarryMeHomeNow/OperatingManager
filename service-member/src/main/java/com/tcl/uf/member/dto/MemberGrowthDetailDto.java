package com.tcl.uf.member.dto;

/**
 *  @author gaofan
 *  获取会员成长值记录搜索条件
 */
public class MemberGrowthDetailDto {

    private String memberGrade;

    private String memberPhonrNumber;

    private String startDay;

    private String endDay;


    public String getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(String memberGrade) {
        this.memberGrade = memberGrade;
    }

    public String getMemberPhonrNumber() {
        return memberPhonrNumber;
    }

    public void setMemberPhonrNumber(String memberPhonrNumber) {
        this.memberPhonrNumber = memberPhonrNumber;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
}
