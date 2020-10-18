package com.tcl.commondb.advert.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertEmailSendRecordModel
 * @Description:
 * @date 2019/12/11 14:48
 */
@Entity
@Table(name = "advert_email_send_record")
public class AdvertEmailSendRecordModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "email", columnDefinition = "varchar(50) NOT NULL COMMENT '邮箱地址'")
    private String email;

    @Column(name = "title", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '邮件标题'")
    private String title;

    @Column(name = "content", columnDefinition = "text DEFAULT NULL COMMENT '邮件内容'")
    private String content;

    @Column(name = "results", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '发送结果'")
    private String results;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    @Column(name = "update_time", columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    private Timestamp updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
