package com.tcl.commonservice.service.dto;

/**
 * @author zhongfk on 2020/8/27.
 * @version 1.0
 */
public class PointTask {
    private Long id;

    /**
     *   任务标题
     */
    private String title;

    /**
     *  任务图片url
     */
    private String url;

    /**
     * 奖级积分/次
     */
    private Integer reward;

    /**
     *完成次数上限
     */
    private Integer maxNum;

    /**
     * app跳转路径
     */
    private String appPath;

    /**
     *  xcx跳转路径
     */
    private String xcxPath;

    private String xcxAppId;

    /**
     * 原始id gh开头
     */
    private String xcxGhId;

    /**
     * h5跳转路径
     */
    private String h5Path;


    /**
     *  任务介绍
     */
    private String content;

    /**
     *  是否删除，1否 2是
     */
    private Byte isDelete;

    private Long createTime;
    /**
     * 修改时间
      */
    private Long updateTime;

    /**
     * 1 新手  2 日常
     */
    private Integer Type;

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

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
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

    public String getXcxAppId() {
        return xcxAppId;
    }

    public void setXcxAppId(String xcxAppId) {
        this.xcxAppId = xcxAppId;
    }

    public String getXcxGhId() {
        return xcxGhId;
    }

    public void setXcxGhId(String xcxGhId) {
        this.xcxGhId = xcxGhId;
    }

    public String getH5Path() {
        return h5Path;
    }

    public void setH5Path(String h5Path) {
        this.h5Path = h5Path;
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

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }
}
