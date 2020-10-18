package com.tcl.uf.points.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.tcl.commonservice.service.FileService;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.points.service.PointTaskService;
import com.tcl.uf.points.vo.PointTaskDetailVo;
import com.tcl.uf.points.vo.PointTaskVo;

import io.swagger.annotations.ApiOperation;

/**
 * 积分任务管理
 */

@RestController
@RequestMapping("/task")
public class PointTaskCntroller extends AbstractBaseController{

	@Autowired
	private PointTaskService pointTaskService;

	@Autowired
	private FileService fileService;

	private static final Logger log = LoggerFactory.getLogger(PointTaskCntroller.class);

	/**
	 * 根据类型不通分别查询新手任务和日常任务
	 */
	@RequestMapping(value = "/listByType", method = RequestMethod.GET)
	@ApiOperation(value = "根据类型不通分别查询新手任务和日常任务", notes = "根据类型不通分别查询新手任务和日常任务", httpMethod = "GET")
	public ResponseData<Map<String, Object>> findByType(@RequestParam(value = "type", required = true) Integer type,
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "size", required = true) Integer size) throws JMException {
		Map<String, Object> pointTaskVoList = pointTaskService.findByType(type, page, size);
		return new ResponseData<>(pointTaskVoList);
	}

	@RequestMapping("/list")
	@ApiOperation(value = "查询所有任务", notes = "查询所有任务", httpMethod = "GET")
	public ResponseData<Map<String, Object>> findAll(@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "size", required = true) Integer size) throws JMException {
		Map<String, Object> pointTaskVos = pointTaskService.findAll(page, size);
		return new ResponseData<>(pointTaskVos);
	}

	// 新增任务
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ApiOperation(value = "新增任务", notes = "新增任务", httpMethod = "POST")
	public ResponseData<Object> insertTask(@RequestBody PointTaskVo pointTaskVo) throws JMException {
		if(pointTaskVo == null) {
			return new ResponseData<>(0,"参数对象不能为空","object can not be null");
		}
		Map<String, Object> map = voCheckNotNull(pointTaskVo);
		boolean flag = Boolean.parseBoolean(String.valueOf(map.get("flag")));
		if(!flag) {
			return new ResponseData<>(0,String.valueOf(map.get("msg")),"param is null");
		}
		boolean task = pointTaskService.saveOrUpdate(pointTaskVo);
		if(!task) {
			return new ResponseData<>(0,"保存失败","save failed");
		}
		return new ResponseData<>(task);
	}

	// 编辑修改任务
	@RequestMapping("/update")
	@ApiOperation(value = "修改任务管理数据", notes = "修改任务管理数据", httpMethod = "POST")
	public ResponseData<Object> updateTask(@RequestBody PointTaskVo pointTaskVo) {
		if(pointTaskVo == null) {
			return new ResponseData<>(0,"参数对象不能为空","object can not be null");
		}
		Map<String, Object> map = voCheckNotNull(pointTaskVo);
		boolean flag = Boolean.parseBoolean(String.valueOf(map.get("flag")));
		if(!flag) {
			return new ResponseData<>(0,String.valueOf(map.get("msg")),"param is null");
		}
		boolean task = pointTaskService.saveOrUpdate(pointTaskVo);
		if(!task) {
			return new ResponseData<>(0,"保存失败","save failed");
		}
		return new ResponseData<>(task);
	}

	// 删除积分任务
	@RequestMapping("/del")
	@ApiOperation(value = "删除任务", notes = "删除任务", httpMethod = "GET")
	public ResponseData<Object> deleteTask(@RequestParam(value = "id", required = true) Long id) throws JMException {
		boolean isExit = pointTaskService.isExit(id);
		if(isExit) {
			return new ResponseData<>(0,"该任务已在计划列表","this task already in plan");
		}
		boolean task = pointTaskService.deleteById(id);
		if(!task) {
			return new ResponseData<>(0,"删除失败","delete failed");
		}
		return new ResponseData<>(task);
	}
	
	@RequestMapping(value = "/queryValidTask",method = RequestMethod.GET)
	public ResponseData<List<PointTaskVo>> queryValidTask() throws JMException{
		List<PointTaskVo> list = pointTaskService.queryValidTask();
		return new ResponseData<>(list);
	}
	

	@RequestMapping(value = "/upload")
	@ApiOperation(value = "图片上传操作", notes = "图片上传操作", httpMethod = "POST")
	public ResponseData<String> upload(@RequestPart("file") MultipartFile file) throws JMException, IOException {
		if(file == null) {
			return new ResponseData<>(0, "文件不能为空", "file is not allow null");
		}
		if(file.getSize() >= 20971520l) {
			return new ResponseData<>(0, "文件大小不能超过20M", "file is too big");
		}
		//判断是否是一个图片格式的文件
		ImageInputStream iis = ImageIO.createImageInputStream(file.getInputStream());
		Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
		if (!iter.hasNext()) {// 文件不是图片
			return new ResponseData<>(0, "这不是一个图片文件", "this isn't a img");
		} else {
			String url = fileService.upload(file);
			JSONObject json = JSONObject.parseObject(url);
			return new ResponseData<>(String.valueOf(json.get("data")));
		}
	}

	@RequestMapping("/queryTaskStatus/{id}")
	@ApiOperation(value = "查看当前任务是否已在生效的计划中", notes = "查看当前任务是否已在生效的计划中", httpMethod = "GET")
	public ResponseData<String> queryTaskStatus(@PathVariable(value = "id",required = true)Long id) throws JMException {
		boolean flag = pointTaskService.queryTaskStatus(id);
		return success(flag);
	}
	
	
	
	// **********************************APP端************************************************//

	@RequestMapping(value = "/app/v1/task/list", method = RequestMethod.GET)
	@ApiOperation(value = "app端用户获取任务列表", notes = "app端用户获取任务列表", httpMethod = "GET")
	public ResponseData<Map<String, Object>> appTaskList(HttpServletRequest request) throws JMException {
		Map<String, Object> map = new HashMap<>();
		try {
			Long ssoid = getAppUserInfo(request).getAccountId();
			// Long ssoid = 8783883838389l;
			map = pointTaskService.queryAppTaskList(ssoid);
		} catch (Exception e) {
			log.debug("查询失败：" + e.getMessage());
			return new ResponseData<>(0, "查询失败", "query failed");
		}
		return new ResponseData<>(map);
	}
	

	@RequestMapping("/app/v1/task/detail/{id}")
	@ApiOperation(value = "app端查看任务详情", notes = "app端查看任务详情", httpMethod = "GET")
	public ResponseData<PointTaskDetailVo> findDetail(@PathVariable Long id) throws JMException {
		PointTaskDetailVo detail = pointTaskService.findDetail(id);
		return new ResponseData<>(detail);
	}

	@RequestMapping(value = "/app/v1/task/saveCurrentNum", method = RequestMethod.POST)
	@ApiOperation(value = "app端用户保存任务完成次数", notes = "app端用户保存任务完成次数", httpMethod = "GET")
	public ResponseData<Object> saveCurrentNum(HttpServletRequest request,
			@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "planId", required = true) Long planId) throws JMException {
		try {
			Long ssoid = getAppUserInfo(request).getAccountId();
			// Long ssoid = 8783883838389l;
			Map<String, Object> map = pointTaskService.saveCurrentNum(ssoid, id, planId);
			String status = String.valueOf(map.get("status"));
			Integer insertCount = Integer.valueOf(String.valueOf(map.get("count")));
			if (status.equals("2")) {
				return new ResponseData<>(0, "任务已失效", "save failed");
			} else if (status.equals("3")) {
				return new ResponseData<>(0, "任务次数已达上限", "save failed");
			} else if (insertCount == 0) {
				return new ResponseData<>(0, "保存已完成次数失败", "save failed");
			}
		} catch (Exception e) {
			log.debug("查询失败：" + e.getMessage());
			return new ResponseData<>(0, "保存完成次数失败", "save failed");
		}
		return new ResponseData<>();

	}

	private Map<String, Object> voCheckNotNull(PointTaskVo vo){
		Map<String, Object> map = new HashMap<>();
		map.put("flag", false);
		String msg = "";
		if(StringUtils.isEmpty(vo.getContent())){
			msg = "任务介绍不能为空";
			map.put("msg", msg);
			return map;
		}else if(vo.getMaxNum() == null){
			msg = "完成次数上限不能为空";
			map.put("msg", msg);
			return map;
		}else if(vo.getMaxNum() > 9999){
			msg = "完成次数上限不能超过9999次";
			map.put("msg", msg);
			return map;
		}else if(vo.getAmount() == null){
			msg = "奖励积分不能为空";
			map.put("msg", msg);
			return map;
		}else if(vo.getAmount() > 9999){
			msg = "奖励积分上限不能超过9999分";
			map.put("msg", msg);
			return map;
		}
		else if(StringUtils.isEmpty(vo.getTitle())){
			msg = "任务标题不能为空";
			map.put("msg", msg);
			return map;
		}else if(vo.getType() == null){
			msg = "任务类型不能为空";
			map.put("msg", msg);
			return map;
		}else if(StringUtils.isEmpty(vo.getUrl())) {
			msg = "任务图片不能为空";
			map.put("msg", msg);
			return map;
		}
		map.put("flag", true);
		return map;
	} 

}
