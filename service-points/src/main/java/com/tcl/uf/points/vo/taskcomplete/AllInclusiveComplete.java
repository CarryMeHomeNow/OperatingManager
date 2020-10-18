package com.tcl.uf.points.vo.taskcomplete;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.points.service.PointService;
import com.tcl.uf.points.service.PointTaskService;
import com.tcl.uf.points.vo.AppPointTaskVo;
import com.tcl.uf.points.vo.PointTaskVo;


public class AllInclusiveComplete extends TaskComplete {
	/*@Autowired
	PointTaskService pointTaskService;*/
	@Autowired
	PointService pointService;
	
	/*protected TaskComplete complete;
	
	public AllInclusiveComplete(TaskComplete complete){
	    this.complete = complete;
	}*/

	@Override
	public void check(TokenAppUserInfo userInfo, int typeCode) {
		List<Long> taskIds = pointTaskService.findBySsoidGroupByTaskId(userInfo.getAccountId());
		Long noviceId = pointTaskService.findByValidTaskByTypeCode(typeCode, 1);
		// 先校验是否完成新手 没有则直接通过

		// 已完成新手情况，继续校验是否完成日常。
		if (taskIds.contains(noviceId)) {
			Long dailyId = pointTaskService.findByValidTaskByTypeCode(typeCode, 2);
			// 根据绑定的类型的id查找相应的完成记录
			if (dailyId != null) {
				AppPointTaskVo vo = pointTaskService.queryAppTaskListByTaskId(userInfo.getAccountId(), dailyId);
				if (vo != null) {
					Integer currentNum = vo.getCurrentNum();
					Integer maxNum = vo.getMaxNum();
					if (currentNum.equals(maxNum)) {
						throw new JMException(0, "任务可完成次数已达上限");
					}
				}
			}
		}
		// 完成则抛出 不奖励积分
		// 未完成则通过
	}

	@Override
	public void finish(TokenAppUserInfo userInfo, int typeCode,String source) {
		// 先校验是否完成新手。
		List<Long> taskIds = pointTaskService.findBySsoidGroupByTaskId(userInfo.getAccountId());
		Long taskId = pointTaskService.findByValidTaskByTypeCode(typeCode, 1);
		AppPointTaskVo vo = new AppPointTaskVo();
		if (!taskIds.contains(taskId)) {
			// 未完成 则记录完成新手记录 再奖级积分 结束
			vo = pointTaskService.queryAppTaskListByTaskId(userInfo.getAccountId(), taskId);
		} else {
			// 已完成新手，则直接记录完成日常任务记录。奖级积分。结束
			taskId = pointTaskService.findByValidTaskByTypeCode(typeCode, 2);
			vo = pointTaskService.queryAppTaskListByTaskId(userInfo.getAccountId(), taskId);
		}
		pointTaskService.saveCurrentNum(userInfo.getAccountId(), vo.getId(), vo.getPlanId());
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
