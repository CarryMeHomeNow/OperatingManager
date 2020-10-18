package com.tcl.uf.shop.vo;

import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

@ApiModel(description = "APP前端显示数据")
public class IntegralShopOrderFo {

	private Long id;
	private String ordersn;
	private String cardsn;
	private BigDecimal pointmoney;
	private Long total;
	private Integer status;
	private String createtime;
	private String finishtime;
	private String buyname;
	private String linkmobile;
	private String address;
	private Long pointnum;
	private String expresssn;
	private Integer comefrom;
	private String goodsname;
	private String goodsimage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}

	public String getCardsn() {
		return cardsn;
	}

	public void setCardsn(String cardsn) {
		this.cardsn = cardsn;
	}

	public BigDecimal getPointmoney() {
		return pointmoney;
	}

	public void setPointmoney(BigDecimal pointmoney) {
		this.pointmoney = pointmoney;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}

	public String getBuyname() {
		return buyname;
	}

	public void setBuyname(String buyname) {
		this.buyname = buyname;
	}

	public String getLinkmobile() {
		return linkmobile;
	}

	public void setLinkmobile(String linkmobile) {
		this.linkmobile = linkmobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPointnum() {
		return pointnum;
	}

	public void setPointnum(Long pointnum) {
		this.pointnum = pointnum;
	}

	public String getExpresssn() {
		return expresssn;
	}

	public void setExpresssn(String expresssn) {
		this.expresssn = expresssn;
	}

	public Integer getComefrom() {
		return comefrom;
	}

	public void setComefrom(Integer comefrom) {
		this.comefrom = comefrom;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodsimage() {
		return goodsimage;
	}

	public void setGoodsimage(String goodsimage) {
		this.goodsimage = goodsimage;
	}




}
