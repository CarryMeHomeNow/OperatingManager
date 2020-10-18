package com.tcl.commondb.activity.model;

import javax.persistence.*;

@Entity
@Table(name = "activity_lottery_prize_news_broadcast")
public class LotteryNewsBroadcastModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "configuration_num", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '配置编码'")
    private String configurationNum;

    @Column(name = "prize_number", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '奖品编码'")
    private String prizeNumber;

    @Column(name = "nick_name", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '昵称'")
    private String nickName;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
