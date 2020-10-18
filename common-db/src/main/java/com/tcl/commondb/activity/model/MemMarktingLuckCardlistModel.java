package com.tcl.commondb.activity.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author  maym
 * @Date 2020-07-23 14:09:19 
 */

@Entity
@Table ( name ="mem_markting_luck_cardlist")
public class MemMarktingLuckCardlistModel{


	@Id
	@GeneratedValue
	private Long id;

   	@Column(name = "card_no" ,columnDefinition="varchar(32) NULL COMMENT '券码/卡号'")
	private String cardNo;

   	@Column(name = "card_pwd" ,columnDefinition="varchar(32) NULL COMMENT '卡密/密码'")
	private String cardPwd;

   	@Column(name = "start_time" ,columnDefinition="varchar(32) NULL COMMENT '开始时间'")
	private String startTime;

   	@Column(name = "end_time" ,columnDefinition="varchar(32) NULL COMMENT '结束时间'")
	private String endTime;

   	@Column(name = "cteate_time" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
	private Timestamp cteateTime;

   	@Column(name = "status" ,columnDefinition="tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态'")
	private Integer status;

   	@Column(name = "win_id" ,columnDefinition="int(8) NOT NULL DEFAULT 0 COMMENT '中奖记录id'")
	private Long winId;

	@Column(name = "prize_id" ,columnDefinition="int(8) NOT NULL DEFAULT 0 COMMENT '奖品id'")
	private Long prizeId;
	
	@Column(name = "prize_number", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '奖品唯一编码'")
    private String prizeNumber;

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNo() {
		return this.cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardPwd() {
		return this.cardPwd;
	}
	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}

	public String getStartTime() {
		return this.startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Timestamp getCteateTime() {
		return this.cteateTime;
	}
	public void setCteateTime(Timestamp cteateTime) {
		this.cteateTime = cteateTime;
	}

	public Integer getStatus() {
		return this.status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getWinId() {
		return this.winId;
	}
	public void setWinId(Long winId) {
		this.winId = winId;
	}

	public Long getPrizeId() {
		return this.prizeId;
	}
	public void setPrizeId(Long prizeId) {
		this.prizeId = prizeId;
	}
	public String getPrizeNumber() {
		return prizeNumber;
	}
	public void setPrizeNumber(String prizeNumber) {
		this.prizeNumber = prizeNumber;
	}

}
