package com.tcl.uf.member.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class IntegralRuleVo implements Serializable{
	
	private Integer id;
	
	private String content; 
	
	@JsonFormat(timezone = "GMT+8",locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime; 
	
	private String updateBy;

	public Integer getId() {
		return id;
	}

	public IntegralRuleVo setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getContent() {
		return content;
	}

	public IntegralRuleVo setContent(String content) {
		this.content = content;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public IntegralRuleVo setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public IntegralRuleVo setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}

}
