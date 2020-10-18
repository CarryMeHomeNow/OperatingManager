package com.tcl.uf.points.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcl.commondb.points.model.PointTask;
import com.tcl.commondb.points.model.PointTaskPlan;
import com.tcl.commondb.points.model.PointTaskPointTaskPlan;
import com.tcl.commondb.points.repository.PointTaskPlanRepository;
import com.tcl.commondb.points.repository.PointTaskPointTaskPlanRepository;
import com.tcl.commondb.points.repository.PointTaskRepository;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.points.service.PointTaskPlanService;
import com.tcl.uf.points.service.PointTaskService;
import com.tcl.uf.points.vo.PointTaskPlanBindTaskVo;
import com.tcl.uf.points.vo.PointTaskPlanListVo;
import com.tcl.uf.points.vo.PointTaskPlanNewVo;
import com.tcl.uf.points.vo.PointTaskVo;

@Service
public class PointTaskPlanServiceImpl implements PointTaskPlanService {
	@Autowired
	private PointTaskService pointTaskService;
	@Autowired
	private PointTaskPlanRepository pointTaskPlanRepository;
	@Autowired
	private PointTaskPointTaskPlanRepository pointTaskPointTaskPlanRepository;
	@Autowired
	private PointTaskRepository pointTaskRepository;
	@PersistenceContext
	private EntityManager entityManager;

	/*@Override
	public Map<String, Object> findByType(Integer type, Integer page, Integer size) {
		taskPlanUpdateStatus();
		Map<String, Object> dataMap = new HashMap<>();
		Pageable pageable = PageRequest.of(page, size);
		List<PointTaskPlanVo> list = new ArrayList<>();
		Page<PointTaskPlan> taskPlans = taskPlans = pointTaskPlanRepository.findAllByType(type, pageable);
		for (PointTaskPlan taskPlan : taskPlans) {
			// 通过中间表统计任务id获取任务数量
			int taskNum = pointTaskPointTaskPlanRepository.countPointTaskByPointTaskPlanId(taskPlan.getId());
			PointTaskPlanVo pointTaskPlanVo = new PointTaskPlanVo();
			pointTaskPlanVo.setType(type);
			pointTaskPlanVo.setId(taskPlan.getId());
			pointTaskPlanVo.setTitle(taskPlan.getTitle());
			pointTaskPlanVo.setStartTime(taskPlan.getStartTime());
			pointTaskPlanVo.setEndTime(taskPlan.getEndTime());
			pointTaskPlanVo.setNum(taskNum);
			pointTaskPlanVo.setStatus(taskPlan.getStatus());
			list.add(pointTaskPlanVo);
		}
		dataMap.put("total", taskPlans.getTotalElements());
		dataMap.put("rows", list);
		return dataMap;
	
	}*/

