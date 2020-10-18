package com.tcl.commondb.activity.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author  maym
 * @Date 2020-07-22 17:31:06 
 */

@Entity
@Table ( name ="mem_markting_luck_eveday")
public class MemMarktingLuckEvedayModel{


	@Id
	@GeneratedValue
	private Long id;

   	@Column(name = "index_title" ,columnDefinition="varchar(32) NULL COMMENT '活动首页标题'")
	private String indexTitle;

   	@Column(name = "detail_title" ,columnDefinition="varchar(32) NULL COMMENT '资料电视页面标题'")
	private String detailTitle;

   	@Column(name = "undetail_title" ,columnDefinition="varchar(32) NULL COMMENT '资料非电视页面标题'")
	private String undetailTitle;

   	@Column(name = "luck_title" ,columnDefinition="varchar(32) NULL COMMENT '抽奖页面标题'")
	private String luckTitle;

   	@Column(name = "activity_info" ,columnDefinition="varchar(32) NULL COMMENT '活动首页说明'")
	private String activityInfo;

   	@Column(name = "file_info" ,columnDefinition="varchar(32) NULL COMMENT '资料视电页面说明'")
	private String fileInfo;

   	@Column(name = "unfile_info" ,columnDefinition="varchar(32) NULL COMMENT 'null'")
	private String unfileInfo;

   	@Column(name = "page_info" ,columnDefinition="varchar(32) NULL COMMENT '抽奖页面说明'")
	private String pageInfo;

   	@Column(name = "banner_url" ,columnDefinition="varchar(32) NULL COMMENT '填写资料banner'")
	private String bannerUrl;

   	@Column(name = "snimg_url" ,columnDefinition="varchar(32) NULL COMMENT '设备序列号说明图片'")
	private String snimgUrl;

   	@Column(name = "approve_passtoast" ,columnDefinition="varchar(32) NULL COMMENT '审核成功提示'")
	private String approvePasstoast;

   	@Column(name = "approve_failtoast" ,columnDefinition="varchar(32) NULL COMMENT '审核失败提示'")
	private String approveFailtoast;

   	@Column(name = "activity_url" ,columnDefinition="varchar(32) NULL COMMENT '活动页面地址'")
	private String activityUrl;

   	@Column(name = "qcode_url" ,columnDefinition="varchar(32) NULL COMMENT '页面二维码'")
	private String qcodeUrl;

   	@Column(name = "share_title" ,columnDefinition="varchar(32) NULL COMMENT '分享标题'")
	private String shareTitle;

   	@Column(name = "share_url" ,columnDefinition="varchar(32) NULL COMMENT '分享地址'")
	private String shareUrl;

   	@Column(name = "share_img" ,columnDefinition="varchar(32) NULL COMMENT '分享图片'")
	private String shareImg;

   	@Column(name = "start_time" ,columnDefinition="varchar(32) NULL COMMENT '活动开始时间'")
	private String startTime;

   	@Column(name = "end_time" ,columnDefinition="varchar(32) NULL COMMENT '活动结束时间'")
	private String endTime;

   	@Column(name = "create_time" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
	private Timestamp createTime;

   	@Column(name = "jhstart_time" ,columnDefinition="varchar(32) NULL COMMENT '激活开始时间'")
	private String jhstartTime;

   	@Column(name = "jhend_time" ,columnDefinition="varchar(32) NULL COMMENT '激活结束时间'")
	private String jhendTime;

   	@Column(name = "luck_aid" ,columnDefinition="int(8) NOT NULL DEFAULT 0 COMMENT '关联抽奖活动id'")
	private Long luckAid;

   	@Column(name = "lucker_toast" ,columnDefinition="varchar(32) NULL COMMENT '中奖名单未到时间提示'")
	private String luckerToast;

   	@Column(name = "status" ,columnDefinition="tinyint(1) NOT NULL DEFAULT 0 COMMENT '活动状态-1-删除，0-停止，1-开启'")
	private Integer status;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndexTitle() {
		return this.indexTitle;
	}

	public void setIndexTitle(String indexTitle) {
		this.indexTitle = indexTitle;
	}

	public String getDetailTitle() {
		return this.detailTitle;
	}

	public void setDetailTitle(String detailTitle) {
		this.detailTitle = detailTitle;
	}

	public String getUndetailTitle() {
		return this.undetailTitle;
	}

	public void setUndetailTitle(String undetailTitle) {
		this.undetailTitle = undetailTitle;
	}

	public String getLuckTitle() {
		return this.luckTitle;
	}

	public void setLuckTitle(String luckTitle) {
		this.luckTitle = luckTitle;
	}

	public String getActivityInfo() {
		return this.activityInfo;
	}

	public void setActivityInfo(String activityInfo) {
		this.activityInfo = activityInfo;
	}

	public String getFileInfo() {
		return this.fileInfo;
	}

	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}

	public String getUnfileInfo() {
		return this.unfileInfo;
	}

	public void setUnfileInfo(String unfileInfo) {
		this.unfileInfo = unfileInfo;
	}

	public String getPageInfo() {
		return this.pageInfo;
	}

	public void setPageInfo(String pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getBannerUrl() {
		return this.bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getSnimgUrl() {
		return this.snimgUrl;
	}

	public void setSnimgUrl(String snimgUrl) {
		this.snimgUrl = snimgUrl;
	}

	public String getApprovePasstoast() {
		return this.approvePasstoast;
	}

	public void setApprovePasstoast(String approvePasstoast) {
		this.approvePasstoast = approvePasstoast;
	}

	public String getApproveFailtoast() {
		return this.approveFailtoast;
	}

	public void setApproveFailtoast(String approveFailtoast) {
		this.approveFailtoast = approveFailtoast;
	}

	public String getActivityUrl() {
		return this.activityUrl;
	}

	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}

	public String getQcodeUrl() {
		return this.qcodeUrl;
	}

	public void setQcodeUrl(String qcodeUrl) {
		this.qcodeUrl = qcodeUrl;
	}

	public String getShareTitle() {
		return this.shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getShareUrl() {
		return this.shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getShareImg() {
		return this.shareImg;
	}

	public void setShareImg(String shareImg) {
		this.shareImg = shareImg;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getJhstartTime() {
		return this.jhstartTime;
	}

	public void setJhstartTime(String jhstartTime) {
		this.jhstartTime = jhstartTime;
	}

	public String getJhendTime() {
		return this.jhendTime;
	}

	public void setJhendTime(String jhendTime) {
		this.jhendTime = jhendTime;
	}

	public Long getLuckAid() {
		return this.luckAid;
	}

	public void setLuckAid(Long luckAid) {
		this.luckAid = luckAid;
	}

	public String getLuckerToast() {
		return this.luckerToast;
	}

	public void setLuckerToast(String luckerToast) {
		this.luckerToast = luckerToast;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
