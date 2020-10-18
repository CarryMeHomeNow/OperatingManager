package com.tcl.uf.shop.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "商品图片表")
public class IntegralShopImagesVo {

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "file_url")
	private String fileUrl;

	@ApiModelProperty(value = "file_type")
	private Integer fileType;

	@ApiModelProperty(value = "linkflag")
	private String linkflag;

	@ApiModelProperty(value = "comefrom")
	private String comefrom;

	@ApiModelProperty(value = "status")
	private Integer status;



  	public Long getId() {
    	return id;
  	}
  	public void setId(Long id) {
    	this.id = id;
  	}

  	public String getFileUrl() {
    	return fileUrl;
  	}
  	public void setFileUrl(String fileUrl) {
    	this.fileUrl = fileUrl;
  	}

  	public Integer getFileType() {
    	return fileType;
  	}
  	public void setFileType(Integer fileType) {
    	this.fileType = fileType;
  	}

  	public String getLinkflag() {
    	return linkflag;
  	}
  	public void setLinkflag(String linkflag) {
    	this.linkflag = linkflag;
  	}

  	public String getComefrom() {
    	return comefrom;
  	}
  	public void setComefrom(String comefrom) {
    	this.comefrom = comefrom;
  	}

  	public Integer getStatus() {
    	return status;
  	}
  	public void setStatus(Integer status) {
    	this.status = status;
  	}
}
