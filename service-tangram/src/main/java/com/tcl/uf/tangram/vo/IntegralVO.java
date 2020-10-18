package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/20.
 * @version 1.0
 * @Desc 积分
 */
public class IntegralVO {

    private String type = "memberHead";

    //会员名称
    private VipTextParams vipTextParams;
    /**
     * 积分明细
     */
    private VipPointParams vipPointParams;
    /**
     * 签到天数
     */
    private SignParams signParams;
    /**
     * 规则按钮调转地址
     */
    private RulesParams ruleParams;
    /**
     * 跳转参数
     */
    private ActionParams actionParams;

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

    public VipPointParams getVipPointParams() {
        return vipPointParams;
    }

    public void setVipPointParams(VipPointParams vipPointParams) {
        this.vipPointParams = vipPointParams;
    }

    public SignParams getSignParams() {
        return signParams;
    }

    public void setSignParams(SignParams signParams) {
        this.signParams = signParams;
    }

    public RulesParams getRuleParams() {
        return ruleParams;
    }

    public void setRuleParams(RulesParams ruleParams) {
        this.ruleParams = ruleParams;
    }

    public ActionParams getActionParams() {
        return actionParams;
    }


    public void setActionParams(ActionParams actionParams) {
        this.actionParams = actionParams;
    }

    @Override
    public String toString() {
        return "{" +
                "type:'" + type + '\'' +
                ", vipTextParams:" + vipTextParams +
                ", vipPointParams:" + vipPointParams +
                ", signParams:" + signParams +
                ", ruleParams:" + ruleParams +
                ", actionParams:" + actionParams +
                '}';
    }
}
