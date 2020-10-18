package com.tcl.uf.activity.vo;

public class LotteryRecordVo {
	private Integer id;
	private String mobile;
    private String nickName;
    private String activityName;
    private String prizeName;
    private String prizeValue;
    private String time;
    private String prizeType;
    private String prizeTypeTxt;
    private String exchangeStatus;
    private String exchangeInformation;
    private String cardNo; // 卡号
    private String cardPwd; // 卡密码
    private String receiver;
    private String receiverMobile;
    private String receiverAddress; // 地址
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeValue() {
		return prizeValue;
	}

	public void setPrizeValue(String prizeValue) {
		this.prizeValue = prizeValue;
	}

	public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
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

	public String getPrizeTypeTxt() {
		return prizeTypeTxt;
	}

	public void setPrizeTypeTxt(String prizeTypeTxt) {
		this.prizeTypeTxt = prizeTypeTxt;
	}
}
