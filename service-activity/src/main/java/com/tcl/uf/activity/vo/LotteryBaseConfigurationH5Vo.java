package com.tcl.uf.activity.vo;

import java.util.List;

public class LotteryBaseConfigurationH5Vo {
	private String type;
    private String configurationNum;
    private String activityName;
    private String effectiveStartTime;
    private String effectiveEndTime;
    private Integer everyDayLimit;
    private Integer everyDayFree;
    private Integer costGoldNumber;
    private String activityRule;
    private String openStatus;
    private Integer chance;//剩余机会
    private Integer everyDayShareAddLimit; // 每日分享邀请新用户增加次数限制
    private Integer shareAddFree; // 分享获得的次数是否免费，1=是，0=否
    private String shareAddPrizeCode; // 分享获得的次数抽奖结果设置
    private Integer winSettingPeriod; // 必中周期，1=永久，2=日
    private Integer prizePeriod; // 库存周期，1=永久，2=日
    private Integer broadcastInterval; // 多久显示以下其中一项
    private String outoffStockPrizeCode; // 中了奖项没库存时
    private Boolean isActivityStart = true; // 活动是否开始
    private Boolean isActivityEnd = false; // 活动是否结束
    private Boolean hasJoinActivity = true; // 是否参与活动
    private Boolean isInfoVerifyPass = true; // 信息是否审核通过
    private Boolean canSeeTodayRecord = true; // 能否查看今日中奖记录
    private String canNotSeeTodayRecordToast; // 不能查看今日中间记录提示
    private Integer cateType; // 1=电视，2=非电视
    private Object activityConfig; // 天天抽锦鲤活动配置
    private List<LotteryPrizeConfigurationVo> prizeConfiguration;

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

    public List<LotteryPrizeConfigurationVo> getPrizeConfiguration() {
        return prizeConfiguration;
    }

    public void setPrizeConfiguration(List<LotteryPrizeConfigurationVo> prizeConfiguration) {
        this.prizeConfiguration = prizeConfiguration;
    }

    public Integer getChance() {
        return chance;
    }

    public void setChance(Integer chance) {
        this.chance = chance;
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

	public Boolean getIsActivityStart() {
		return isActivityStart;
	}

	public void setIsActivityStart(Boolean isActivityStart) {
		this.isActivityStart = isActivityStart;
	}

	public Boolean getIsInfoVerifyPass() {
		return isInfoVerifyPass;
	}

	public void setIsInfoVerifyPass(Boolean isInfoVerifyPass) {
		this.isInfoVerifyPass = isInfoVerifyPass;
	}

	public Boolean getCanSeeTodayRecord() {
		return canSeeTodayRecord;
	}

	public void setCanSeeTodayRecord(Boolean canSeeTodayRecord) {
		this.canSeeTodayRecord = canSeeTodayRecord;
	}

	public String getCanNotSeeTodayRecordToast() {
		return canNotSeeTodayRecordToast;
	}

	public void setCanNotSeeTodayRecordToast(String canNotSeeTodayRecordToast) {
		this.canNotSeeTodayRecordToast = canNotSeeTodayRecordToast;
	}

	public Boolean getHasJoinActivity() {
		return hasJoinActivity;
	}

	public void setHasJoinActivity(Boolean hasJoinActivity) {
		this.hasJoinActivity = hasJoinActivity;
	}

	public Integer getCateType() {
		return cateType;
	}

	public void setCateType(Integer cateType) {
		this.cateType = cateType;
	}

	public Object getActivityConfig() {
		return activityConfig;
	}

	public void setActivityConfig(Object activityConfig) {
		this.activityConfig = activityConfig;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsActivityEnd() {
		return isActivityEnd;
	}

	public void setIsActivityEnd(Boolean isActivityEnd) {
		this.isActivityEnd = isActivityEnd;
	}
}
