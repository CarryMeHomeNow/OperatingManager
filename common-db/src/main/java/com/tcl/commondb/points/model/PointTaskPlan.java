package com.tcl.commondb.points.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 积分任务投放计划
 */
@Entity
@Table(name = "point_task_plan")
public class PointTaskPlan {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", columnDefinition = "varchar(50) NOT NULL DEFAULT '' COMMENT '投放计划标题'")
    private String title;

    @Column(name = "type", columnDefinition = "tinyint(4) NOT NULL DEFAULT 0 COMMENT '任务类型，1新手任务 2日常任务'")
    private Integer type;


    @Column(name = "status", columnDefinition = "tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态 1 未生效 2 生效'")
    private Integer status;


    @Column(name = "start_time", columnDefinition = "bigint(20)  NOT NULL DEFAULT 0 COMMENT '计划生效开始时间'")
    private Long startTime;

    @Column(name = "end_time", columnDefinition = "bigint(20)  NOT NULL DEFAULT 0 COMMENT '计划生效投放结束时间'")
    private Long endTime;

    @Column(name = "is_delete", columnDefinition = "tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否删除，1否 2是'")
    private Byte isDelete;


    @Column(name = "create_time", columnDefinition = "bigint(20)  NOT NULL DEFAULT 0 COMMENT '创建时间'")
    private Long createTime;

    @Column(name = "update_time", columnDefinition = "bigint(20)  NOT NULL DEFAULT 0 COMMENT '修改时间'")
    private Long updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }



	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
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

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
