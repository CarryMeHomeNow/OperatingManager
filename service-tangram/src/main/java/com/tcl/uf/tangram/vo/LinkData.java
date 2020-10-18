package com.tcl.uf.tangram.vo;

import java.io.Serializable;

/**
 * @Desc : 后端接口的跳转数据
 * @Author yxlong
 * @Date 2020/9/2 9:54 上午
 */
public class LinkData implements Serializable {

    private static final long serialVersionUID = -3209031559312208927L;
    /**
     * 对象值
     */
    private String objectValue;
    /**
     * 对象标签
     */
    private String objectLabel;
    /**
     * 类型值 GOODS、COUPON、CUSTOM、APP_PAGE
     */
    private String typeValue;
    /**
     * 类型标签 商品、优惠券、外链、应用页面
     */
    private String typeLabel;

    public String getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(String objectValue) {
        this.objectValue = objectValue;
    }

    public String getObjectLabel() {
        return objectLabel;
    }

    public void setObjectLabel(String objectLabel) {
        this.objectLabel = objectLabel;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(String typeLabel) {
        this.typeLabel = typeLabel;
    }
}
