package com.tcl.commondb.member.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 会员成长值明细表
 */
@Entity
@Table(name = "member_user_growth_value_detail")
public class MemberGrowthValueDetailModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ssoid", columnDefinition = "BIGINT(20)  COMMENT '云平台ssoid'")
    private Long ssoid;

    @Column(name = "growth_value", columnDefinition = "int(10) DEFAULT NULL DEFAULT 0 COMMENT '成长值'")
    private int growthValue;

    @Column(name = "type", columnDefinition = "int(2) DEFAULT NULL COMMENT '1 增； 2减'")
    private int type;

    @Column(name = "sn", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '设备序列号'")
    private String sn;

    @Column(name = "device_model", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '设备型号'")
    private String deviceModel;

    @Column(name = "source", columnDefinition = "int(2) DEFAULT NULL COMMENT '渠道来源'")
    private int source;

    @Column(name = "comment", columnDefinition = "varchar(36) DEFAULT NULL COMMENT '说明'")
    private String comment;

    @Column(name = "expired_time", columnDefinition = "BIGINT(20) DEFAULT NULL COMMENT '过期失效时间'")
    private Long expiredTime;

    @Column(name = "create_time", columnDefinition = "datetime DEFAULT NULL COMMENT '创建时间'")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSsoid() {
        return ssoid;
    }

    public void setSsoid(Long ssoid) {
        this.ssoid = ssoid;
    }

    public int getGrowthValue() {
        return growthValue;
    }

    public void setGrowthValue(int growthValue) {
        this.growthValue = growthValue;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }
}
