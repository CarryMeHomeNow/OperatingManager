package com.tcl.commondb.member.model;

import javax.persistence.*;

@Entity
@Table(name="member_device_growth_configure")
public class DeviceGrowthConfigureModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "brand",columnDefinition = "varchar(32) DEFAULT NULL COMMENT '品牌'")
    private String brand;

    @Column(name = "classification",columnDefinition = "varchar(32) DEFAULT NULL COMMENT '分类'")
    private String classification;

    @Column(name = "model",columnDefinition = "varchar(32) DEFAULT NULL COMMENT '标题'")
    private String model;

    @Column(name = "growth_value",columnDefinition = "int(2) DEFAULT NULL COMMENT '成长值'")
    private Integer growthValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getGrowthValue() {
        return growthValue;
    }

    public void setGrowthValue(Integer growthValue) {
        this.growthValue = growthValue;
    }
}
