package com.tcl.uf.advert.vo;

import java.util.Map;

public class LocationGroupListVo {

    private Long id;

    private String title;

    private Integer picWidth;

    private Integer picHeight;

    private Integer picSize;

    private Integer frameNum;

    private String appVer;

    private Map<Integer, String> frameMap;

    public LocationGroupListVo(Long id, String title, Integer picWidth, Integer picHeight, Integer picSize, Integer frameNum, String appVer) {
        this.id = id;
        this.title = title;
        this.picWidth = picWidth;
        this.picHeight = picHeight;
        this.picSize = picSize;
        this.frameNum = frameNum;
        this.appVer = appVer;
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

    public Integer getPicWidth() {
        return picWidth;
    }

    public void setPicWidth(Integer picWidth) {
        this.picWidth = picWidth;
    }

    public Integer getPicHeight() {
        return picHeight;
    }

    public void setPicHeight(Integer picHeight) {
        this.picHeight = picHeight;
    }

    public Integer getPicSize() {
        return picSize;
    }

    public void setPicSize(Integer picSize) {
        this.picSize = picSize;
    }

    public Integer getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(Integer frameNum) {
        this.frameNum = frameNum;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public Map<Integer, String> getFrameMap() {
        return frameMap;
    }

    public void setFrameMap(Map<Integer, String> frameMap) {
        this.frameMap = frameMap;
    }
}
