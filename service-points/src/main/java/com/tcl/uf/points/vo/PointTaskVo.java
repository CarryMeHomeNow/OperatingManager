package com.tcl.uf.points.vo;


public class PointTaskVo {

    private Long id;
    //任务标题
    private String title;
    //任务类型 1新手任务 2日常任务
    private Integer type;
    //任务类型编码
    private Integer typeCode;
    //任务图标
    private String url;
    //奖励积分
    private Integer amount;
    //次数上限
    private Integer maxNum;
    //跳转连接
    private String appPath;
    private String xcxPath;
    private String h5Path;
    private String content;
    private String xcxGhId;
    private String xcxAppId;

    public String getXcxGhId() {
        return xcxGhId;
    }

    public void setXcxGhId(String xcxGhId) {
        this.xcxGhId = xcxGhId;
    }

    public String getXcxAppId() {
        return xcxAppId;
    }

    public void setXcxAppId(String xcxAppId) {
        this.xcxAppId = xcxAppId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getXcxPath() {
        return xcxPath;
    }

    public void setXcxPath(String xcxPath) {
        this.xcxPath = xcxPath;
    }

    public String getH5Path() {
        return h5Path;
    }

    public void setH5Path(String h5Path) {
        this.h5Path = h5Path;
    } 

    public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	@Override
    public String toString() {
        return "PointTaskVo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", typeCode=" + typeCode +
                ", url='" + url + '\'' +
                ", amount=" + amount +
                ", maxNum=" + maxNum +
                ", appPath='" + appPath + '\'' +
                '}';
    }
}
