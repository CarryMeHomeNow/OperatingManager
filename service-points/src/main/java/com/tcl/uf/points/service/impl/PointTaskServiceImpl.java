package com.tcl.uf.points.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tcl.commondb.points.model.PointTask;
import com.tcl.commondb.points.model.PointTaskAppFinishDetailModel;
import com.tcl.commondb.points.model.PointTaskPlan;
import com.tcl.commondb.points.model.PointTaskPointTaskPlan;
import com.tcl.commondb.points.repository.PointTaskAppFinishDetailModelRepository;
import com.tcl.commondb.points.repository.PointTaskPlanRepository;
import com.tcl.commondb.points.repository.PointTaskPointTaskPlanRepository;
import com.tcl.commondb.points.repository.PointTaskRepository;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.points.service.PointTaskService;
import com.tcl.uf.points.vo.AppPointTaskVo;
import com.tcl.uf.points.vo.PointTaskDetailVo;
import com.tcl.uf.points.vo.PointTaskVo;

@Service
public class PointTaskServiceImpl implements PointTaskService {

	private static final Logger log = LoggerFactory.getLogger(PointTaskServiceImpl.class);

	@Autowired
	private PointTaskRepository pointTaskRepository;

	@Autowired
	private PointTaskPlanRepository pointTaskPlanRepository;

	@Autowired
	private PointTaskAppFinishDetailModelRepository appTask;

	@Autowired
	private PointTaskPointTaskPlanRepository pointTaskPointTaskPlanRepository;

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public List<PointTaskVo> findByIds(Long[] ids) {
		List<PointTask> pointTasks = pointTaskRepository.findByIds(ids);
		return modelToVo(pointTasks);
	}

	@Override
	public Map<String, Object> findAll(Integer page, Integer size) {
		HashMap<String, Object> map = new HashMap<>();
		Pageable pageable = PageRequest.of(page, size);
		List<PointTaskVo> list = new ArrayList<>();

		Page<PointTask> pointTasks = pointTaskRepository.findAll(pageable);
		list = modelToVo(pointTasks.getContent());
		map.put("total", pointTasks.getTotalElements());
		map.put("rows", list);
		return map;
	}

