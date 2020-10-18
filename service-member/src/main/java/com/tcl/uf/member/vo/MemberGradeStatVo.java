package com.tcl.uf.member.vo;

public class MemberGradeStatVo {

    private String grade;// 等级
    private Long userNum;// 用户数
    private Float ratio;// 占比
    private GradeValue gradeValue;// 成长值范围

    public static class GradeValue {
        private Integer min;
        private Integer max;

        public Integer getMin() {
            return min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }

    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getUserNum() {
        return userNum;
    }

    public void setUserNum(Long userNum) {
        this.userNum = userNum;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }

    public GradeValue getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(GradeValue gradeValue) {
        this.gradeValue = gradeValue;
    }
}
