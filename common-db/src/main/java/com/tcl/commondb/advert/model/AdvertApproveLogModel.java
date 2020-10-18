package com.tcl.commondb.advert.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "advert_approve_log")
public class AdvertApproveLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "apply_type", columnDefinition = "varchar(20) NOT NULL COMMENT '申请单类型'")
    private String applyType;

    @Column(name = "apply_id", columnDefinition = "int(32) NOT NULL COMMENT '申请单ID'")
    private Long applyId;

    @Column(name = "approver", columnDefinition = "varchar(100) NOT NULL COMMENT '审核人'")
    private String approver;

    @Column(name = "approve_result", columnDefinition = "tinyint(4) NOT NULL COMMENT '审核结果 1通过 2驳回'")
    private Integer approveResult;

    @Column(name = "remark", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '备注'")
    private String remark;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Integer getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(Integer approveResult) {
        this.approveResult = approveResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
