package com.tcl.uf.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
@ApiModel(description = "券卡表")
public class IntegralShopVouchercardDto {

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "card_no")
	private String cardNo;

	@ApiModelProperty(value = "card_pwd")
	private String cardPwd;

	@ApiModelProperty(value = "start_time")
	private String startTime;

	@ApiModelProperty(value = "end_time")
	private String endTime;

	@ApiModelProperty(value = "cteate_time")
	private Timestamp cteateTime;

	@ApiModelProperty(value = "status")
	private Integer status;

	@ApiModelProperty(value = "order_id")
	private Long orderId;

	@ApiModelProperty(value = "goods_no")
	private String goodsNo;



  	public Long getId() {
    	return id;
  	}
  	public void setId(Long id) {
    	this.id = id;
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

  	public String getStartTime() {
    	return startTime;
  	}
  	public void setStartTime(String startTime) {
    	this.startTime = startTime;
  	}

  	public String getEndTime() {
    	return endTime;
  	}
  	public void setEndTime(String endTime) {
    	this.endTime = endTime;
  	}

  	public Timestamp getCteateTime() {
    	return cteateTime;
  	}
  	public void setCteateTime(Timestamp cteateTime) {
    	this.cteateTime = cteateTime;
  	}

  	public Integer getStatus() {
    	return status;
  	}
  	public void setStatus(Integer status) {
    	this.status = status;
  	}

  	public Long getOrderId() {
    	return orderId;
  	}
  	public void setOrderId(Long orderId) {
    	this.orderId = orderId;
  	}

  	public String getGoodsNo() {
    	return goodsNo;
  	}
  	public void setGoodsNo(String goodsNo) {
    	this.goodsNo = goodsNo;
  	}
}