	@Override
	public Map<String, Object> findByType(Integer type, Integer page, Integer size) {
		Long time = System.currentTimeMillis();
		taskPlanUpdateStatus(time);
		Map<String, Object> dataMap = new HashMap<>();
		if (page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, size);
		List<PointTaskPlanBindTaskVo> list = new ArrayList<>();
		//查询到所有计划列表
		Page<PointTaskPlan> taskPlans = taskPlans = pointTaskPlanRepository.findAllByType(type, pageable);
		//如果type为1，而且新手任务列表为空，则默认添加一条新手任务计划并绑定所有的新手任务
		List<PointTaskPlan> planList = taskPlansCheck(taskPlans.getContent(),type);
		List<Long> ids = new ArrayList<>();
		planList.forEach(task -> { ids.add(task.getId());});
		List<Map<String, Object>> tasList = pointTaskPointTaskPlanRepository
				.queryByIds(ids.toArray(new Long[ids.size()]));
		Map<Long, List<PointTaskVo>> task = new HashMap<>();
		for (Long pid : ids) {
			List<PointTaskVo> taskTempList = new ArrayList<>();
			for (Map<String, Object> map : tasList) {
				Long id = Long.parseLong(String.valueOf(map.get("plan_id")));
				if (pid.equals(id)) {
					PointTaskVo taskM = new PointTaskVo();
					taskM.setAppPath(String.valueOf(isNotBlank(map.get("app_path"))));
					taskM.setContent(String.valueOf(isNotBlank(map.get("content"))));
					taskM.setH5Path(String.valueOf(isNotBlank(map.get("h5_path"))));
					taskM.setId(Long.parseLong(String.valueOf(map.get("id"))));
					taskM.setMaxNum(Integer.parseInt(String.valueOf(returnZero(map.get("max_num")))));
					taskM.setAmount(Integer.parseInt(String.valueOf(returnZero(map.get("reward")))));
					taskM.setTitle(String.valueOf(isNotBlank(map.get("title"))));
					taskM.setType(Integer.parseInt(String.valueOf(returnZero(map.get("type")))));
					taskM.setUrl(String.valueOf(isNotBlank(map.get("url"))));
					taskM.setXcxAppId(String.valueOf(isNotBlank(map.get("xcx_app_id"))));
					taskM.setXcxGhId(String.valueOf(isNotBlank(map.get("xcx_gh_id"))));
					taskM.setXcxPath(String.valueOf(isNotBlank(map.get("xcx_path"))));
					taskTempList.add(taskM);
				}
			}
			task.put(pid, taskTempList);
		}
		for (PointTaskPlan taskPlan : planList) {
			// 通过中间表统计任务id获取任务数量
			int taskNum = pointTaskPointTaskPlanRepository.countPointTaskByPointTaskPlanId(taskPlan.getId());
			PointTaskPlanBindTaskVo pointTaskPlanVo = new PointTaskPlanBindTaskVo();
			pointTaskPlanVo.setType(type);
			pointTaskPlanVo.setId(taskPlan.getId());
			pointTaskPlanVo.setTitle(taskPlan.getTitle());
			pointTaskPlanVo.setStartTime(taskPlan.getStartTime());
			pointTaskPlanVo.setEndTime(taskPlan.getEndTime());
			pointTaskPlanVo.setNum(taskNum);
			pointTaskPlanVo.setStatus(taskPlan.getStatus());
			pointTaskPlanVo.setTaskList(task.get(taskPlan.getId()));
			list.add(pointTaskPlanVo);
		}
		dataMap.put("total", taskPlans.getTotalElements());
		dataMap.put("rows", list);
		return dataMap;

	}

	//根据计划类型来是不是新手计划，新手计划则要另外处理
	private List<PointTaskPlan> taskPlansCheck(List<PointTaskPlan> taskPlans, Integer type) {
		if(type.equals(1)) {
			if(taskPlans == null || taskPlans.isEmpty()) {
				PointTaskPlan newPlan = new PointTaskPlan();
				newPlan.setCreateTime(TimeUtils.getCurrentTimeMillis());
				newPlan.setEndTime(0l);
				newPlan.setIsDelete((byte)1);
				newPlan.setStartTime(0l);
				newPlan.setStatus(2);
				newPlan.setTitle("新手任务计划");
				newPlan.setType(type);
				newPlan.setUpdateTime(TimeUtils.getCurrentTimeMillis());
				newPlan = pointTaskPlanRepository.saveAndFlush(newPlan);
				List<PointTask> taskList = pointTaskRepository.findByTypeAndIsDelete(1,1);
				List<PointTaskPointTaskPlan> batchList = new ArrayList<>();
				for (PointTask pointTask : taskList) {
					PointTaskPointTaskPlan pointTaskPointTaskPlan = new PointTaskPointTaskPlan();
					pointTaskPointTaskPlan.setPointTaskId(pointTask.getId());
					pointTaskPointTaskPlan.setPointTaskPlanId(newPlan.getId());
					batchList.add(pointTaskPointTaskPlan);
				}
				batchInsert(batchList);
				taskPlans.add(newPlan);
			}
		}
		return taskPlans;
	}

