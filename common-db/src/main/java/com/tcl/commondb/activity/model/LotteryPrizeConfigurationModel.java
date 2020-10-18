package com.tcl.commondb.activity.model;

import javax.persistence.*;

@Entity
@Table(name = "activity_lottery_prize_configuration")
public class LotteryPrizeConfigurationModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "prize_number", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '奖品唯一编码'")
    private String prizeNumber;

    @Column(name = "prize_name", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '奖品名称'")
    private String prizeName;

    @Column(name = "prize_icon_url", columnDefinition = "varchar(150) DEFAULT NULL COMMENT '奖品图片链接'")
    private String prizeIcon;

    @Column(name = "prize_probability", columnDefinition = "varchar(10) DEFAULT NULL COMMENT '中奖概率->中奖公式'")
    private String prizeProbability;

    @Column(name = "prize_total_number", columnDefinition = "int(11) DEFAULT 0 COMMENT '奖品配置总数->库存'")
    private Integer prizeTotalNumber;

    @Column(name = "prize_remainder_number", columnDefinition = "int(11) DEFAULT 0 COMMENT '奖品剩余数量'")
    private Integer prizeRemainderNumber;

    @Column(name = "prize_type", columnDefinition = "varchar(2) DEFAULT NULL COMMENT '奖品类别：1=实物, 2=金币,3=积分, 4=优惠券,5=兑换券,6=谢谢参与,7=赠送次数,8=现金'")
    private String prizeType;

    @Column(name = "prize_value", columnDefinition = "varchar(10) DEFAULT NULL COMMENT '奖品类别配置值'")
    private String prizeValue;

    @Column(name = "prize_location_index", columnDefinition = "int(5) COMMENT '奖品位置索引编码'")
    private Integer prizeLocationIndex;

    @Column(name = "configuration_num", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '配置编码'")
    private String configurationNum;

    @Column(name = "use_status", columnDefinition = "int(1) DEFAULT 1 COMMENT '伪删除 1:使用, 0:逻辑删除'")
    private Integer useStatus;
    
    @Column(name = "win_time", columnDefinition = "int(2) DEFAULT 1 COMMENT '中奖固定时间，1=每日，2=每月最后N天'")
    private Integer winTime = 1; 
    
    @Column(name = "win_time_value", columnDefinition = "int(4) COMMENT '每月最后N天'")
    private Integer winTimeValue; 
    
    @Column(name = "win_type", columnDefinition = "int(2) DEFAULT 3 COMMENT '中奖设置，1=人数，2=次数，3=概率'")
    private Integer winType = 3;
    
    @Column(name = "infinity_total", columnDefinition = "int(2) DEFAULT 0 COMMENT '库存无限量，1=是，0=不是'")
    private Integer infinityTotal = 0;
    
    @Column(name = "coupon_url", columnDefinition = "varchar(255) COMMENT '券码地址'")
    private String couponUrl; 
    
    @Column(name = "notify_user", columnDefinition = "int(2) COMMENT '消息通知，0=不通知，1=短信'")
    private Integer notifyUser = 0; 
    
    @Column(name = "sms_tpl", columnDefinition = "varchar(255) COMMENT '短信模板'")
    private String smsTpl;

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

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
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
