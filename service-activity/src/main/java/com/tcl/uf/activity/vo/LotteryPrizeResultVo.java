package com.tcl.uf.activity.vo;

public class LotteryPrizeResultVo {

    private Integer status; //0:成功
    private String notice; //提示信息
    private LotteryPrizeInformation prizeInformation;//奖品实体信息
    private Integer chance;//剩余机会

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public LotteryPrizeInformation getPrizeInformation() {
        return prizeInformation;
    }

    public void setPrizeInformation(LotteryPrizeInformation prizeInformation) {
        this.prizeInformation = prizeInformation;
    }

    public Integer getChance() {
        return chance;
    }

    public void setChance(Integer chance) {
        this.chance = chance;
    }
}
