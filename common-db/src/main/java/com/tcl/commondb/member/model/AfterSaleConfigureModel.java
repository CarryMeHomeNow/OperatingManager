package com.tcl.commondb.member.model;

import javax.persistence.*;

@Entity
@Table(name="member_after_sale_configure")
public class AfterSaleConfigureModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "title",columnDefinition = "text DEFAULT NULL COMMENT '标题'")
    private String title;

    @Column(name = "sub_title",columnDefinition = "varchar(100) DEFAULT NULL COMMENT '子标题'")
    private String subTitle;

    @Column(name = "image_url",columnDefinition = "varchar(150) DEFAULT NULL COMMENT '图片链接'")
    private String imageUrl;

    @Column(name = "position",columnDefinition = "int(2) DEFAULT NULL COMMENT '会员规则'")
    private Integer position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
