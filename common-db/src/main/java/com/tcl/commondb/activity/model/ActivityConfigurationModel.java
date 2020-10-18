package com.tcl.commondb.activity.model;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activity_base_configuration")
public class ActivityConfigurationModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "activity_configure_id", columnDefinition ="varchar(32) DEFAULT NULL COMMENT '活动配置编码'")
    private String activityConfigureId;

    @Column(name = "activity_name", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '活动名称'")
    private String activityName;

    @Column(name = "activity_type", columnDefinition = "varchar(2) DEFAULT NULL COMMENT '活动类型'")
    private String activityType;

    @Column(name = "activity_rule", columnDefinition = "text DEFAULT NULL COMMENT '活动规则'")
    private String activityRule;

    @Column(name = "share_title", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '分享标题'")
    private String shareTitle;

    @Column(name = "share_description", columnDefinition = "text DEFAULT NULL COMMENT '分享描述'")
    private String shareDescription;

    @Column(name = "share_image", columnDefinition = "text DEFAULT NULL COMMENT '分享图 多个用逗号隔开'")
    private String shareImage;

    @Column(name = "start_time", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '开始时间'")
    private String startTime;

    @Column(name = "end_time", columnDefinition = "varchar(33) DEFAULT NULL COMMENT '结束时间'")
    private String endTime;

    @Column(name = "present_image", columnDefinition = "text DEFAULT NULL COMMENT '活动内容介绍图 多个用逗号隔开'")
    private String presentImage;

    @Column(name = "activity_url", columnDefinition = "varchar(60) DEFAULT NULL COMMENT '活动地址'")
    private String activityUrl;

    @Column(name = "userId", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '创建人'")
    private String userId;

    @Column(name = "use_status", columnDefinition = "int(1) DEFAULT 1 COMMENT '伪删除 1:使用, 0:逻辑删除'")
    private Integer useStatus;

    @Column(name = "source", columnDefinition = "varchar(2) DEFAULT NULL COMMENT '活动来源'")
    private String source;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityConfigureId() {
        return activityConfigureId;
    }

    public void setActivityConfigureId(String activityConfigureId) {
        this.activityConfigureId = activityConfigureId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityRule() {
        return activityRule;
    }

    public void setActivityRule(String activityRule) {
        this.activityRule = activityRule;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDescription() {
        return shareDescription;
    }

    public void setShareDescription(String shareDescription) {
        this.shareDescription = shareDescription;
    }

    public String getShareImage() {
        return shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPresentImage() {
        return presentImage;
    }

    public void setPresentImage(String presentImage) {
        this.presentImage = presentImage;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
