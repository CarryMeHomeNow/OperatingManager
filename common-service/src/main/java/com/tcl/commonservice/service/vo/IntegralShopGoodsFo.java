package com.tcl.commonservice.service.vo;

import java.math.BigDecimal;

public class IntegralShopGoodsFo {

	private Long id;
	private Long category;
	private String goodsName;
	private String image;
	private BigDecimal price;
	private Long pointmoney;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getPointmoney() {
		return pointmoney;
	}

	public void setPointmoney(Long pointmoney) {
		this.pointmoney = pointmoney;
	}

	@Override
	public String toString() {
		return "IntegralShopGoodsFo{" +
				"id=" + id +
				", category=" + category +
				", goodsName='" + goodsName + '\'' +
				", image='" + image + '\'' +
				", price=" + price +
				", pointmoney=" + pointmoney +
				'}';
	}
}
