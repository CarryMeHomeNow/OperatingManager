package com.tcl.commondb.points.model;
import javax.persistence.*;
/**
 * App 签到记录
 */
@Entity
@Table(name = "point_sign_in_record",indexes = {@Index(columnList = "account_id")})
public class PointSignInRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sign_time", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT '签到时间'")
    private Long signTime;
    @Column(name = "account_id", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT '用户ssoid'")
    private Long accountId;
    @Column(name = "day_num", columnDefinition = "int(11) NOT NULL DEFAULT 1 COMMENT '连续签到天数,默认第一天'")
    private Integer dayNum;
    @Column(name = "is_delete", columnDefinition = "tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否删除，1否 2是'")
    private Byte isDelete;
    @Column(name = "create_time", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT '创建时间'")
    private Long createTime;
    @Column(name = "update_time", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT '修改时间'")
    private Long updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSignTime() {
		return signTime;
	}
	public void setSignTime(Long signTime) {
		this.signTime = signTime;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Integer getDayNum() {
		return dayNum;
	}
	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}
	public Byte getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
    

}
