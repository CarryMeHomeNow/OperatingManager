package com.tcl.commondb.shop.model;

import javax.persistence.*;

/**
 * @Description  
 * @Author  maym
 * @Date 2020-08-10 14:39:37 
 */

@Entity
@Table ( name ="integral_shop_images")
public class IntegralShopImagesModel{


	@Id
	@GeneratedValue
   	@Column(name = "id" ,columnDefinition="int(8) NOT NULL DEFAULT 0 COMMENT 'null'")
	private Long id;

   	@Column(name = "file_url" ,columnDefinition="varchar(255) NULL COMMENT '文件url'")
	private String fileUrl;

   	@Column(name = "file_type" ,columnDefinition="tinyint(1) NOT NULL DEFAULT 0 COMMENT '文件类型1-图片，2-文件'")
	private Integer fileType;

   	@Column(name = "linkflag" ,columnDefinition="varchar(32) NULL COMMENT '关联标识'")
	private String linkflag;

   	@Column(name = "comefrom" ,columnDefinition="varchar(32) NULL COMMENT '自来哪个模块上传的'")
	private String comefrom;

   	@Column(name = "status" ,columnDefinition="tinyint(1) NOT NULL DEFAULT 0 COMMENT '活动状态 0-停止，1-开启'")
	private Integer status;

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getFileUrl() {
		return this.fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getFileType() {
		return this.fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public String getLinkflag() {
		return this.linkflag;
	}
	public void setLinkflag(String linkflag) {
		this.linkflag = linkflag;
	}

	public String getComefrom() {
		return this.comefrom;
	}
	public void setComefrom(String comefrom) {
		this.comefrom = comefrom;
	}

	public Integer getStatus() {
		return this.status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
