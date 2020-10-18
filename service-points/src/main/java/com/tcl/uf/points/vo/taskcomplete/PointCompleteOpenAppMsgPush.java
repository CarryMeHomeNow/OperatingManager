package com.tcl.uf.points.vo.taskcomplete;

import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * app打开消息推送
 */
@Service
public class PointCompleteOpenAppMsgPush extends OnlyNoviceTaskComplete {
	/*
		public PointCompleteOpenAppMsgPush(TaskComplete complete) {
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
