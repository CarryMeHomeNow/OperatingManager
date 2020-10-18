package com.tcl.uf.shop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
@ApiModel(description = "订单列表显示")
public class IntegralShopOrderVo {

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "unionid")
	private String unionid;

	@ApiModelProperty(value = "memuid")
	private Long memuid;

	@ApiModelProperty(value = "openid")
	private String openid;

	@ApiModelProperty(value = "ordersn")
	private String ordersn;

	@ApiModelProperty(value = "ordersn2")
	private String ordersn2;

	@ApiModelProperty(value = "goodsid")
	private Long goodsid;

	@ApiModelProperty(value = "goodsprice")
	private BigDecimal goodsprice;

	@ApiModelProperty(value = "cardsn")
	private String cardsn;

	@ApiModelProperty(value = "total")
	private Long total;

	@ApiModelProperty(value = "price")
	private BigDecimal price;

	@ApiModelProperty(value = "oldprice")
	private BigDecimal oldprice;

	@ApiModelProperty(value = "changeprice")
	private BigDecimal changeprice;

	@ApiModelProperty(value = "status")
	private Integer status;

	@ApiModelProperty(value = "refund_status")
	private Integer refundStatus;

	@ApiModelProperty(value = "paytype")
	private Integer paytype;

	@ApiModelProperty(value = "createtime")
	private String createtime;

	@ApiModelProperty(value = "updatetime")
	private String updatetime;

	@ApiModelProperty(value = "payovertime")
	private Long payovertime;

	@ApiModelProperty(value = "transid")
	private String transid;

	@ApiModelProperty(value = "paytime")
	private String paytime;

	@ApiModelProperty(value = "remark")
	private String remark;

	@ApiModelProperty(value = "buyname")
	private String buyname;

	@ApiModelProperty(value = "linkmobile")
	private String linkmobile;

	@ApiModelProperty(value = "addressid")
	private Long addressid;

	@ApiModelProperty(value = "address")
	private String address;

	@ApiModelProperty(value = "pointprice")
	private BigDecimal pointprice;

	@ApiModelProperty(value = "pointnum")
	private Long pointnum;

	@ApiModelProperty(value = "deleted")
	private Integer deleted;

	@ApiModelProperty(value = "userdeleted")
	private Integer userdeleted;

	@ApiModelProperty(value = "invoice_type")
	private Integer invoiceType;

	@ApiModelProperty(value = "invoice_name")
	private String invoiceName;

	@ApiModelProperty(value = "invoice_identi_number")
	private String invoiceIdentiNumber;

	@ApiModelProperty(value = "invoice_mobile")
	private String invoiceMobile;

	@ApiModelProperty(value = "email")
	private String email;

	@ApiModelProperty(value = "expresscom")
	private String expresscom;

	@ApiModelProperty(value = "expresssn")
	private String expresssn;

	@ApiModelProperty(value = "expresstime")
	private String expresstime;

	@ApiModelProperty(value = "receovertime")
	private Long receovertime;

	@ApiModelProperty(value = "isnc")
	private Integer isnc;

	@ApiModelProperty(value = "comefrom")
	private Integer comefrom;

	private String goodsname;
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	private String goodsimage;
	public String getGoodsimage() {
		return goodsimage;
	}
	public void setGoodsimage(String goodsimage) {
		this.goodsimage = goodsimage;
	}


  	public Long getId() {
    	return id;
  	}
  	public void setId(Long id) {
    	this.id = id;
  	}

  	public String getUnionid() {
    	return unionid;
  	}
  	public void setUnionid(String unionid) {
    	this.unionid = unionid;
  	}

  	public Long getMemuid() {
    	return memuid;
  	}
  	public void setMemuid(Long memuid) {
    	this.memuid = memuid;
  	}

  	public String getOpenid() {
    	return openid;
  	}
  	public void setOpenid(String openid) {
    	this.openid = openid;
  	}

  	public String getOrdersn() {
    	return ordersn;
  	}
  	public void setOrdersn(String ordersn) {
    	this.ordersn = ordersn;
  	}

  	public String getOrdersn2() {
    	return ordersn2;
  	}
  	public void setOrdersn2(String ordersn2) {
    	this.ordersn2 = ordersn2;
  	}

  	public Long getGoodsid() {
    	return goodsid;
  	}
  	public void setGoodsid(Long goodsid) {
    	this.goodsid = goodsid;
  	}

  	public BigDecimal getGoodsprice() {
    	return goodsprice;
  	}
  	public void setGoodsprice(BigDecimal goodsprice) {
    	this.goodsprice = goodsprice;
  	}

  	public String getCardsn() {
    	return cardsn;
  	}
  	public void setCardsn(String cardsn) {
    	this.cardsn = cardsn;
  	}

  	public Long getTotal() {
    	return total;
  	}
  	public void setTotal(Long total) {
    	this.total = total;
  	}

  	public BigDecimal getPrice() {
    	return price;
  	}
  	public void setPrice(BigDecimal price) {
    	this.price = price;
  	}

  	public BigDecimal getOldprice() {
    	return oldprice;
  	}
  	public void setOldprice(BigDecimal oldprice) {
    	this.oldprice = oldprice;
  	}

  	public BigDecimal getChangeprice() {
    	return changeprice;
  	}
  	public void setChangeprice(BigDecimal changeprice) {
    	this.changeprice = changeprice;
  	}

  	public Integer getStatus() {
    	return status;
  	}
  	public void setStatus(Integer status) {
    	this.status = status;
  	}

  	public Integer getRefundStatus() {
    	return refundStatus;
  	}
  	public void setRefundStatus(Integer refundStatus) {
    	this.refundStatus = refundStatus;
  	}

  	public Integer getPaytype() {
    	return paytype;
  	}
  	public void setPaytype(Integer paytype) {
    	this.paytype = paytype;
  	}

  	public String getCreatetime() {
    	return createtime;
  	}
  	public void setCreatetime(String createtime) {
    	this.createtime = createtime;
  	}

  	public String getUpdatetime() {
    	return updatetime;
  	}
  	public void setUpdatetime(String updatetime) {
    	this.updatetime = updatetime;
  	}

  	public Long getPayovertime() {
    	return payovertime;
  	}
  	public void setPayovertime(Long payovertime) {
    	this.payovertime = payovertime;
  	}

  	public String getTransid() {
    	return transid;
  	}
  	public void setTransid(String transid) {
    	this.transid = transid;
  	}

  	public String getPaytime() {
    	return paytime;
  	}
  	public void setPaytime(String paytime) {
    	this.paytime = paytime;
  	}

  	public String getRemark() {
    	return remark;
  	}
  	public void setRemark(String remark) {
    	this.remark = remark;
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

  	public Long getAddressid() {
    	return addressid;
  	}
  	public void setAddressid(Long addressid) {
    	this.addressid = addressid;
  	}

  	public String getAddress() {
    	return address;
  	}
  	public void setAddress(String address) {
    	this.address = address;
  	}

  	public BigDecimal getPointprice() {
    	return pointprice;
  	}
  	public void setPointprice(BigDecimal pointprice) {
    	this.pointprice = pointprice;
  	}

  	public Long getPointnum() {
    	return pointnum;
  	}
  	public void setPointnum(Long pointnum) {
    	this.pointnum = pointnum;
  	}

  	public Integer getDeleted() {
    	return deleted;
  	}
  	public void setDeleted(Integer deleted) {
    	this.deleted = deleted;
  	}

  	public Integer getUserdeleted() {
    	return userdeleted;
  	}
  	public void setUserdeleted(Integer userdeleted) {
    	this.userdeleted = userdeleted;
  	}

  	public Integer getInvoiceType() {
    	return invoiceType;
  	}
  	public void setInvoiceType(Integer invoiceType) {
    	this.invoiceType = invoiceType;
  	}

  	public String getInvoiceName() {
    	return invoiceName;
  	}
  	public void setInvoiceName(String invoiceName) {
    	this.invoiceName = invoiceName;
  	}

  	public String getInvoiceIdentiNumber() {
    	return invoiceIdentiNumber;
  	}
  	public void setInvoiceIdentiNumber(String invoiceIdentiNumber) {
    	this.invoiceIdentiNumber = invoiceIdentiNumber;
  	}

  	public String getInvoiceMobile() {
    	return invoiceMobile;
  	}
  	public void setInvoiceMobile(String invoiceMobile) {
    	this.invoiceMobile = invoiceMobile;
  	}

  	public String getEmail() {
    	return email;
  	}
  	public void setEmail(String email) {
    	this.email = email;
  	}

  	public String getExpresscom() {
    	return expresscom;
  	}
  	public void setExpresscom(String expresscom) {
    	this.expresscom = expresscom;
  	}

  	public String getExpresssn() {
    	return expresssn;
  	}
  	public void setExpresssn(String expresssn) {
    	this.expresssn = expresssn;
  	}

  	public String getExpresstime() {
    	return expresstime;
  	}
  	public void setExpresstime(String expresstime) {
    	this.expresstime = expresstime;
  	}

  	public Long getReceovertime() {
    	return receovertime;
  	}
  	public void setReceovertime(Long receovertime) {
    	this.receovertime = receovertime;
  	}

  	public Integer getIsnc() {
    	return isnc;
  	}
  	public void setIsnc(Integer isnc) {
    	this.isnc = isnc;
  	}

  	public Integer getComefrom() {
    	return comefrom;
  	}
  	public void setComefrom(Integer comefrom) {
    	this.comefrom = comefrom;
  	}
}
