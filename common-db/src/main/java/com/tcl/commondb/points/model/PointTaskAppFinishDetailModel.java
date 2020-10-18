package com.tcl.commondb.points.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "point_task_app_finish_detail",
indexes = {@Index(columnList = "ssoid"),@Index(columnList = "task_id")})
public class PointTaskAppFinishDetailModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;    //id标识
	
	@Column(name = "ssoid",columnDefinition = "bigint(20) NOT NULL DEFAULT 0 COMMENT '用户唯一标识'")
	private Long ssoid;  //用户唯一标识
	
	@Column(name = "task_id",columnDefinition = "int(11) NOT NULL DEFAULT 0 COMMENT '任务标识id'")
	private Long taskId;  //任务标识id
	
	@Column(name = "current_num",columnDefinition = "tinyint(2) NOT NULL DEFAULT 0 COMMENT '今天第几次完成任务'")
	private Integer currentNum;  //第几次完成任务
	
	//@JsonFormat(timezone = "GMT+8",locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "finish_time",columnDefinition = "bigint(20) NOT NULL DEFAULT 0  COMMENT '完成时间'")
	//@Temporal(TemporalType.TIMESTAMP)
	private Long finishTime;    //完成时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSsoid() {
		return ssoid;
	}

	public void setSsoid(Long ssoid) {
		this.ssoid = ssoid;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Integer getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
	}

	public Long getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Long finishTime) {
		this.finishTime = finishTime;
	}

}
