package com.tcl.uf.points.vo.taskcomplete;

import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 发送邀请好友链接任务
 */
@Service
public class PointCompleteSendInviteFirendLink extends OnlyDailyTaskComplete {

	/*public PointCompleteSendInviteFirendLink(TaskComplete complete) {
		super(complete);
	}
	
	@Override
	public void check(TokenAppUserInfo userInfo, int typeCode) {
		complete.check(userInfo, typeCode);
	}
	
	@Override
	public void finish(TokenAppUserInfo userInfo, int typeCode) {
		complete.finish(userInfo, typeCode);
	}*/
}
