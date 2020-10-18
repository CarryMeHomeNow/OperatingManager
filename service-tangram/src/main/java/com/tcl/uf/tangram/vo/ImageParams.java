package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/20.
 * @version 1.0
 */
public class ImageParams {

    private String imgUrl;
    private String imgPlaceHolder;
    private String smallIcon;
    private Integer imgWidth ;
    private Integer imgHeight ;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgPlaceHolder() {
        return imgPlaceHolder;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
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

    public Integer[] getImgEdgeInsets() {
        return imgEdgeInsets;
    }

    public void setImgEdgeInsets(Integer[] imgEdgeInsets) {
        this.imgEdgeInsets = imgEdgeInsets;
    }

    public Integer[] imgEdgeInsets = new Integer[]{0,10,25,10};

}
