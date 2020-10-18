package com.tcl.uf.member.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class PrivacyPolicyVo implements Serializable{
	
	private Integer id;
	
	private String privacyPolicy; 
	
	@JsonFormat(timezone = "GMT+8",locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime; 
	
	private String updateBy;

	public Integer getId() {
		return id;
	}

	public PrivacyPolicyVo setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getPrivacyPolicy() {
		return privacyPolicy;
	}

	public PrivacyPolicyVo setPrivacyPolicy(String privacyPolicy) {
		this.privacyPolicy = privacyPolicy;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public PrivacyPolicyVo setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public PrivacyPolicyVo setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}

}
