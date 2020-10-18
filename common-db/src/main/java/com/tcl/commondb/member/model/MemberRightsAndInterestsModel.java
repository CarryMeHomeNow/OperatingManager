package com.tcl.commondb.member.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Description: 会员权益信息表
 */
@Entity
@Table(name = "member_rights_and_interests")
public class MemberRightsAndInterestsModel {

    @Id
    @GeneratedValue
    private Long id;

	@Column(name = "ssoid", columnDefinition = "BIGINT(20) NOT NULL unique COMMENT '云平台ssoid'")
	private Long ssoid;
    
    @Column(name = "growth_value", columnDefinition = "int(10) DEFAULT NULL DEFAULT 0 COMMENT '成长值'")
    private int growthValue;

	@Column(name = "create_time", columnDefinition = "datetime DEFAULT NULL COMMENT '创建时间'")
	private Date createTime;

	@Column(name = "update_time", columnDefinition = "datetime DEFAULT NULL COMMENT '创建时间'")
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSsoid() {
		return ssoid;
	}

	public void setSsoid(Long ssoid) {
		this.ssoid = ssoid;
	}

	public int getGrowthValue() {
		return growthValue;
	}

	public void setGrowthValue(int growthValue) {
		this.growthValue = growthValue;
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
