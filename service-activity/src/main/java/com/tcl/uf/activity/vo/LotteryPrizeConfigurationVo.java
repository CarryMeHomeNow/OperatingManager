package com.tcl.uf.activity.vo;

public class LotteryPrizeConfigurationVo {

    private String prizeNumber;
    private String prizeName;
    private String prizeIcon;
    private String prizeProbability;
    private Integer prizeTotalNumber;
    private Integer prizeRemainderNumber;
    private String prizeType;
    private Integer prizeLocationIndex;
    private String configurationNum;
    private String prizeValue;
    
    private Integer winTime; // 中奖固定时间，1=每日，2=每月最后N天
    private Integer winTimeValue; // 每月最后N天
    private Integer winType; // 中奖设置，1=人数，2=次数，3=概率
    private Integer infinityTotal; // 库存无限量，1=是，其他=不是
    private String couponUrl; // 券码地址
    private Integer couponTotal = 0; // 券码总数
    private Integer couponUse = 0; // 券码已用数
    private Integer couponNotUse = 0; // 券码可用数
    private Integer couponExpired = 0; // 券码过期数
    private Integer notifyUser; // 消息通知，0=不通知，1=短信
    private String smsTpl; // 短信模板

    public String getPrizeNumber() {
        return prizeNumber;
    }

    public void setPrizeNumber(String prizeNumber) {
        this.prizeNumber = prizeNumber;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeIcon() {
        return prizeIcon;
    }

    public void setPrizeIcon(String prizeIcon) {
        this.prizeIcon = prizeIcon;
    }

    public String getPrizeProbability() {
        return prizeProbability;
    }

    public void setPrizeProbability(String prizeProbability) {
        this.prizeProbability = prizeProbability;
    }

    public Integer getPrizeTotalNumber() {
        return prizeTotalNumber;
    }

    public void setPrizeTotalNumber(Integer prizeTotalNumber) {
        this.prizeTotalNumber = prizeTotalNumber;
    }

    public Integer getPrizeRemainderNumber() {
        return prizeRemainderNumber;
    }

    public void setPrizeRemainderNumber(Integer prizeRemainderNumber) {
        this.prizeRemainderNumber = prizeRemainderNumber;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public Integer getPrizeLocationIndex() {
        return prizeLocationIndex;
    }

    public void setPrizeLocationIndex(Integer prizeLocationIndex) {
        this.prizeLocationIndex = prizeLocationIndex;
    }

    public String getConfigurationNum() {
        return configurationNum;
    }

    public void setConfigurationNum(String configurationNum) {
        this.configurationNum = configurationNum;
    }

    public String getPrizeValue() {
        return prizeValue;
    }

    public void setPrizeValue(String prizeValue) {
        this.prizeValue = prizeValue;
    }

	public Integer getWinTime() {
		return winTime;
	}

	public void setWinTime(Integer winTime) {
		this.winTime = winTime;
	}

	public Integer getWinTimeValue() {
		return winTimeValue;
	}

	public void setWinTimeValue(Integer winTimeValue) {
		this.winTimeValue = winTimeValue;
	}

	public Integer getWinType() {
		return winType;
	}

	public void setWinType(Integer winType) {
		this.winType = winType;
	}

	public Integer getInfinityTotal() {
		return infinityTotal;
	}

	public void setInfinityTotal(Integer infinityTotal) {
		this.infinityTotal = infinityTotal;
	}

	public String getCouponUrl() {
		return couponUrl;
	}

	public void setCouponUrl(String couponUrl) {
		this.couponUrl = couponUrl;
	}

	public Integer getNotifyUser() {
		return notifyUser;
	}

	public void setNotifyUser(Integer notifyUser) {
		this.notifyUser = notifyUser;
	}

	public String getSmsTpl() {
		return smsTpl;
	}

	public void setSmsTpl(String smsTpl) {
		this.smsTpl = smsTpl;
	}

	public Integer getCouponTotal() {
		return couponTotal;
	}

	public void setCouponTotal(Integer couponTotal) {
		this.couponTotal = couponTotal;
	}

	public Integer getCouponUse() {
		return couponUse;
	}

	public void setCouponUse(Integer couponUse) {
		this.couponUse = couponUse;
	}

	public Integer getCouponNotUse() {
		return couponNotUse;
	}

	public void setCouponNotUse(Integer couponNotUse) {
		this.couponNotUse = couponNotUse;
	}

	public Integer getCouponExpired() {
		return couponExpired;
	}

	public void setCouponExpired(Integer couponExpired) {
		this.couponExpired = couponExpired;
	}
}
