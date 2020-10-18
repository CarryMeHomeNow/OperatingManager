package com.tcl.commondb.activity.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activity_lottery_base_configuration")
public class LotteryBaseConfigurationModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "configuration_num", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '配置编码'")
    private String configurationNum;

    @Column(name = "activity_name", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '活动名称'")
    private String activityName;

    @Column(name = "effective_start_time", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '开始时间'")
    private String effectiveStartTime;

    @Column(name = "effective_end_time", columnDefinition = "varchar(33) DEFAULT NULL COMMENT '结束时间'")
    private String effectiveEndTime;

    @Column(name = "every_day_limit", columnDefinition = "int(11) DEFAULT 0 COMMENT '每日抽奖限制'")
    private Integer everyDayLimit;

    @Column(name = "every_day_free", columnDefinition = "int(11) DEFAULT 0 COMMENT '每日免费抽奖次数'")
    private Integer everyDayFree;

    @Column(name = "cost_gold_number", columnDefinition = "int(11) DEFAULT 0 COMMENT '每次消耗金币数'")
    private Integer costGoldNumber;

    @Column(name = "activity_rule", columnDefinition = "varchar(200) DEFAULT '' COMMENT '活动规则描述'")
    private String activityRule;

    @Column(name = "open_status", columnDefinition = "varchar(2) DEFAULT NULL COMMENT '是否开启0:否 1:是'")
    private String openStatus;

    @Column(name = "creator", columnDefinition = "varchar(20) COMMENT '创建人'")
    private String creator;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    @Column(name = "use_status", columnDefinition = "int(1) DEFAULT 1 COMMENT '伪删除 1:使用, 0:逻辑删除'")
    private Integer useStatus;

    @Column(name = "type", columnDefinition = "varchar(20) COMMENT '抽奖类型'")
    private String type;
    
    @Column(name = "every_day_share_add_limit", columnDefinition = "int(8) COMMENT '每日分享邀请新用户增加次数限制'")
    private Integer everyDayShareAddLimit;
    
    @Column(name = "share_add_free", columnDefinition = "int(2) DEFAULT 1 COMMENT '分享获得的次数是否免费，1=是，0=否'")
    private Integer shareAddFree = 1; 
    
    @Column(name = "share_add_prize_code", columnDefinition = "varchar(50) COMMENT '分享获得的次数抽奖结果设置'")
    private String shareAddPrizeCode; 
    
    @Column(name = "win_setting_period", columnDefinition = "int(2) DEFAULT 1 COMMENT '必中周期，1=永久，2=日'")
    private Integer winSettingPeriod = 1;
    
    @Column(name = "prize_period", columnDefinition = "int(2) DEFAULT 1 COMMENT '库存周期，1=永久，2=日'")
    private Integer prizePeriod = 1; 
    
    @Column(name = "broadcast_interval", columnDefinition = "int(8) COMMENT '多久显示以下其中一项'")
    private Integer broadcastInterval; 
    
    @Column(name = "outoff_stock_prize_code", columnDefinition = "varchar(50) COMMENT '中了奖项没库存时'")
    private String outoffStockPrizeCode; 

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
