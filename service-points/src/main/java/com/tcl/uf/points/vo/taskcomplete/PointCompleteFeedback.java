package com.tcl.uf.points.vo.taskcomplete;

import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 意见反馈
 */
@Service
public class PointCompleteFeedback extends OnlyDailyTaskComplete {
	/*
	public PointCompleteFeedback(TaskComplete complete){
	    super(complete);
	}
	
	@Override
	public void check(TokenAppUserInfo userInfo,int typeCode) {
		complete.check(userInfo, typeCode);
	}
	
	@Override
	public void finish(TokenAppUserInfo userInfo,int typeCode) {
		complete.finish(userInfo, typeCode);
	}*/
}
