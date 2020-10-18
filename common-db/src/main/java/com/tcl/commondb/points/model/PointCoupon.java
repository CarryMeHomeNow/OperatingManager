package com.tcl.commondb.points.model;

import javax.persistence.*;

/**
 * 积分优惠券model
 *
 */
@Entity
@Table(name = "point_coupon")
public class PointCoupon{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "dc_coupon_id", columnDefinition = "varchar(50) NOT NULL DEFAULT '' COMMENT '电商系统优惠券id'")
    private String dcCouponId;

    @Column(name = "name", columnDefinition = "varchar(64) NOT NULL DEFAULT '' COMMENT '优惠券名称'")
    private String name;

    @Column(name = "use_point", columnDefinition = "int(11) NOT NULL DEFAULT 1 COMMENT '兑换积分'")
    private Integer usePoint;

    @Column(name = "use_num", columnDefinition = "int(11) NOT NULL DEFAULT 0 COMMENT '兑换次数 0标识不限制'")
    private Integer useNum;

    @Column(name = "status", columnDefinition = "int(11) NOT NULL DEFAULT 0 COMMENT '任务状态 1 未发布 2 已发布'")
    private Integer status;

    @Column(name = "content", columnDefinition = "varchar(2048) NOT NULL DEFAULT '' COMMENT '使用范围'")
    private String content;

    @Column(name = "is_delete", columnDefinition = "tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否删除，1否 2是'")
    private Byte isDelete;
    
    @Column(name = "start_time", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT '生效开始时间'")
    private Long startTime;
    
    @Column(name = "end_time", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT '生效结束时间'")
    private Long endTime;
    
    @Column(name = "create_time", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT '创建时间'")
    private Long createTime;
    @Column(name = "update_time", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT '修改时间'")
    private Long updateTime;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDcCouponId() {
        return dcCouponId;
    }

    public void setDcCouponId(String dcCouponId) {
        this.dcCouponId = dcCouponId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUsePoint() {
        return usePoint;
    }

    public void setUsePoint(Integer usePoint) {
        this.usePoint = usePoint;
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
    
    
}
