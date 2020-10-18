package com.tcl.commondb.activity.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activity_lottery_prize_result_record")
public class LotteryPrizeRecordModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "configuration_num", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '抽奖配置关联编码'")
    private String configurationNum;

    @Column(name = "chance_id", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '抽奖机会编码'")
    private String chanceId;

    @Column(name = "open_id", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '当前抽奖用户身份标识'")
    private String openId;

    @Column(name = "nick_name", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '用户昵称'")
    private String nickName;

    @Column(name = "activity_name", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '活动名称'")
    private String activityName;

    @Column(name = "prize_id", columnDefinition = "int(11) COMMENT '奖品ID'")
    private Integer prizeId;
    
    @Column(name = "prize_number", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '奖品唯一编码'")
    private String prizeNumber;

    @Column(name = "prize_name", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '奖品名称'")
    private String prizeName;

    @Column(name = "prize_icon_url", columnDefinition = "varchar(150) DEFAULT NULL COMMENT '奖品图片链接'")
    private String prizeIcon;

    @Column(name = "prize_type", columnDefinition = "varchar(3) DEFAULT NULL COMMENT '奖品类别：1=实物, 2=金币,3=积分, 4=优惠券,5=兑换券,6=谢谢参与,7=赠送次数,8=现金'")
    private String prizeType;

    @Column(name = "prize_value", columnDefinition = "varchar(10) DEFAULT NULL COMMENT '奖品类别配置值'")
    private String prizeValue;

    @Column(name = "exchange_status", columnDefinition = "varchar(10) DEFAULT NULL COMMENT '奖品兑付状态 1:已兑换 2:未兑换'")
    private String exchangeStatus;

    @Column(name = "exchange_information", columnDefinition = "varchar(10) DEFAULT NULL COMMENT '奖品兑付信息'")
    private String exchangeInformation;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;
    
    @Column(name = "update_time", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'")
    private Timestamp updateTime;

    @Column(name = "business_time", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '业务日期'")
    private String businessTime;

    @Column(name = "source", columnDefinition = "varchar(2) DEFAULT NULL COMMENT '机会获得来源[0:每日系统自动发放 1:问卷 2:每日赠送 3:九宫格中奖 4:大屏活动 5:天天抽锦鲤 6:分享获得]'")
    private String source;

    @Column(name = "type", columnDefinition = "varchar(20) COMMENT '抽奖类型'")
    private String type;
    
    @Column(name = "card_id", columnDefinition = "int(11) COMMENT '卡密ID'")
    private Long cardId;
    
    @Column(name = "card_no" ,columnDefinition="varchar(32) NULL COMMENT '券码/卡号'")
	private String cardNo;

   	@Column(name = "card_pwd" ,columnDefinition="varchar(32) NULL COMMENT '卡密/密码'")
	private String cardPwd;
    
    @Column(name = "addr_id", columnDefinition = "int(11) COMMENT '地址ID'")
    private Long addrId;
    
    @Column(name = "win_time", columnDefinition = "int(2) DEFAULT 1 COMMENT '中奖固定时间，1=每日，2=每月最后N天'")
    private Integer winTime = 1; 
    
    @Column(name = "win_time_value", columnDefinition = "int(4) COMMENT '每月最后N天'")
    private Integer winTimeValue; 
    
    @Column(name = "win_type", columnDefinition = "int(2) COMMENT '中奖设置，1=人数，2=次数，3=概率'")
    private Integer winType;
    
    @Column(name = "prize_probability", columnDefinition = "varchar(10) DEFAULT NULL COMMENT '中奖概率->中奖公式'")
    private String prizeProbability;
    
    @Column(name = "infinity_total", columnDefinition = "int(2) DEFAULT 0 COMMENT '库存无限量，1=是，0=不是'")
    private Integer infinityTotal = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigurationNum() {
        return configurationNum;
    }

    public void setConfigurationNum(String configurationNum) {
        this.configurationNum = configurationNum;
    }

    public String getChanceId() {
        return chanceId;
    }

    public void setChanceId(String chanceId) {
        this.chanceId = chanceId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getAddrId() {
		return addrId;
	}

	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
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

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
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

	public String getPrizeProbability() {
		return prizeProbability;
	}

	public void setPrizeProbability(String prizeProbability) {
		this.prizeProbability = prizeProbability;
	}

	public Integer getInfinityTotal() {
		return infinityTotal;
	}

	public void setInfinityTotal(Integer infinityTotal) {
		this.infinityTotal = infinityTotal;
	}
}
