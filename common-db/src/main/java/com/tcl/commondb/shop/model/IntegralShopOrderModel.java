package com.tcl.commondb.shop.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author  maym
 * @Date 2020-08-13 16:52:37 
 */

@Entity
@Table ( name ="integral_shop_order")
public class IntegralShopOrderModel{


	@Id
	@GeneratedValue
   	@Column(name = "id" ,columnDefinition="int(8) NOT NULL DEFAULT 0 COMMENT 'null'")
	private Long id;

   	@Column(name = "unionid" ,columnDefinition="varchar(64) NULL COMMENT 'null'")
	private String unionid;

   	@Column(name = "memuid" ,columnDefinition="int(8) NULL COMMENT 'TCL会员uid'")
	private Long memuid;

   	@Column(name = "openid" ,columnDefinition="varchar(64) NULL COMMENT 'null'")
	private String openid;

   	@Column(name = "ordersn" ,columnDefinition="varchar(64) NULL COMMENT '订单号'")
	private String ordersn;

   	@Column(name = "ordersn2" ,columnDefinition="varchar(32) NULL COMMENT '支付订单号'")
	private String ordersn2;

   	@Column(name = "goodsid" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '商品id'")
	private Long goodsid;

   	@Column(name = "goodsprice" ,columnDefinition="decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品金额'")
	private BigDecimal goodsprice;

   	@Column(name = "cardsn" ,columnDefinition="varchar(32) NULL COMMENT '券码'")
	private String cardsn;

   	@Column(name = "total" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '购买数量'")
	private Long total;

   	@Column(name = "price" ,columnDefinition="decimal(10, 2) NULL DEFAULT 0.00 COMMENT '应收款 订单金额(含运费)'")
	private BigDecimal price;

   	@Column(name = "oldprice" ,columnDefinition="decimal(10, 2) NULL DEFAULT 0.00 COMMENT '原订单金额(含运费)'")
	private BigDecimal oldprice;

   	@Column(name = "changeprice" ,columnDefinition="decimal(10, 2) NULL DEFAULT 0.00 COMMENT '订单改价金额'")
	private BigDecimal changeprice;

   	@Column(name = "status" ,columnDefinition="tinyint(1) NULL DEFAULT 0 COMMENT '0待付款 1待发货 2已发货 3交易成功 4交易关闭 5交易取消'")
	private Integer status;

   	@Column(name = "refund_status" ,columnDefinition="tinyint(1) NULL DEFAULT 0 COMMENT '退款状态'")
	private Integer refundStatus;

   	@Column(name = "paytype" ,columnDefinition="tinyint(1) NULL DEFAULT 0 COMMENT '支付类型 1 微信支付 2 支付宝支付 3 银联支付'")
	private Integer paytype;

   	@Column(name = "createtime" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间'")
	private Timestamp createtime;

   	@Column(name = "updatetime" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间'")
	private Timestamp updatetime;

   	@Column(name = "payovertime" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT 'null'")
	private Long payovertime;

   	@Column(name = "transid" ,columnDefinition="varchar(64) NULL COMMENT '微信支付单号'")
	private String transid;

   	@Column(name = "paytime" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间'")
	private Timestamp paytime;

   	@Column(name = "remark" ,columnDefinition="varchar(255) NULL COMMENT '买家备注'")
	private String remark;

   	@Column(name = "buyname" ,columnDefinition="varchar(32) NULL COMMENT '购买人'")
	private String buyname;

   	@Column(name = "linkmobile" ,columnDefinition="varchar(32) NULL COMMENT '联系电话'")
	private String linkmobile;

   	@Column(name = "addressid" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '收货地址ID'")
	private Long addressid;

   	@Column(name = "address" ,columnDefinition="varchar(255) NULL COMMENT '地址'")
	private String address;

   	@Column(name = "pointprice" ,columnDefinition="decimal(10, 2) NULL DEFAULT 0.00 COMMENT '积分抵扣价格'")
	private BigDecimal pointprice;

   	@Column(name = "pointnum" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '积分抵扣数量'")
	private Long pointnum;

   	@Column(name = "deleted" ,columnDefinition="tinyint(1) NULL DEFAULT 0 COMMENT '管理删除'")
	private Integer deleted;

   	@Column(name = "userdeleted" ,columnDefinition="tinyint(1) NULL DEFAULT 0 COMMENT '用户删除'")
	private Integer userdeleted;

   	@Column(name = "invoice_type" ,columnDefinition="tinyint(1) NULL DEFAULT 0 COMMENT '发票抬头类型，1-个人 2-单位'")
	private Integer invoiceType;

   	@Column(name = "invoice_name" ,columnDefinition="varchar(64) NULL COMMENT '发票抬头'")
	private String invoiceName;

   	@Column(name = "invoice_identi_number" ,columnDefinition="varchar(32) NULL COMMENT '发票识别号'")
	private String invoiceIdentiNumber;

   	@Column(name = "invoice_mobile" ,columnDefinition="varchar(32) NULL COMMENT '发票电话'")
	private String invoiceMobile;

   	@Column(name = "email" ,columnDefinition="varchar(32) NULL COMMENT '用户邮件'")
	private String email;

   	@Column(name = "expresscom" ,columnDefinition="varchar(32) NULL COMMENT 'null'")
	private String expresscom;

   	@Column(name = "expresssn" ,columnDefinition="varchar(32) NULL COMMENT 'null'")
	private String expresssn;

   	@Column(name = "expresstime" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '发货时间'")
	private Timestamp expresstime;

   	@Column(name = "receovertime" ,columnDefinition="int(8) NULL DEFAULT 0 COMMENT '收货超时'")
	private Long receovertime;

   	@Column(name = "isnc" ,columnDefinition="tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否推送NC'")
	private Integer isnc;

   	@Column(name = "comefrom" ,columnDefinition="tinyint(1) NULL DEFAULT 0 COMMENT '1-APP，2-小程序'")
	private Integer comefrom;

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUnionid() {
		return this.unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Long getMemuid() {
		return this.memuid;
	}
	public void setMemuid(Long memuid) {
		this.memuid = memuid;
	}

	public String getOpenid() {
		return this.openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOrdersn() {
		return this.ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}

	public String getOrdersn2() {
		return this.ordersn2;
	}
	public void setOrdersn2(String ordersn2) {
		this.ordersn2 = ordersn2;
	}

	public Long getGoodsid() {
		return this.goodsid;
	}
	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}

	public BigDecimal getGoodsprice() {
		return this.goodsprice;
	}
	public void setGoodsprice(BigDecimal goodsprice) {
		this.goodsprice = goodsprice;
	}

	public String getCardsn() {
		return this.cardsn;
	}
	public void setCardsn(String cardsn) {
		this.cardsn = cardsn;
	}

	public Long getTotal() {
		return this.total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}

	public BigDecimal getPrice() {
		return this.price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getOldprice() {
		return this.oldprice;
	}
	public void setOldprice(BigDecimal oldprice) {
		this.oldprice = oldprice;
	}

	public BigDecimal getChangeprice() {
		return this.changeprice;
	}
	public void setChangeprice(BigDecimal changeprice) {
		this.changeprice = changeprice;
	}

	public Integer getStatus() {
		return this.status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRefundStatus() {
		return this.refundStatus;
	}
	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Integer getPaytype() {
		return this.paytype;
	}
	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getUpdatetime() {
		return this.updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public Long getPayovertime() {
		return this.payovertime;
	}
	public void setPayovertime(Long payovertime) {
		this.payovertime = payovertime;
	}

	public String getTransid() {
		return this.transid;
	}
	public void setTransid(String transid) {
		this.transid = transid;
	}

	public Timestamp getPaytime() {
		return this.paytime;
	}
	public void setPaytime(Timestamp paytime) {
		this.paytime = paytime;
	}

	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBuyname() {
		return this.buyname;
	}
	public void setBuyname(String buyname) {
		this.buyname = buyname;
	}

	public String getLinkmobile() {
		return this.linkmobile;
	}
	public void setLinkmobile(String linkmobile) {
		this.linkmobile = linkmobile;
	}

	public Long getAddressid() {
		return this.addressid;
	}
	public void setAddressid(Long addressid) {
		this.addressid = addressid;
	}

	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getPointprice() {
		return this.pointprice;
	}
	public void setPointprice(BigDecimal pointprice) {
		this.pointprice = pointprice;
	}

	public Long getPointnum() {
		return this.pointnum;
	}
	public void setPointnum(Long pointnum) {
		this.pointnum = pointnum;
	}

	public Integer getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getUserdeleted() {
		return this.userdeleted;
	}
	public void setUserdeleted(Integer userdeleted) {
		this.userdeleted = userdeleted;
	}

	public Integer getInvoiceType() {
		return this.invoiceType;
	}
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceName() {
		return this.invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public String getInvoiceIdentiNumber() {
		return this.invoiceIdentiNumber;
	}
	public void setInvoiceIdentiNumber(String invoiceIdentiNumber) {
		this.invoiceIdentiNumber = invoiceIdentiNumber;
	}

	public String getInvoiceMobile() {
		return this.invoiceMobile;
	}
	public void setInvoiceMobile(String invoiceMobile) {
		this.invoiceMobile = invoiceMobile;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getExpresscom() {
		return this.expresscom;
	}
	public void setExpresscom(String expresscom) {
		this.expresscom = expresscom;
	}

	public String getExpresssn() {
		return this.expresssn;
	}
	public void setExpresssn(String expresssn) {
		this.expresssn = expresssn;
	}

	public Timestamp getExpresstime() {
		return this.expresstime;
	}
	public void setExpresstime(Timestamp expresstime) {
		this.expresstime = expresstime;
	}

	public Long getReceovertime() {
		return this.receovertime;
	}
	public void setReceovertime(Long receovertime) {
		this.receovertime = receovertime;
	}

	public Integer getIsnc() {
		return this.isnc;
	}
	public void setIsnc(Integer isnc) {
		this.isnc = isnc;
	}

	public Integer getComefrom() {
		return this.comefrom;
	}
	public void setComefrom(Integer comefrom) {
		this.comefrom = comefrom;
	}

}
