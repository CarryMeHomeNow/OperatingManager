package com.tcl.commonservice.service.vo;

public class SignInVo {

	private Long id;
	private Long signTime;
	private Long accountId;
	private Integer dayNum;

	
	public Long getId() {
		return id;
	}
	public SignInVo setId(Long id) {
		this.id = id;
		return this;
	}
	public Long getSignTime() {
		return signTime;
	}
	public SignInVo setSignTime(Long signTime) {
		this.signTime = signTime;
		return this;
	}
	public Long getAccountId() {
		return accountId;
	}
	public SignInVo setAccountId(Long accountId) {
		this.accountId = accountId;
		return this;
	}
	public Integer getDayNum() {
		return dayNum;
	}
	public SignInVo setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
		return this;
	}
	

}
