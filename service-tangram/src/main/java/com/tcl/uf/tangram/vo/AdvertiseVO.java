package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/28.
 * @version 1.0
 * @Desc
 */
public class AdvertiseVO {

    private String type;
    private String id;
    private String imgUrl;
    private String imgPlaceHolder;
    private Integer imgWidth;
    private Integer imgHeight;
    private ActionParams actionParams;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgPlaceHolder() {
        return imgPlaceHolder;
    }

    public void setImgPlaceHolder(String imgPlaceHolder) {
        this.imgPlaceHolder = imgPlaceHolder;
    }

    public Integer getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(Integer imgWidth) {
        this.imgWidth = imgWidth;
    }

    public Integer getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(Integer imgHeight) {
        this.imgHeight = imgHeight;
    }

    public ActionParams getActionParams() {
        return actionParams;
    }

    public void setActionParams(ActionParams actionParams) {
        this.actionParams = actionParams;
    }
}