	@Override
	public Map<String, Object> findByType(Integer type, Integer page, Integer size) {
		Map<String, Object> map = new HashMap<>();
		if (page > 0) {
			page = page - 1;
		}
		List<PointTaskVo> list = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, size);
		Page<PointTask> pointTasks = pointTaskRepository.findByType(type, pageable);
		list = modelToVo(pointTasks.getContent());
		map.put("total", pointTasks.getTotalElements());
		map.put("rows", list);
		return map;
	}

	@Override
	public boolean saveOrUpdate(PointTaskVo pointTaskVo) {
		Long id = pointTaskVo.getId();
		Boolean flag = false;
		Date date = new Date();
		long time = date.getTime();
		PointTask pointTask = new PointTask();
		BeanUtils.copyProperties(pointTaskVo, pointTask);
		pointTask.setReward(pointTaskVo.getAmount());
		pointTask.setUpdateTime(time);
		pointTask.setTypeCode(0);
		if (id == null) {
			pointTask.setCreateTime(time);
			pointTask.setIsDelete((byte) 1);
			pointTask = pointTaskRepository.saveAndFlush(pointTask);
			if (pointTask.getId() != null) {
				flag = true;
				//判断是否为新手任务
				if(pointTaskVo.getType().equals(1)) {
					PointTaskPlan plan = pointTaskPlanRepository.findFirstByTypeAndIsDelete(1,1);
					//自动将新手任务绑定在新手任务计划里面
					if(plan!=null) {
						PointTaskPointTaskPlan bindModel = new PointTaskPointTaskPlan();
						bindModel.setPointTaskId(pointTask.getId());
						bindModel.setPointTaskPlanId(plan.getId());
						pointTaskPointTaskPlanRepository.saveAndFlush(bindModel);
					}
				}
			}
		} else {
			pointTask.setId(id);
			if (pointTaskRepository.updateTask(pointTask) > 0) {
				flag = true;
			}
		}
		return flag;
	}

	/*@Override
	public boolean updatePask(PointTaskVo pointTaskVo) {
		Date date = new Date();
		long time = date.getTime();
		PointTask pointTask = new PointTask();
		pointTask.setTitle(pointTaskVo.getTitle());
		pointTask.setMaxNum(pointTaskVo.getMaxNum());
		pointTask.setId(pointTaskVo.getId());
		pointTask.setReward(pointTaskVo.getAmount());
		pointTask.setUrl(pointTaskVo.getUrl());
		pointTask.setAppPath(pointTaskVo.getAppPath());
		pointTask.setH5Path(pointTaskVo.getH5Path());
		pointTask.setXcxAppId(pointTaskVo.getXcxAppId());
		pointTask.setXcxPath(pointTaskVo.getXcxPath());
		pointTask.setXcxGhId(pointTaskVo.getXcxGhId());
		pointTask.setUpdateTime(time);
		pointTask.setCreateTime(time);
		pointTask.setContent(pointTaskVo.getContent());
		pointTask.setType(pointTaskVo.getType());
		pointTaskRepository.saveAndFlush(pointTask);
		return true;
	}
	
	
	
	@Override
	public boolean insertPask(PointTaskVo pointTaskVo) {
	
		Date date = new Date();
		long time = date.getTime();
		PointTask pointTask = new PointTask();
		pointTask.setTitle(pointTaskVo.getTitle());
		pointTask.setMaxNum(pointTaskVo.getMaxNum());
		pointTask.setReward(pointTaskVo.getAmount());
		pointTask.setUrl(pointTaskVo.getUrl());
		pointTask.setAppPath(pointTaskVo.getAppPath());
		pointTask.setH5Path(pointTaskVo.getH5Path());
		pointTask.setXcxAppId(pointTaskVo.getXcxAppId());
		pointTask.setXcxPath(pointTaskVo.getXcxPath());
		pointTask.setXcxGhId(pointTaskVo.getXcxGhId());
		pointTask.setCreateTime(time);
		pointTask.setUpdateTime(time);
		pointTask.setContent(pointTaskVo.getContent());
		// 新建默认1
		pointTask.setIsDelete((byte) 1);
		pointTask.setType(pointTaskVo.getType());
		pointTaskRepository.saveAndFlush(pointTask);
		return true;
	
	}*/

	@Override
	public boolean deleteById(Long id) {
		// 先判断改任务是否已删除状态
		PointTask result = pointTaskRepository.findByIdEquals(id);
		if (result.getIsDelete() == 2) {
			return false;
		}
		// 先删除关联表数据
		pointTaskPointTaskPlanRepository.deleteByPointTaskId(id);
		// 修改isDelete,使用软删除
		Integer delTolal = pointTaskRepository.updateIsDelete(id);
		if (delTolal == 0) {
			return false;
		}
		return true;
	}

	@Override
	public PointTaskDetailVo findDetail(Long id) {

		PointTaskDetailVo pointTaskDetailVo = new PointTaskDetailVo();

		PointTask detail = pointTaskRepository.findByIdEquals(id);
		if (detail != null) {
			pointTaskDetailVo.setId(detail.getId());
			pointTaskDetailVo.setAmount(detail.getReward());
			pointTaskDetailVo.setContent(detail.getContent());
			pointTaskDetailVo.setMaxNum(detail.getMaxNum());
			pointTaskDetailVo.setPath(detail.getAppPath());
			pointTaskDetailVo.setTitle(detail.getTitle());
			pointTaskDetailVo.setType(detail.getType());
			pointTaskDetailVo.setStartTime(detail.getCreateTime());
			pointTaskDetailVo.setUrl(detail.getUrl());
		}
		return pointTaskDetailVo;
	}

	/**
	 * app端任务列表查询
	 */
	@Override
	public Map<String, Object> queryAppTaskList(Long ssoid) {
		Map<String, Object> returnMap = new HashMap<>();
		Long time = System.currentTimeMillis();
		taskPlanUpdateStatus(time);
		List<AppPointTaskVo> voList = new ArrayList<>();
		//List<Long> completeTask = appTask.findBySsoidGroupByTaskId(ssoid);
 		List<Map<String, Object>> dataList = appTask.findAppTaskListBySsoid(ssoid);
		for (Map<String, Object> map : dataList) {
			Long id = Long.parseLong(String.valueOf(isNotBlank(map.get("id"))));
			Integer type = (map.get("type") == null ? 2 : Integer.parseInt(String.valueOf(map.get("type"))));
			Integer maxNum = Integer.parseInt(String.valueOf(returnZero(map.get("max_num"))));
			Integer currentNum = getCurrunNum(map, time);
			//新手任务列表完成后不再展示
			/*if(type.equals(1) && completeTask.contains(id)) {
				if(currentNum >= maxNum) {
					continue;
				}
			}*/
			AppPointTaskVo vo = new AppPointTaskVo();
			vo.setId(id)
			.setType(type)
			.setPlanId(Long.parseLong(String.valueOf(isNotBlank(map.get("plan_id")))))
			.setAmount(Integer.parseInt(String.valueOf(returnZero(map.get("amout")))))
			.setAppPath(String.valueOf(isNotBlank(map.get("app_path")))).setCurrentNum(currentNum)
			.setMaxNum(maxNum)
			.setStartTime(Long.parseLong(String.valueOf(isNotBlank(map.get("start_time")))))
			.setTitle(String.valueOf(isNotBlank(map.get("title"))))
			.setUrl(String.valueOf(isNotBlank(map.get("url"))));
			voList.add(vo);
		}
		returnMap.put("list", voList);
		return returnMap;
	}

	private Integer getCurrunNum(Map<String, Object> map, long time) {
		Integer returnCode = 0;
		Integer type = map.get("type") == null ? 2 : Integer.parseInt(String.valueOf(map.get("type")));
		if (map.get("current_num") != null) {
			if (type.equals(1)) {
				returnCode = Integer.parseInt(String.valueOf(map.get("current_num")));
			} else {
				if (format.format(time).equals(format.format(Long.parseLong(String.valueOf(map.get("finish_time")))))) {
					returnCode = Integer.parseInt(String.valueOf(map.get("current_num")));
				}
			}
		}
		return returnCode;
	}

	/**
	 * 提前更新所有的计划任务的状态，返回所有生效的任务
	 */
	private void taskPlanUpdateStatus(Long time) {
		pointTaskPlanRepository.updateStatusValid(time);
		pointTaskPlanRepository.updateStatusInvalid(time);
		pointTaskPlanRepository.updateStatusFailure(time);
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

	/**
	 * 返回状态与插入条数 status： 1、正常，2、任务失效，3、完成次数达到上限
	 */
	@Override
	public Map<String, Object> saveCurrentNum(Long ssoid, Long taskId, Long planId) {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("status", "1");
		Integer insertCount = 0;
		Long endTime = pointTaskPlanRepository.queryVaildTimeByTaskId(planId);
		Long now = System.currentTimeMillis();
		// 判断任务是否处于有生效状态
		if (endTime == null || (endTime != null && endTime > now)) {
			// 判断次数是否已经达到上限
			PointTask task = new PointTask();
			PointTaskAppFinishDetailModel appTaskModel = appTask.findFirstBySsoidAndTaskIdOrderByFinishTimeDesc(ssoid,
					taskId);
			Optional<PointTask> taskOptional = pointTaskRepository.findById(taskId.longValue());
			if (taskOptional.isPresent()) {
				task = taskOptional.get();
			}
			Integer num = 0;
			if (appTaskModel != null
					&& (format.format(new Date(appTaskModel.getFinishTime())).equals(format.format(new Date(now))))
					&& task.getType().equals(2)) {
				num = appTaskModel.getCurrentNum();
			}else if(appTaskModel != null && task.getType().equals(1)) {
				num = appTaskModel.getCurrentNum();
			}
			Integer maxNum = task.getMaxNum();
			// 次数未达上限，往detail表中新增一条数据
			if (num < maxNum) {
				num = num + 1;
				PointTaskAppFinishDetailModel model = new PointTaskAppFinishDetailModel();
				model.setCurrentNum(num);
				model.setFinishTime(now);
				model.setSsoid(ssoid);
				model.setTaskId(taskId);
				PointTaskAppFinishDetailModel newModel = appTask.save(model);
				if (newModel.getId() != null) {
					insertCount = 1;
				}
			} else {
				returnMap.put("status", "3");
			}
		} else {
			returnMap.put("status", "2");
		}
		returnMap.put("count", insertCount);
		return returnMap;
	}

	@Override
	public boolean isExit(Long id) {
		// 先判断任务是否已经在任务计划中
		List<PointTaskPointTaskPlan> exitList = pointTaskPointTaskPlanRepository.findExitList(id);
		return (exitList != null && !exitList.isEmpty());
	}

	@Override
	public List<PointTaskVo> queryValidTask() {
		taskPlanUpdateStatus(TimeUtils.getCurrentTimeMillis());
		List<PointTask> list = pointTaskRepository.queryValidTask();
		return modelToVo(list);
	}

	private List<PointTaskVo> modelToVo(List<PointTask> pointTasks) {
		List<PointTaskVo> list = new ArrayList<>();
		for (PointTask pointTask : pointTasks) {
			PointTaskVo pointTaskVo = new PointTaskVo();
			pointTaskVo.setId(pointTask.getId());
			pointTaskVo.setType(pointTask.getType());
			pointTaskVo.setAmount(pointTask.getReward());
			pointTaskVo.setMaxNum(pointTask.getMaxNum());
			pointTaskVo.setTitle(pointTask.getTitle());
			pointTaskVo.setUrl(pointTask.getUrl());
			pointTaskVo.setAppPath(pointTask.getAppPath());
			pointTaskVo.setXcxPath(pointTask.getXcxPath());
			pointTaskVo.setH5Path(pointTask.getH5Path());
			pointTaskVo.setXcxAppId(pointTask.getXcxAppId());
			pointTaskVo.setXcxGhId(pointTask.getXcxGhId());
			pointTaskVo.setContent(pointTask.getContent());
			pointTaskVo.setTypeCode(pointTask.getTypeCode());
			list.add(pointTaskVo);
		}
		return list;
	}

	@Override
	public Long findByValidTaskByTypeCode(int typeCode, int type) {
		return pointTaskRepository.findByValidTaskByTypeCode(typeCode,type);
	}

	@Override
	public List<Long> findBySsoidGroupByTaskId(Long accountId) {
		return appTask.findBySsoidGroupByTaskId(accountId);
	}

	@Override
	public AppPointTaskVo queryAppTaskListByTaskId(Long ssoid,Long dailyId) {
		Long time = System.currentTimeMillis();
		Map<String, Object> map = appTask.queryAppTaskListByTaskId(ssoid,dailyId);
		AppPointTaskVo vo = new AppPointTaskVo();
		vo.setId(Long.parseLong(String.valueOf(isNotBlank(map.get("id")))))
			.setType((map.get("type") == null ? 2 : Integer.parseInt(String.valueOf(map.get("type")))))
			.setPlanId(Long.parseLong(String.valueOf(isNotBlank(map.get("plan_id")))))
			.setAmount(Integer.parseInt(String.valueOf(returnZero(map.get("amout")))))
			.setAppPath(String.valueOf(isNotBlank(map.get("app_path"))))
			.setCurrentNum(getCurrunNum(map, time))
			.setMaxNum(Integer.parseInt(String.valueOf(returnZero(map.get("max_num")))))
			.setStartTime(Long.parseLong(String.valueOf(isNotBlank(map.get("start_time")))))
			.setTitle(String.valueOf(isNotBlank(map.get("title"))))
			.setUrl(String.valueOf(isNotBlank(map.get("url"))));
		return vo;
	}

	@Override
	public boolean queryTaskStatus(Long id) {
		PointTask task = pointTaskRepository.queryTaskStatus(id);
		return (task != null);
	}

}
