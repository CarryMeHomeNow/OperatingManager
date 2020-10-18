package com.tcl.uf.points.vo;

public class PointCouponVo {
	
    private Long id;

    private String dcCouponId;

    private String name;

    private Integer usePoint;

    private Integer useNum;

    private Integer status;

    private String content;

    private Byte isDelete;
    
    private Long startTime;
    
    private Long endTime;
    
    private Long createTime;

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

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
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

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	} 
    
    

}
