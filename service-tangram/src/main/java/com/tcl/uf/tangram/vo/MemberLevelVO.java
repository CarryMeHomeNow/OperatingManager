package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/24.
 * @version 1.0
 * 会员等级模板 会员等级
 */
public class MemberLevelVO {

    private String type = "gradeHead";

    private VipTextParams vipTextParams;

    private GrowthParams growthParams;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VipTextParams getVipTextParams() {
        return vipTextParams;
    }

    public void setVipTextParams(VipTextParams vipTextParams) {
        this.vipTextParams = vipTextParams;
    }

    public GrowthParams getGrowthParams() {
        return growthParams;
    }

    public void setGrowthParams(GrowthParams growthParams) {
        this.growthParams = growthParams;
    }

    @Override
    public String toString() {
        return "MemberLevelVO{" +
                "type='" + type + '\'' +
                ", vipTextParams=" + vipTextParams +
                ", growthParams=" + growthParams +
                '}';
    }
}
