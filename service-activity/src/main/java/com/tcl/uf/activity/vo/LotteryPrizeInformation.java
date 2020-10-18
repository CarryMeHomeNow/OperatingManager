package com.tcl.uf.activity.vo;

public class LotteryPrizeInformation {
	private Integer id; // 中奖结果ID
	private String mobile;
    private String nickName;
    private String activityName;
    private String time;
    private String prizeNumber; //奖品编码
    private Integer prizeLocationIndex;//奖品索引位置
    private String prizeName;//奖品名称
    private String prizeIcon;//奖品图标
    private String prizeType;//奖品类型
    private String prizeValue;//奖品值
    private String prizeTypeTxt;
    private String exchangeStatus;
    private String exchangeInformation;
    private String cardNo; // 卡号
    private String cardPwd; // 卡密码
    private String cardStartTime; // 卡密开始时间
    private String cardEndTime; // 卡密结束时间
    private String receiver;
    private String receiverMobile;
    private String receiverAddress; // 地址
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public String getPrizeValue() {
        return prizeValue;
    }

    public void setPrizeValue(String prizeValue) {
        this.prizeValue = prizeValue;
    }

    public Integer getPrizeLocationIndex() {
        return prizeLocationIndex;
    }

    public void setPrizeLocationIndex(Integer prizeLocationIndex) {
        this.prizeLocationIndex = prizeLocationIndex;
    }

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardPwd() {
		return cardPwd;
	}

	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}

	public String getCardStartTime() {
		return cardStartTime;
	}

	public void setCardStartTime(String cardStartTime) {
		this.cardStartTime = cardStartTime;
	}

	public String getCardEndTime() {
		return cardEndTime;
	}

	public void setCardEndTime(String cardEndTime) {
		this.cardEndTime = cardEndTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPrizeTypeTxt() {
		return prizeTypeTxt;
	}

	public void setPrizeTypeTxt(String prizeTypeTxt) {
		this.prizeTypeTxt = prizeTypeTxt;
	}

	public String getExchangeStatus() {
		return exchangeStatus;
	}

	public void setExchangeStatus(String exchangeStatus) {
		this.exchangeStatus = exchangeStatus;
	}

	public String getExchangeInformation() {
		return exchangeInformation;
	}

	public void setExchangeInformation(String exchangeInformation) {
		this.exchangeInformation = exchangeInformation;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
}
