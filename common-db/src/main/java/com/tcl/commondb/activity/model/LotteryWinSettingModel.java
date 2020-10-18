package com.tcl.commondb.activity.model;

import javax.persistence.*;


@Entity
@Table(name = "activity_lottery_prize_win_simulation_data")
public class LotteryWinSettingModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "configuration_num", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '配置编码'")
    private String configurationNum;

    @Column(name = "prize_number", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '奖品编码'")
    private String prizeNumber;

    @Column(name = "serial_number", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '中奖设定'")
    private Integer serialNumber;

    @Column(name = "prize_name", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '奖品名称'")
    private String prizeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrizeNumber() {
        return prizeNumber;
    }

    public void setPrizeNumber(String prizeNumber) {
        this.prizeNumber = prizeNumber;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getConfigurationNum() {
        return configurationNum;
    }

    public void setConfigurationNum(String configurationNum) {
        this.configurationNum = configurationNum;
    }
}
