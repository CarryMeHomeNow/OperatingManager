package com.tcl.commonservice.service.vo;

/**
 * @author youyun.xu
 * @ClassName: ${CLASSNAME}
 * @Description: 文章内容管理
 * @date 2020/8/18 16:03
 */
public class AfterSaleConfigureVo {

    private String title;
    private String subTitle;
    private String imageUrl;
    private String position;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
