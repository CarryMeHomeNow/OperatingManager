package com.tcl.uf.points.vo.taskcomplete;

import org.springframework.beans.factory.annotation.Autowired;

import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.points.service.PointService;
import com.tcl.uf.points.vo.AppPointTaskVo;

 
public class OnlyDailyTaskComplete extends TaskComplete {
	/*@Autowired
	PointTaskService pointTaskService;*/
	@Autowired
	PointService pointService;
	
	/*protected TaskComplete complete;
	
	public OnlyDailyTaskComplete(TaskComplete complete) {
		this.complete = complete;
	}*/

	@Override
	public void check(TokenAppUserInfo userInfo, int typeCode) {
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
		// 完成则抛出 不奖励积分
		// 未完成则通过
	}

	@Override
	public void finish(TokenAppUserInfo userInfo, int typeCode,String source) {
		Long taskId = pointTaskService.findByValidTaskByTypeCode(typeCode, 2);
		AppPointTaskVo vo = pointTaskService.queryAppTaskListByTaskId(userInfo.getAccountId(), taskId);
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
