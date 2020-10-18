package com.tcl.commondb.activity.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activity_lottery_prize_daily_stock")
public class LotteryPrizeDailyStockModel {

    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(name = "configuration_num", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '配置编码'")
    private String configurationNum;

    @Column(name = "prize_number", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '奖品唯一编码'")
    private String prizeNumber;

    @Column(name = "stock", columnDefinition = "int(11) DEFAULT 0 COMMENT '库存'")
    private Integer stock = 0;
    
    @Column(name = "remain_stock", columnDefinition = "int(11) DEFAULT 0 COMMENT '剩余库存'")
    private Integer remainStock = 0;

    @Column(name = "day", columnDefinition = "varchar(16) COMMENT '日期: yyyy-MM-dd'")
    private String day; 
    
    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;
    
    @Column(name = "update_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'")
    private Timestamp updateTime;

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

	public String getPrizeNumber() {
		return prizeNumber;
	}

	public void setPrizeNumber(String prizeNumber) {
		this.prizeNumber = prizeNumber;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getRemainStock() {
		return remainStock;
	}

	public void setRemainStock(Integer remainStock) {
		this.remainStock = remainStock;
	}
}
