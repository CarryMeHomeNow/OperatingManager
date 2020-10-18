package com.tcl.uf.points.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.points.service.PointTaskPlanService;
import com.tcl.uf.points.service.PointTaskService;
import com.tcl.uf.points.vo.PointTaskPlanListVo;
import com.tcl.uf.points.vo.PointTaskPlanNewVo;

import io.swagger.annotations.ApiOperation;

/**
 * 积分任务计划
 */
@RestController
@RequestMapping("/plan")
public class PointTaskPlanController {

	@Autowired
	private PointTaskPlanService pointTaskPlanService;

	@Autowired
	PointTaskService pointTaskService;

	// 任务计划查询
	@RequestMapping(value = "/list")
	@ApiOperation(value = "任务计划列表查询", notes = "任务计划列表查询", httpMethod = "GET")
	public ResponseData<Map<String, Object>> findAll(@RequestParam(value = "type", required = true) Integer type,
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "size", required = true) Integer size) throws JMException {
		Map<String, Object> map = pointTaskPlanService.findByType(type, page, size);
		return new ResponseData<>(map);
	}

	// 根据计划id查询 计划和任务信息
	@RequestMapping(value = "/listById")
	@ApiOperation(value = "根据计划id查询 计划和任务信息", notes = "根据计划id查询 计划和任务信息", httpMethod = "GET")
	public ResponseData<Object> findById(@RequestParam(value = "id", required = true) Long id) throws JMException {
		PointTaskPlanListVo planListVo = pointTaskPlanService.findById(id);
		return new ResponseData<>(planListVo);
	}

	@RequestMapping(value = "/save")
	@ApiOperation(value = "保存任务计划信息", notes = "保存任务计划信息", httpMethod = "POST")
	public ResponseData<Object> insertPlan(@RequestBody PointTaskPlanNewVo pointTaskPlanNewVo) throws JMException {
		if(pointTaskPlanNewVo == null) {
			return new ResponseData<>(0,"参数对象不能为空","object can not be null");
		}
		Map<String, Object> map = voCheckNotNull(pointTaskPlanNewVo);
		boolean flag = Boolean.parseBoolean(String.valueOf(map.get("flag")));
		if(!flag) {
			return new ResponseData<>(0,String.valueOf(map.get("msg")),"param is null");
		}
		Long[] taskId = pointTaskPlanNewVo.getTaskId();
		if(taskId.length == 0) {
			return new ResponseData<>(0,"绑定的任务列表不能为空","tasklist isn't allow null");
		}
		if(pointTaskPlanService.findTimeConflictList(pointTaskPlanNewVo.getStartTime(),pointTaskPlanNewVo.getEndTime(),pointTaskPlanNewVo.getType(),pointTaskPlanNewVo.getId())) {
			return new ResponseData<>(0,"选择的时间范围发生冲突","save failed");
		}
		if(!pointTaskPlanService.taskIdNotExit(taskId)) {
			return new ResponseData<>(0,"有任务已被删除，请刷新","save failed");
		}
		boolean result = pointTaskPlanService.insertTaskPlan(pointTaskPlanNewVo);
		if(!result) {
			return new ResponseData<>(0,"新增失败","save failed");
		}
		return new ResponseData<>(result);

	}

	// 删除任务计划
	@RequestMapping(value = "/del/{id}")
	@ApiOperation(value = "删除任务计划", notes = "删除任务计划", httpMethod = "GET")
	public ResponseData<Object> delPlan(@PathVariable(value = "id",required = true)Long id) throws JMException {
		/*if(pointTaskPlanService.planStatusIsEffect(id)) {
			return new ResponseData<>(0,"该任务计划已生效，无法删除","delete failed");
		}*/
		boolean result = pointTaskPlanService.deletePlan(id);
		if(!result) {
			return new ResponseData<>(0,"任务删除失败","delete failed");
		}
		return new ResponseData<>(result);
	}

	// 删除中间表的任务
	@RequestMapping(value = "/delTask",method = RequestMethod.GET)
	@ApiOperation(value = "删除中间表的任务", notes = "删除中间表的任务", httpMethod = "GET")
	public ResponseData<Object> delTask(@RequestParam("pointTaskId") Long pointTaskId,
			@RequestParam("pointTaskPlanId") Long pointTaskPlanId) {
		/*if(pointTaskPlanService.planStatusIsEffect(pointTaskPlanId)) {
			return new ResponseData<>(0,"该任务计划已生效，无法删除","delete failed");
		}*/
		boolean result = pointTaskPlanService.deleteTask(pointTaskId, pointTaskPlanId);
		if(!result) {
			return new ResponseData<>(0,"删除失败","delete failed");
		}
		return new ResponseData<>(result);
	}

	// 编辑任务计划
	@RequestMapping("/update")
	@ApiOperation(value = "更新任务计划 ", notes = "更新任务计划", httpMethod = "POST")
	public ResponseData<Object> updatePlan(@RequestBody PointTaskPlanNewVo pointTaskPlanNewVo) throws JMException {
		if(pointTaskPlanNewVo == null) {
			return new ResponseData<>(0,"参数对象不能为空","object can not be null");
		}
		boolean invaild = pointTaskPlanService.isInvaild(pointTaskPlanNewVo.getId());
		if(invaild) {
			return new ResponseData<>(0,"该任务已失效，不能修改","");
		}
		Map<String, Object> map = voCheckNotNull(pointTaskPlanNewVo);
		boolean flag = Boolean.parseBoolean(String.valueOf(map.get("flag")));
		if(!flag) {
			return new ResponseData<>(0,String.valueOf(map.get("msg")),"param is null");
		}
		if(!pointTaskPlanService.queryPlanIsExit(pointTaskPlanNewVo.getId())) {
			return new ResponseData<>(0,"该任务计划已被删除，无法更新删除","update failed");
		}
		if(pointTaskPlanService.findTimeConflictList(pointTaskPlanNewVo.getStartTime(),pointTaskPlanNewVo.getEndTime(),pointTaskPlanNewVo.getType(),pointTaskPlanNewVo.getId())) {
			return new ResponseData<>(0,"选择的时间范围发生冲突","save failed");
		}
		boolean result = pointTaskPlanService.updateTaskPlan(pointTaskPlanNewVo);
		if(!result) {
			return new ResponseData<>(0,"保存失败","save failed");
		}
		return new ResponseData<>(result);
	}

	private Map<String, Object> voCheckNotNull(PointTaskPlanNewVo vo){
		Map<String, Object> map = new HashMap<>();
		map.put("flag", false);
		String msg = "";
		if(vo.getStartTime()== null){
			msg = "计划开始生效时间不能为空";
			map.put("msg", msg);
			return map;
		}else if((vo.getStartTime()+300000l) < TimeUtils.getCurrentTimeMillis()){
			msg = "计划开始生效时间不能早于当前时间超过5分钟";
			map.put("msg", msg);
			return map;
		} else if(vo.getEndTime() == null){
			msg = "计划生效结束时间不能为空";
			map.put("msg", msg);
			return map;
		}else if(vo.getStartTime() >= vo.getEndTime()) {
			msg = "开始时间不能大于等于结束时间";
			map.put("msg", msg);
			return map;
		}else if(StringUtils.isEmpty(vo.getTitle())){
			msg = "任务计划标题不能为空";
			map.put("msg", msg);
			return map;
		}else if(vo.getType() == null){
			msg = "任务计划类型不能为空";
			map.put("msg", msg);
			return map;
		}
		map.put("flag", true);
		return map;
	}
	
}