	@Override
	public PointTaskPlanListVo findById(Long id) {

		PointTaskPlanListVo pointTaskPlanListVo = new PointTaskPlanListVo();
		PointTaskPlan pointTaskPlan = pointTaskPlanRepository.findByIdEquals(id);

		Long[] taskIds = pointTaskPointTaskPlanRepository.findByPointTaskPlanId(id);
		List<PointTaskVo> pointTaskVoList = pointTaskService.findByIds(taskIds);

		pointTaskPlanListVo.setId(pointTaskPlan.getId());
		pointTaskPlanListVo.setEndTime(pointTaskPlan.getEndTime());
		pointTaskPlanListVo.setStartTime(pointTaskPlan.getStartTime());
		pointTaskPlanListVo.setTitle(pointTaskPlan.getTitle());
		pointTaskPlanListVo.setType(pointTaskPlan.getStatus());

		pointTaskPlanListVo.setPointTaskVoList(pointTaskVoList);

		return pointTaskPlanListVo;
	}

	/**
	 * 查找保存时的时间冲突问题
	 */
	@Override
	public boolean findTimeConflictList(Long startTime, Long endTime, Integer type,Long id) {
		List<PointTaskPlan> pointTaskPlan = new ArrayList<>();
		if(id != null) {
			pointTaskPlan = pointTaskPlanRepository.findOneTimeConflictForUpdate(startTime, endTime, type,id);
		}else {
			pointTaskPlan = pointTaskPlanRepository.findOneTimeConflict(startTime, endTime, type);
		}
		return (!pointTaskPlan.isEmpty());
	}
	

	/**
	 * 校验任务id是否都存在
	 */
	@Override
	public boolean taskIdNotExit(Long[] taskId) {
		List<PointTask> pointTasks = pointTaskRepository.findByIds(taskId);
		return (pointTasks.size() == taskId.length);
	}

	@Transactional
	@Override
	public boolean insertTaskPlan(PointTaskPlanNewVo pointTaskPlanNewVo) {

		Date date = new Date();
		long time = date.getTime();
		PointTaskPlan pointTaskPlan = new PointTaskPlan();
		pointTaskPlan.setEndTime(pointTaskPlanNewVo.getEndTime());
		pointTaskPlan.setTitle(pointTaskPlanNewVo.getTitle());
		pointTaskPlan.setType(pointTaskPlanNewVo.getType());
		pointTaskPlan.setCreateTime(time);
		pointTaskPlan.setUpdateTime(time);
		pointTaskPlan.setStartTime(pointTaskPlanNewVo.getStartTime());
		pointTaskPlan.setStatus((pointTaskPlanNewVo.getStartTime() <= time) ? 2 : 1);
		// 新建默认
		pointTaskPlan.setIsDelete((byte) 1);
		PointTaskPlan taskPlan = pointTaskPlanRepository.saveAndFlush(pointTaskPlan);
		Long[] taskIds = pointTaskPlanNewVo.getTaskId();
		List<PointTaskPointTaskPlan> batchList = new ArrayList<>();
		for (Long taskId : taskIds) {
			PointTaskPointTaskPlan pointTaskPointTaskPlan = new PointTaskPointTaskPlan();
			pointTaskPointTaskPlan.setPointTaskId(taskId);
			pointTaskPointTaskPlan.setPointTaskPlanId(taskPlan.getId());
			batchList.add(pointTaskPointTaskPlan);
			// pointTaskPointTaskPlanRepository.saveLinkEntity(pointTaskPointTaskPlan);
		}
		batchInsert(batchList);
		return true;
	}

	// 批量保存
	@Modifying
	private void batchInsert(List<PointTaskPointTaskPlan> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO point_task_point_task_plan(point_task_id,point_task_plan_id) VALUES ");
		for (int i = 0; i < list.size(); i++) {
			sb.append("(?, ?),");
		}
		// 有则更新，无则新增
		String sql = sb.toString().substring(0, sb.length() - 1);
		StringBuilder onkdy = new StringBuilder();
		onkdy.append(" ON DUPLICATE KEY UPDATE `point_task_plan_id` = VALUES(`point_task_plan_id`)");
		Query query = entityManager.createNativeQuery(sql+onkdy.toString());
		int paramIndex = 1;
		for (PointTaskPointTaskPlan taskPlan : list) {
			query.setParameter(paramIndex++, taskPlan.getPointTaskId());
			query.setParameter(paramIndex++, taskPlan.getPointTaskPlanId());
		}
		query.executeUpdate();
	}


