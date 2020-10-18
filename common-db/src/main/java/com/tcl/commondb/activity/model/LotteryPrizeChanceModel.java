package com.tcl.commondb.activity.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activity_lottery_prize_chance_record")
public class LotteryPrizeChanceModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "chance_id", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '抽奖机会编码'")
    private String chanceId;

    @Column(name = "configuration_num", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '抽奖配置关联编码'")
    private String configurationNum;

    @Column(name = "open_id", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '当前抽奖用户身份标识'")
    private String openId;

    @Column(name = "use_status", columnDefinition = "varchar(2) DEFAULT NULL COMMENT '是否使用[0:未使用 1:已使用]'")
    private String useStatus;

    @Column(name = "use_time", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '抽奖时间'")
    private String useTime;

    @Column(name = "source", columnDefinition = "varchar(2) DEFAULT NULL COMMENT '机会获得来源[0:每日系统自动发放 1:问卷 2:每日赠送 3:九宫格中奖 4:大屏活动 5:天天抽锦鲤 6:分享获得]'")
    private String source;

    @Column(name = "business_time", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '业务日期'")
    private String businessTime;

    @Column(name = "active_state", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '有效状态'")
    private String activeState;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChanceId() {
        return chanceId;
    }

    public void setChanceId(String chanceId) {
        this.chanceId = chanceId;
    }

    public String getConfigurationNum() {
        return configurationNum;
    }

    public void setConfigurationNum(String configurationNum) {
        this.configurationNum = configurationNum;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getActiveState() {
        return activeState;
    }

    public void setActiveState(String activeState) {
        this.activeState = activeState;
    }
}
