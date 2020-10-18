package com.tcl.uf.shop.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "商品图片表")
public class IntegralShopImagesDto {

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "file_url")
	private String fileUrl;


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

}
