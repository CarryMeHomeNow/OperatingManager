package com.tcl.commondb.member.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "member_privacy_policy")
@Entity
public class PrivacyPolicyModel {

	@GeneratedValue
	@Id
	private Integer id;

	@Column(name = "privacy_policy", columnDefinition = "varchar(5000) not null COMMENT '隐私政策内容'")
	private String privacyPolicy;

	@JsonFormat(timezone = "GMT+8", locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Column(name = "create_by", columnDefinition = "varchar(64) NOT NULL COMMENT '创建人'")
	private String createBy;

	@JsonFormat(timezone = "GMT+8", locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	@Column(name = "update_by", columnDefinition = "varchar(64) NOT NULL COMMENT '修改人'")
	private String updateBy;

	@Transient
	private String result;

	public Integer getId() {
		return id;
	}

	public PrivacyPolicyModel setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getPrivacyPolicy() {
		return privacyPolicy;
	}

	public PrivacyPolicyModel setPrivacyPolicy(String privacyPolicy) {
		this.privacyPolicy = privacyPolicy;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public PrivacyPolicyModel setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getResult() {
		return result;
	}

	public PrivacyPolicyModel setResult(String result) {
		this.result = result;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public PrivacyPolicyModel setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getCreateBy() {
		return createBy;
	}

	public PrivacyPolicyModel setCreateBy(String createBy) {
		this.createBy = createBy;
		return this;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public PrivacyPolicyModel setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}

}
