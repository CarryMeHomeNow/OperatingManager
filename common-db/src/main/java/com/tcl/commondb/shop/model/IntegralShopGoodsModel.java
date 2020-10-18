package com.tcl.commondb.shop.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author  maym
 * @Date 2020-08-27 09:36:11 
 */

@Entity
@Table ( name ="integral_shop_goods")
public class IntegralShopGoodsModel{


	@Id
	@GeneratedValue
   	@Column(name = "id" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '商品ID'")
	private Long id;

   	@Column(name = "goodssn" ,columnDefinition="varchar(32) NULL COMMENT '商品编码'")
	private String goodssn;

   	@Column(name = "sku" ,columnDefinition="varchar(32) NULL COMMENT '商品sku'")
	private String sku;

   	@Column(name = "storge" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '库存'")
	private Long storge;

   	@Column(name = "withholdnum" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '并发版本控制'")
	private Long withholdnum;

   	@Column(name = "brand_id" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '商品品类'")
	private Long brandId;

   	@Column(name = "category" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '1-实体商品，2-虚拟商品'")
	private Long category;

   	@Column(name = "catedisplay" ,columnDefinition="tinyint(1) NULL DEFAULT 0 COMMENT '是否在栏目中显示 0-不显示，1-显示'")
	private Integer catedisplay;

   	@Column(name = "goods_name" ,columnDefinition="varchar(128) NULL COMMENT '商品名称'")
	private String goodsName;

   	@Column(name = "image" ,columnDefinition="varchar(128) NULL COMMENT '商品主图'")
	private String image;

   	@Column(name = "corner_mark" ,columnDefinition="varchar(32) NULL COMMENT '角标，如：热卖、推荐、特价等'")
	private String cornerMark;

   	@Column(name = "summary" ,columnDefinition="varchar(255) NULL COMMENT '摘要'")
	private String summary;

   	@Column(name = "srvpolicy" ,columnDefinition="varchar(255) NULL COMMENT '服务政策'")
	private String srvpolicy;

   	@Column(name = "proparam" ,columnDefinition="varchar(255) NULL COMMENT '产品参数'")
	private String proparam;

   	@Column(name = "description" ,columnDefinition="text NULL COMMENT '详细介绍'")
	private String description;

   	@Column(name = "keywords" ,columnDefinition="varchar(128) NULL COMMENT '关键词'")
	private String keywords;

   	@Column(name = "price" ,columnDefinition="decimal(10, 2) NULL DEFAULT 0.00 COMMENT '官方零售价'")
	private BigDecimal price;

   	@Column(name = "min_price" ,columnDefinition="decimal(10, 2) NULL DEFAULT 0.00 COMMENT '最低交成价'")
	private BigDecimal minPrice;

   	@Column(name = "market_price" ,columnDefinition="decimal(10, 2) NULL DEFAULT 0.00 COMMENT '市场价'")
	private BigDecimal marketPrice;

   	@Column(name = "unit" ,columnDefinition="varchar(32) NULL COMMENT '计量单位'")
	private String unit;

   	@Column(name = "weight" ,columnDefinition="decimal(10, 2) NULL DEFAULT 0.00 COMMENT '单位重量'")
	private BigDecimal weight;

   	@Column(name = "pointmoney" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '积分抵扣'")
	private Long pointmoney;

   	@Column(name = "pointscale" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '积分比例'")
	private Long pointscale;

   	@Column(name = "buy_count" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '限购数量'")
	private Long buyCount;

   	@Column(name = "uptime" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '上架时间(最后一次)'")
	private Timestamp uptime;

   	@Column(name = "downtime" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '下架时间(最后一次)'")
	private Timestamp downtime;

   	@Column(name = "status" ,columnDefinition="tinyint(1) NULL DEFAULT 0 COMMENT '0-上架,1-下架,-1删除'")
	private Integer status;

   	@Column(name = "list_order" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '排序'")
	private Long listOrder;

   	@Column(name = "created_by" ,columnDefinition="tinyint(1) NULL DEFAULT 0 COMMENT '创建人'")
	private Integer createdBy;

   	@Column(name = "modified_by" ,columnDefinition="tinyint(1) NULL DEFAULT 0 COMMENT '修改人'")
	private Integer modifiedBy;

   	@Column(name = "created_time" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期'")
	private Timestamp createdTime;

   	@Column(name = "modified_time" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间'")
	private Timestamp modifiedTime;

   	@Column(name = "favorite_count" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '收藏次数'")
	private Long favoriteCount;

   	@Column(name = "sharepage" ,columnDefinition="varchar(128) NULL COMMENT '分享路径'")
	private String sharepage;

   	@Column(name = "comefrom" ,columnDefinition="tinyint(1) NULL DEFAULT 0 COMMENT '1-APP，2-小程序'")
	private Integer comefrom;

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getGoodssn() {
		return this.goodssn;
	}
	public void setGoodssn(String goodssn) {
		this.goodssn = goodssn;
	}

	public String getSku() {
		return this.sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}

	public Long getStorge() {
		return this.storge;
	}
	public void setStorge(Long storge) {
		this.storge = storge;
	}

	public Long getWithholdnum() {
		return this.withholdnum;
	}
	public void setWithholdnum(Long withholdnum) {
		this.withholdnum = withholdnum;
	}

	public Long getBrandId() {
		return this.brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getCategory() {
		return this.category;
	}
	public void setCategory(Long category) {
		this.category = category;
	}

	public Integer getCatedisplay() {
		return this.catedisplay;
	}
	public void setCatedisplay(Integer catedisplay) {
		this.catedisplay = catedisplay;
	}

	public String getGoodsName() {
		return this.goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getImage() {
		return this.image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public String getCornerMark() {
		return this.cornerMark;
	}
	public void setCornerMark(String cornerMark) {
		this.cornerMark = cornerMark;
	}

	public String getSummary() {
		return this.summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSrvpolicy() {
		return this.srvpolicy;
	}
	public void setSrvpolicy(String srvpolicy) {
		this.srvpolicy = srvpolicy;
	}

	public String getProparam() {
		return this.proparam;
	}
	public void setProparam(String proparam) {
		this.proparam = proparam;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return this.keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public BigDecimal getPrice() {
		return this.price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getMinPrice() {
		return this.minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMarketPrice() {
		return this.marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getUnit() {
		return this.unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Long getPointmoney() {
		return this.pointmoney;
	}
	public void setPointmoney(Long pointmoney) {
		this.pointmoney = pointmoney;
	}

	public Long getPointscale() {
		return this.pointscale;
	}
	public void setPointscale(Long pointscale) {
		this.pointscale = pointscale;
	}

	public Long getBuyCount() {
		return this.buyCount;
	}
	public void setBuyCount(Long buyCount) {
		this.buyCount = buyCount;
	}

	public Timestamp getUptime() {
		return this.uptime;
	}
	public void setUptime(Timestamp uptime) {
		this.uptime = uptime;
	}

	public Timestamp getDowntime() {
		return this.downtime;
	}
	public void setDowntime(Timestamp downtime) {
		this.downtime = downtime;
	}

	public Integer getStatus() {
		return this.status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getListOrder() {
		return this.listOrder;
	}
	public void setListOrder(Long listOrder) {
		this.listOrder = listOrder;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedTime() {
		return this.createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Long getFavoriteCount() {
		return this.favoriteCount;
	}
	public void setFavoriteCount(Long favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public String getSharepage() {
		return this.sharepage;
	}
	public void setSharepage(String sharepage) {
		this.sharepage = sharepage;
	}

	public Integer getComefrom() {
		return this.comefrom;
	}
	public void setComefrom(Integer comefrom) {
		this.comefrom = comefrom;
	}

}
