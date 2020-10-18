package com.tcl.uf.points.vo;

public class AppPointTaskVo {

	//任务id
    private Long id;
    //任务所属计划
    private Long planId;
    //任务标题
    private String title;
    //任务类型 1新手任务 2日常任务
    private Integer type;
    //任务图标
    private String url;
    //奖励积分
    private Integer amount;
    //次数上限
    private Integer maxNum;
    //跳转连接
    private String appPath;
    //已玩次数 
    private Integer currentNum;
    //开始生效时间 
    private Long startTime;
    
	public Integer getCurrentNum() {
		return currentNum;
	}

	public AppPointTaskVo setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
		return this;
	}

	public Long getStartTime() {
		return startTime;
	}

	public AppPointTaskVo setStartTime(Long startTime) {
		this.startTime = startTime;
		return this;
	}

	public Long getId() {
		return id;
	}

	public AppPointTaskVo setId(Long id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public AppPointTaskVo setTitle(String title) {
		this.title = title;
		return this;
	}

	public Integer getType() {
		return type;
	}

	public AppPointTaskVo setType(Integer type) {
		this.type = type;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public AppPointTaskVo setUrl(String url) {
		this.url = url;
		return this;
	}

	public Integer getAmount() {
		return amount;
	}

	public AppPointTaskVo setAmount(Integer amount) {
		this.amount = amount;
		return this;
	}

	public Integer getMaxNum() {
		return maxNum;
	}

	public AppPointTaskVo setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
		return this;
	}

	public String getAppPath() {
		return appPath;
	}

	public AppPointTaskVo setAppPath(String appPath) {
		this.appPath = appPath;
		return this;
	}
	

	public Long getPlanId() {
		return planId;
	}

	public AppPointTaskVo setPlanId(Long planId) {
		this.planId = planId;
		return this;
	}

	@Override
	public String toString() {
		return "AppPointTaskVo [id=" + id + ", planId=" + planId + ", title=" + title + ", type=" + type + ", url="
				+ url + ", amount=" + amount + ", maxNum=" + maxNum + ", appPath=" + appPath + ", currentNum="
				+ currentNum + ", startTime=" + startTime + "]";
	}

	

}
