package com.tcl.commondb.points.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 积分任务model
 *
 */
@Entity
@Table(name = "point_task")
public class PointTask{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(50) NOT NULL DEFAULT '' COMMENT '任务标题'")
    private String title;

    @Column(name = "url", columnDefinition = "varchar(256) NOT NULL DEFAULT ''  COMMENT '任务图片url'")
    private String url;

    @Column(name = "reward", columnDefinition = "int(11) NOT NULL DEFAULT 0 COMMENT '奖级积分/次'")
    private Integer reward;

    @Column(name = "max_num", columnDefinition = "int(11) NOT NULL DEFAULT 1 COMMENT '完成次数上限'")
    private Integer maxNum;

    @Column(name = "app_path", columnDefinition = "varchar(256)   COMMENT 'app跳转路径'")
    private String appPath;

    @Column(name = "xcx_path", columnDefinition = "varchar(256)   COMMENT 'xcx跳转路径'")
    private String xcxPath;

    @Column(name = "xcx_app_id", columnDefinition = "varchar(256)   COMMENT 'xcx appid'")
    private String xcxAppId;

    @Column(name = "xcx_gh_id", columnDefinition = "varchar(256)   COMMENT 'xcx 原始id gh开头'")
    private String xcxGhId;

    @Column(name = "h5_path", columnDefinition = "varchar(256)   COMMENT 'h5跳转路径'")
    private String h5Path;


    @Column(name = "content", columnDefinition = "varchar(2048) NOT NULL DEFAULT '' COMMENT '任务介绍'")
    private String content;

    @Column(name = "is_delete", columnDefinition = "tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否删除，1否 2是'")
    private Byte isDelete;

    @Column(name = "create_time", columnDefinition = "bigint(20) NOT NULL DEFAULT 0  COMMENT '创建时间'")
    private Long createTime;
    @Column(name = "update_time", columnDefinition = "bigint(20) NOT NULL DEFAULT 0  COMMENT '修改时间'")
    private Long updateTime;

    @Column(name = "type", columnDefinition = "tinyint(4) NOT NULL DEFAULT 0 COMMENT '任务类型，1新手任务 2日常任务'")
    private Integer type;

    @Column(name = "type_code", columnDefinition = "int(11) NOT NULL DEFAULT 0 COMMENT '任务类型编码'")
    private Integer typeCode;
    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }



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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getXcxPath() {
        return xcxPath;
    }

    public void setXcxPath(String xcxPath) {
        this.xcxPath = xcxPath;
    }

    public String getXcxAppId() {
        return xcxAppId;
    }

    public void setXcxAppId(String xcxAppId) {
        this.xcxAppId = xcxAppId;
    }

    public String getXcxGhId() {
        return xcxGhId;
    }

    public void setXcxGhId(String xcxGhId) {
        this.xcxGhId = xcxGhId;
    }

    public String getH5Path() {
        return h5Path;
    }

    public void setH5Path(String h5Path) {
        this.h5Path = h5Path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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
	
	public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	@Override
    public String toString() {
        return "PointTask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", reward=" + reward +
                ", maxNum=" + maxNum +
                ", appPath='" + appPath + '\'' +
                ", xcxPath='" + xcxPath + '\'' +
                ", xcxAppId='" + xcxAppId + '\'' +
                ", xcxGhId='" + xcxGhId + '\'' +
                ", h5Path='" + h5Path + '\'' +
                ", content='" + content + '\'' +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", type=" + type +
                ", type_code=" + typeCode +
                '}';
    }
}
