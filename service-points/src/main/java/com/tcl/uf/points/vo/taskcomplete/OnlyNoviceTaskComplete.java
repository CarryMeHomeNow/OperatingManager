package com.tcl.uf.points.vo.taskcomplete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.points.service.PointService;
import com.tcl.uf.points.vo.AppPointTaskVo;

 
public class OnlyNoviceTaskComplete extends TaskComplete {
	/*@Autowired
	PointTaskService pointTaskService;*/
	@Autowired
	PointService pointService;

	/*protected TaskComplete complete;
	
	public OnlyNoviceTaskComplete(TaskComplete complete) {
		this.complete = complete;
	}*/

	@Override
	public void check(TokenAppUserInfo userInfo, int typeCode) throws JMException {
		List<Long> taskIds = pointTaskService.findBySsoidGroupByTaskId(userInfo.getAccountId());
		Long noviceId = pointTaskService.findByValidTaskByTypeCode(typeCode, 1);
		// 1校验是否有过该任务送积分任务的记录
		if (taskIds.contains(noviceId)) {
			// 如果有 抛异 ，没有则通过
			throw new JMException(0, "该新手任务已完成");
		}
	}

	@Override
	public void finish(TokenAppUserInfo userInfo, int typeCode,String source) throws JMException {
		Long taskId = pointTaskService.findByValidTaskByTypeCode(typeCode, 1);
		// 积分奖级阶段
		// 1 查询当前任务奖级多少积分
		AppPointTaskVo vo = pointTaskService.queryAppTaskListByTaskId(userInfo.getAccountId(), taskId);
		// 2 记录任务完成记录
		pointTaskService.saveCurrentNum(userInfo.getAccountId(), vo.getId(), vo.getPlanId());
		// 3 进行积分奖励
		pointService.pushPoints(userInfo.getToken(), userInfo.getAccountId(), vo.getAmount(), "完成积分任务触发", source, "+",
				"积分任务",0);
	}
	
	/*@Override
	public void taskEffective(int type) throws JMException {
		List<PointTaskVo> list = pointTaskService.queryValidTask();
		List<Integer> typeCodeList = list.stream().map(PointTaskVo::getTypeCode).collect(Collectors.toList());
		if (!typeCodeList.contains(type)) {
			throw new JMException("任务已过期");
		}
	}*/
}
