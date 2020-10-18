package com.tcl.uf.tangram.vo;

import java.util.List;

/**
 * @author zhongfk on 2020/8/24.
 * @version 1.0
 * @Desc 图片参数
 */
public class ImgParams {


    private String imgUrl;
    private String imgPlaceHolder = "service_equity_icon";
    private String smallIcon;
    private Integer imgWidth = 200;
    private Integer imgHeight = 200;
    private List<Integer> imgEdgeInsets;

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

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }

    public List<Integer> getImgEdgeInsets() {
        return imgEdgeInsets;
    }

    public void setImgEdgeInsets(List<Integer> imgEdgeInsets) {
        this.imgEdgeInsets = imgEdgeInsets;
    }
}
