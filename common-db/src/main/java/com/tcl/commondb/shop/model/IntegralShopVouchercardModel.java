package com.tcl.commondb.shop.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author  maym
 * @Date 2020-08-10 14:39:57 
 */

@Entity
@Table ( name ="integral_shop_vouchercard")
public class IntegralShopVouchercardModel{


	@Id
	@GeneratedValue
   	@Column(name = "id" ,columnDefinition="int(8) NOT NULL DEFAULT 0 COMMENT 'null'")
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

   	@Column(name = "order_id" ,columnDefinition="int(8) NOT NULL DEFAULT 0 COMMENT '订单ID'")
	private Long orderId;

   	@Column(name = "goods_no" ,columnDefinition="varchar(32) NULL COMMENT '商品编码'")
	private String goodsNo;

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

	public Long getOrderId() {
		return this.orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getGoodsNo() {
		return this.goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

}
