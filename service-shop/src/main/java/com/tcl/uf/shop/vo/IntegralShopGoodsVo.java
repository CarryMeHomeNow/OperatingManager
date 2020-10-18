package com.tcl.uf.shop.vo;

import com.tcl.uf.shop.dto.IntegralShopImagesDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@ApiModel(description = "商品表")
public class IntegralShopGoodsVo {

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "goodssn")
	private String goodssn;

	@ApiModelProperty(value = "sku")
	private String sku;

	@ApiModelProperty(value = "storge")
	private Long storge;

	@ApiModelProperty(value = "withholdnum")
	private Long withholdnum;

	@ApiModelProperty(value = "brand_id")
	private Long brandId;

	private String brandName;

	@ApiModelProperty(value = "category")
	private Long category;

	@ApiModelProperty(value = "catedisplay")
	private Integer catedisplay;

	@ApiModelProperty(value = "goods_name")
	private String goodsName;

	@ApiModelProperty(value = "image")
	private String image;

	@ApiModelProperty(value = "corner_mark")
	private String cornerMark;

	@ApiModelProperty(value = "summary")
	private String summary;

	@ApiModelProperty(value = "srvpolicy")
	private String srvpolicy;
	private List<String> srvpolicyimgs;
	public void setSrvpolicyimgs(List<String> srvpolicyimgs)
	{
		this.srvpolicyimgs = srvpolicyimgs;
	}
	public List<String> getSrvpolicyimgs()
	{
		return srvpolicyimgs;
	}

	@ApiModelProperty(value = "proparam")
	private String proparam;

	private List<String> proparamimgs;
	public void setProparamimgs(List<String> proparamimgs)
	{
		this.proparamimgs = proparamimgs;
	}
	public List<String> getProparamimgs()
	{
		return proparamimgs;
	}

	@ApiModelProperty(value = "description")
	private String description;
	private List<String> descimgs;
	public void setDescimgs(List<String> descimgs)
	{
		this.descimgs = descimgs;
	}
	public List<String> getDescimgs()
	{
		return descimgs;
	}

	@ApiModelProperty(value = "keywords")
	private String keywords;

	@ApiModelProperty(value = "price")
	private BigDecimal price;

	@ApiModelProperty(value = "min_price")
	private BigDecimal minPrice;

	@ApiModelProperty(value = "market_price")
	private BigDecimal marketPrice;

	@ApiModelProperty(value = "unit")
	private String unit;

	@ApiModelProperty(value = "weight")
	private BigDecimal weight;

	@ApiModelProperty(value = "pointmoney")
	private Long pointmoney;

	@ApiModelProperty(value = "pointscale")
	private Long pointscale;

	@ApiModelProperty(value = "buy_count")
	private Long buyCount;

	@ApiModelProperty(value = "uptime")
	private String uptime;

	@ApiModelProperty(value = "downtime")
	private String downtime;

	@ApiModelProperty(value = "status")
	private Integer status;

	@ApiModelProperty(value = "list_order")
	private Long listOrder;

	@ApiModelProperty(value = "created_by")
	private Integer createdBy;

	@ApiModelProperty(value = "modified_by")
	private Integer modifiedBy;

	@ApiModelProperty(value = "created_time")
	private String createdTime;

	@ApiModelProperty(value = "modified_time")
	private String modifiedTime;

	@ApiModelProperty(value = "favorite_count")
	private Long favoriteCount;

	@ApiModelProperty(value = "sharepage")
	private String sharepage;

	@ApiModelProperty(value = "comefrom")
	private Integer comefrom;

	@ApiModelProperty(value = "商品关联图片", example = "")
	private List<IntegralShopImagesDto> shopImagesDtoList;

	public void setShopImagesDtoList(List<IntegralShopImagesDto> shopImagesDtoList)
	{
		this.shopImagesDtoList = shopImagesDtoList;
	}
	public List<IntegralShopImagesDto> getShopImagesDtoList()
	{
		return shopImagesDtoList;
	}


  	public Long getId() {
    	return id;
  	}
  	public void setId(Long id) {
    	this.id = id;
  	}

  	public String getGoodssn() {
    	return goodssn;
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
    	return storge;
  	}
  	public void setStorge(Long storge) {
    	this.storge = storge;
  	}

  	public Long getWithholdnum() {
    	return withholdnum;
  	}
  	public void setWithholdnum(Long withholdnum) {
    	this.withholdnum = withholdnum;
  	}

  	public Long getBrandId() {
    	return brandId;
  	}
  	public void setBrandId(Long brandId) {
    	this.brandId = brandId;
  	}

	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

  	public Long getCategory() {
    	return category;
  	}
  	public void setCategory(Long category) {
    	this.category = category;
  	}

  	public Integer getCatedisplay() {
    	return catedisplay;
  	}
  	public void setCatedisplay(Integer catedisplay) {
    	this.catedisplay = catedisplay;
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

  	public String getCornerMark() {
    	return cornerMark;
  	}
  	public void setCornerMark(String cornerMark) {
    	this.cornerMark = cornerMark;
  	}

  	public String getSummary() {
    	return summary;
  	}
  	public void setSummary(String summary) {
    	this.summary = summary;
  	}

  	public String getSrvpolicy() {
    	return srvpolicy;
  	}
  	public void setSrvpolicy(String srvpolicy) {
    	this.srvpolicy = srvpolicy;
  	}

  	public String getProparam() {
    	return proparam;
  	}
  	public void setProparam(String proparam) {
    	this.proparam = proparam;
  	}

  	public String getDescription() {
    	return description;
  	}
  	public void setDescription(String description) {
    	this.description = description;
  	}

  	public String getKeywords() {
    	return keywords;
  	}
  	public void setKeywords(String keywords) {
    	this.keywords = keywords;
  	}

  	public BigDecimal getPrice() {
    	return price;
  	}
  	public void setPrice(BigDecimal price) {
    	this.price = price;
  	}

  	public BigDecimal getMinPrice() {
    	return minPrice;
  	}
  	public void setMinPrice(BigDecimal minPrice) {
    	this.minPrice = minPrice;
  	}

  	public BigDecimal getMarketPrice() {
    	return marketPrice;
  	}
  	public void setMarketPrice(BigDecimal marketPrice) {
    	this.marketPrice = marketPrice;
  	}

  	public String getUnit() {
    	return unit;
  	}
  	public void setUnit(String unit) {
    	this.unit = unit;
  	}

  	public BigDecimal getWeight() {
    	return weight;
  	}
  	public void setWeight(BigDecimal weight) {
    	this.weight = weight;
  	}

  	public Long getPointmoney() {
    	return pointmoney;
  	}
  	public void setPointmoney(Long pointmoney) {
    	this.pointmoney = pointmoney;
  	}

  	public Long getPointscale() {
    	return pointscale;
  	}
  	public void setPointscale(Long pointscale) {
    	this.pointscale = pointscale;
  	}

  	public Long getBuyCount() {
    	return buyCount;
  	}
  	public void setBuyCount(Long buyCount) {
    	this.buyCount = buyCount;
  	}

  	public String getUptime() {
    	return uptime;
  	}
  	public void setUptime(String uptime) {
    	this.uptime = uptime;
  	}

  	public String getDowntime() {
    	return downtime;
  	}
  	public void setDowntime(String downtime) {
    	this.downtime = downtime;
  	}

  	public Integer getStatus() {
    	return status;
  	}
  	public void setStatus(Integer status) {
    	this.status = status;
  	}

  	public Long getListOrder() {
    	return listOrder;
  	}
  	public void setListOrder(Long listOrder) {
    	this.listOrder = listOrder;
  	}

  	public Integer getCreatedBy() {
    	return createdBy;
  	}
  	public void setCreatedBy(Integer createdBy) {
    	this.createdBy = createdBy;
  	}

  	public Integer getModifiedBy() {
    	return modifiedBy;
  	}
  	public void setModifiedBy(Integer modifiedBy) {
    	this.modifiedBy = modifiedBy;
  	}

  	public String getCreatedTime() {
    	return createdTime;
  	}
  	public void setCreatedTime(String createdTime) {
    	this.createdTime = createdTime;
  	}

  	public String getModifiedTime() {
    	return modifiedTime;
  	}
  	public void setModifiedTime(String modifiedTime) {
    	this.modifiedTime = modifiedTime;
  	}

  	public Long getFavoriteCount() {
    	return favoriteCount;
  	}
  	public void setFavoriteCount(Long favoriteCount) {
    	this.favoriteCount = favoriteCount;
  	}

  	public String getSharepage() {
    	return sharepage;
  	}
  	public void setSharepage(String sharepage) {
    	this.sharepage = sharepage;
  	}

  	public Integer getComefrom() {
    	return comefrom;
  	}
  	public void setComefrom(Integer comefrom) {
    	this.comefrom = comefrom;
  	}
}
