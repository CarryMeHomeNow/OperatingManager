package com.tcl.commondb.points.model;

import javax.persistence.*;

/**
 * point_task 积分任务表 -> point_task_plan积分任务计划表
 * 中间表
 *
 */
@Entity
@Table(name = "point_task_point_task_plan",uniqueConstraints = @UniqueConstraint(columnNames = {"point_task_id", "point_task_plan_id"},name = "taskIndex"))
public class PointTaskPointTaskPlan {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point_task_id", columnDefinition = "bigint(20) NOT NULL DEFAULT 0 COMMENT 'point_task表主键'")
    private Long pointTaskId;

    @Column(name = "point_task_plan_id", columnDefinition = "bigint(20) NOT NULL DEFAULT 0 COMMENT 'point_task_plan主键'")
    private Long pointTaskPlanId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPointTaskId() {
        return pointTaskId;
    }

    public void setPointTaskId(Long pointTaskId) {
        this.pointTaskId = pointTaskId;
    }

    public Long getPointTaskPlanId() {
        return pointTaskPlanId;
    }

    public void setPointTaskPlanId(Long pointTaskPlanId) {
        this.pointTaskPlanId = pointTaskPlanId;
    }
}