	@Transactional
	@Override
	public boolean updateTaskPlan(PointTaskPlanNewVo pointTaskPlanNewVo) {
		Date date = new Date();
		long time = date.getTime();
		pointTaskPlanRepository.updateTaskPlan(time, pointTaskPlanNewVo.getId(),pointTaskPlanNewVo.getTitle(),pointTaskPlanNewVo.getStartTime(),pointTaskPlanNewVo.getEndTime());
		Long[] taskIds = pointTaskPlanNewVo.getTaskId();
		List<PointTaskPointTaskPlan> batchList = new ArrayList<>();
		for (Long taskId : taskIds) {
			PointTaskPointTaskPlan pointTaskPointTaskPlan = new PointTaskPointTaskPlan();
			pointTaskPointTaskPlan.setPointTaskId(taskId);
			pointTaskPointTaskPlan.setPointTaskPlanId(pointTaskPlanNewVo.getId());
			batchList.add(pointTaskPointTaskPlan);
			// pointTaskPointTaskPlanRepository.saveLinkEntity(pointTaskPointTaskPlan);
		}
		batchInsert(batchList);
		return true;

	}

	@Override
	public boolean deletePlan(Long id) {

		// 软删除计划表
		pointTaskPlanRepository.updateIsDelete(id);
		// pointTaskPlanRepository.deleteById(id);
		/*Long[] taskPlanPointTaskIds = pointTaskPointTaskPlanRepository.findByPointTaskPlanId(id);
		for (Long taskPlanId : taskPlanPointTaskIds) {
			pointTaskPointTaskPlanRepository.deleteByPointTaskId(taskPlanId);
		}*/
		pointTaskPointTaskPlanRepository.deleteByPointTaskPlanId(id);
		return true;
	}

	// 判断任务计划是否已经生效
	@Override
	public boolean planStatusIsEffect(Long pointTaskPlanId) {
		Optional<PointTaskPlan> optional = pointTaskPlanRepository.findById(pointTaskPlanId);
		if (optional.isPresent()) {
			PointTaskPlan plan = optional.get();
			return (plan.getStatus().equals(2));
		}
		return false;
	}

	@Override
	public boolean deleteTask(Long pointTaskId, Long pointTaskPlanId) {
		int count = pointTaskPointTaskPlanRepository.deleteByPointTaskIdAndPointTaskPlanId(pointTaskId,
				pointTaskPlanId);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 使用定时任务不太符合实际情况，所以采用查询前更新一次 提前更新所有的计划任务的状态，返回所有生效的任务
	 */
	private void taskPlanUpdateStatus(Long time) {
		pointTaskPlanRepository.updateStatusValid(time);
		pointTaskPlanRepository.updateStatusInvalid(time);
		pointTaskPlanRepository.updateStatusFailure(time);
	}

	// 任务是否存在
	@Override
	public boolean queryPlanIsExit(Long id) {
		PointTaskPlan pointTaskPlan = pointTaskPlanRepository.findByIdEquals(id);
		return (pointTaskPlan != null);
	}

	/**
	 * 避免出现null值的情况
	 * 
	 * @param object
	 * @return
	 */
	private Object isNotBlank(Object object) {
		if (object == null) {
			return "";
		}
		return object;
	}

	/**
	 * 返回0
	 * 
	 * @param object
	 * @return
	 */
	private Object returnZero(Object object) {
		if (object == null) {
			return "0";
		}
		return object;
	}

	@Override
	public boolean isInvaild(Long id) {
		taskPlanUpdateStatus(TimeUtils.getCurrentTimeMillis());
		PointTaskPlan pointTaskPlan = pointTaskPlanRepository.findByIdEquals(id);
		return pointTaskPlan.getStatus().equals(3);
	}

}
