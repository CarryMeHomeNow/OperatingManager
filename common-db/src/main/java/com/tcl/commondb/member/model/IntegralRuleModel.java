package com.tcl.commondb.member.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name="member_integral_rule_configure")
@Entity 
public class IntegralRuleModel {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "user_id",columnDefinition = "varchar(100) DEFAULT NULL COMMENT '创建人'")
	private String name;
	
	@Column(name = "content",columnDefinition = "text DEFAULT NULL COMMENT '会员规则'")
	private String content;

	@Column(name = "content_format",columnDefinition = "text DEFAULT NULL COMMENT '会员规则转换JSON'")
	private String contentFormat;

	@Column(name = "editor", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '编辑者'")
	private String editor;

	@Column(name = "creator", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '创建者'")
	private String creator;

	@Column(name = "create_time", columnDefinition = "datetime DEFAULT NULL COMMENT '创建时间'")
	private Date createTime;

	@JsonFormat(timezone = "GMT+8",locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentFormat() {
		return contentFormat;
	}

	public void setContentFormat(String contentFormat) {
		this.contentFormat = contentFormat;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
