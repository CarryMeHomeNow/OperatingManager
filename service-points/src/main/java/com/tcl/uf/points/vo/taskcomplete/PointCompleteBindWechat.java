package com.tcl.uf.points.vo.taskcomplete;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;

/**
 * 绑定微信
 */ 
@Service
public class PointCompleteBindWechat extends OnlyNoviceTaskComplete {

	/*public PointCompleteBindWechat(TaskComplete complete) {
		super(complete);
	}
	
	@Override
	public void check(TokenAppUserInfo userInfo, int typeCode) throws JMException {
		// 1校验是否有过该任务送积分任务的记录
		// 如果有 抛异 ，没有则通过
		complete.check(userInfo, typeCode);
	}
	
	@Override
	public void finish(TokenAppUserInfo userInfo, int typeCode) throws JMException {
		// 积分奖级阶段
		// 1 查询当前任务奖级多少积分
		// 2 记录任务完成记录
		// 3 进行积分奖励
		complete.finish(userInfo, typeCode);
	}*/

}
