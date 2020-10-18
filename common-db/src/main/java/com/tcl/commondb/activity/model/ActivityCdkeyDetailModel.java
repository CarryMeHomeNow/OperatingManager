package com.tcl.commondb.activity.model;

import javax.persistence.*;

/**
 * 兑换码明细表
 */
@Entity
@Table(name = "activity_cdkey_detail")
public class ActivityCdkeyDetailModel  {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "cdkey_id", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT 'activity_cdkey表主键'")
    private Long cdkeyId;

    @Column(name = "code", columnDefinition = "varchar(64) NOT NULL DEFAULT '' COMMENT '兑换码'")
    private String code;

    @Column(name = "use_status", columnDefinition = "tinyint(1) DEFAULT 0 COMMENT '状态 0 未使用 1 已使用'")
    private Byte useStatus;

    @Column(name = "is_delete", columnDefinition = "tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否删除，1否 2是'")
    private Byte isDelete;
    @Column(name = "create_time", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT '创建时间'")
    private Long createTime;
    @Column(name = "update_time", columnDefinition = "bigint(11) NOT NULL DEFAULT 0 COMMENT '修改时间'")
    private Long updateTime;

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCdkeyId() {
        return cdkeyId;
    }

    public void setCdkeyId(Long cdkeyId) {
        this.cdkeyId = cdkeyId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Byte useStatus) {
        this.useStatus = useStatus;
    }
}
