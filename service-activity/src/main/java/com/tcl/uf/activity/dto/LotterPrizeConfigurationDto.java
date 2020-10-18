package com.tcl.uf.activity.dto;

public class LotterPrizeConfigurationDto {
	private String configurationNum; // 转盘编码
    private String prizeNumber; // 奖品编码
    private String prizeName; // 奖品名称
    private String prizeIcon; // 奖品图片
    private Integer winTime; // 中奖固定时间，1=每日，2=每月最后N天
    private Integer winTimeValue; // 每月最后N天
    private Integer winType; // 中奖设置，1=人数，2=次数，3=概率
    private String prizeProbability; // 中奖公式
    private Integer prizeTotalNumber; // 库存
    private Integer infinityTotal; // 库存无限量，1=是，0=不是
    private Integer prizeRemainderNumber;
    private String prizeType; // 奖品类型   1=实物, 2=金币,3=积分, 4=优惠券,5=兑换券,6=谢谢参与,7=赠送次数,8=现金
    private Integer prizeLocationIndex; // 排序
    private String prizeValue; // 积分/金币/现金/次数
    private String couponUrl; // 券码地址
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
}
