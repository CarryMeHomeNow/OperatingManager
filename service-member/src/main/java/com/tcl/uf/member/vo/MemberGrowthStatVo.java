package com.tcl.uf.member.vo;

public class MemberGrowthStatVo {

    private String source;// 渠道
    private Long growth;// 成长值
    private Float ratio;// 占比

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getGrowth() {
        return growth;
    }

    public void setGrowth(Long growth) {
        this.growth = growth;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }
}
