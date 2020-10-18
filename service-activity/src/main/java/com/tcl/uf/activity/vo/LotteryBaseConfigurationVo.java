package com.tcl.uf.activity.vo;

import java.util.List;

public class LotteryBaseConfigurationVo {
    private String configurationNum;
    private String activityName;
    private String effectiveStartTime;
    private String effectiveEndTime;
    private Integer everyDayLimit;
    private Integer everyDayFree;
    private Integer costGoldNumber;
    private String activityRule;
    private String openStatus;
    private String createTime;
    private String type;
    private Integer everyDayShareAddLimit; // 每日分享邀请新用户增加次数限制
    private Integer shareAddFree; // 分享获得的次数是否免费，1=是，0=否
    private String shareAddPrizeCode; // 分享获得的次数抽奖结果设置
    private Integer winSettingPeriod; // 必中周期，1=永久，2=日
    private Integer prizePeriod; // 库存周期，1=永久，2=日
    private Integer broadcastInterval; // 多久显示以下其中一项
    private String outoffStockPrizeCode; // 中了奖项没库存时
    private List<LotteryPrizeConfigurationVo> prizeConfiguration;
    private List<LotterWinSettingVo> winSetting;
    private List<LotterNewsBroadcastVo> newsBroadcast;

    public String getConfigurationNum() {
        return configurationNum;
    }

    public void setConfigurationNum(String configurationNum) {
        this.configurationNum = configurationNum;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getEffectiveStartTime() {
        return effectiveStartTime;
    }

    public void setEffectiveStartTime(String effectiveStartTime) {
        this.effectiveStartTime = effectiveStartTime;
    }

    public String getEffectiveEndTime() {
        return effectiveEndTime;
    }

    public void setEffectiveEndTime(String effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
    }

    public Integer getEveryDayLimit() {
        return everyDayLimit;
    }

    public void setEveryDayLimit(Integer everyDayLimit) {
        this.everyDayLimit = everyDayLimit;
    }

    public Integer getEveryDayFree() {
        return everyDayFree;
    }

    public void setEveryDayFree(Integer everyDayFree) {
        this.everyDayFree = everyDayFree;
    }

    public Integer getCostGoldNumber() {
        return costGoldNumber;
    }

    public void setCostGoldNumber(Integer costGoldNumber) {
        this.costGoldNumber = costGoldNumber;
    }

    public String getActivityRule() {
        return activityRule;
    }

    public void setActivityRule(String activityRule) {
        this.activityRule = activityRule;
    }

    public String getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(String openStatus) {
        this.openStatus = openStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<LotteryPrizeConfigurationVo> getPrizeConfiguration() {
        return prizeConfiguration;
    }

    public void setPrizeConfiguration(List<LotteryPrizeConfigurationVo> prizeConfiguration) {
        this.prizeConfiguration = prizeConfiguration;
    }

    public List<LotterWinSettingVo> getWinSetting() {
        return winSetting;
    }

    public void setWinSetting(List<LotterWinSettingVo> winSetting) {
        this.winSetting = winSetting;
    }

    public List<LotterNewsBroadcastVo> getNewsBroadcast() {
        return newsBroadcast;
    }

    public void setNewsBroadcast(List<LotterNewsBroadcastVo> newsBroadcast) {
        this.newsBroadcast = newsBroadcast;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

	public Integer getEveryDayShareAddLimit() {
		return everyDayShareAddLimit;
	}

	public void setEveryDayShareAddLimit(Integer everyDayShareAddLimit) {
		this.everyDayShareAddLimit = everyDayShareAddLimit;
	}

	public Integer getShareAddFree() {
		return shareAddFree;
	}

	public void setShareAddFree(Integer shareAddFree) {
		this.shareAddFree = shareAddFree;
	}

	public String getShareAddPrizeCode() {
		return shareAddPrizeCode;
	}

	public void setShareAddPrizeCode(String shareAddPrizeCode) {
		this.shareAddPrizeCode = shareAddPrizeCode;
	}

	public Integer getWinSettingPeriod() {
		return winSettingPeriod;
	}

	public void setWinSettingPeriod(Integer winSettingPeriod) {
		this.winSettingPeriod = winSettingPeriod;
	}

	public Integer getPrizePeriod() {
		return prizePeriod;
	}

	public void setPrizePeriod(Integer prizePeriod) {
		this.prizePeriod = prizePeriod;
	}

	public Integer getBroadcastInterval() {
		return broadcastInterval;
	}

	public void setBroadcastInterval(Integer broadcastInterval) {
		this.broadcastInterval = broadcastInterval;
	}

	public String getOutoffStockPrizeCode() {
		return outoffStockPrizeCode;
	}

	public void setOutoffStockPrizeCode(String outoffStockPrizeCode) {
		this.outoffStockPrizeCode = outoffStockPrizeCode;
	}
}
