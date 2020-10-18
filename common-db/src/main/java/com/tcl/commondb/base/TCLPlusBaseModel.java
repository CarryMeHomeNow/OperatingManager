package com.tcl.commondb.base;

import javax.persistence.Column;

/**
 * 基础model
 */
public class TCLPlusBaseModel {
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
}
