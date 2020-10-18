package com.tcl.uf.points.service;

import java.util.List;
import java.util.Map;

import com.tcl.uf.points.vo.AppPointTaskVo;
import com.tcl.uf.points.vo.PointTaskDetailVo;
import com.tcl.uf.points.vo.PointTaskVo;

public interface PointTaskService {

	List<PointTaskVo> findByIds(Long[] ids);

	Map<String, Object> findAll(Integer page, Integer size);

	Map<String, Object> findByType(Integer type, Integer page, Integer size);

	//boolean insertPask(PointTaskVo pointTaskVo);

	boolean deleteById(Long id);

	//boolean updatePask(PointTaskVo pointTaskVo);

	PointTaskDetailVo findDetail(Long id);

	Map<String, Object> queryAppTaskList(Long ssoid);

	Map<String, Object> saveCurrentNum(Long ssoid, Long id, Long planId);

	boolean saveOrUpdate(PointTaskVo pointTaskVo);

	boolean isExit(Long id);

	List<PointTaskVo> queryValidTask();

	Long findByValidTaskByTypeCode(int typeCode,int type);

	List<Long> findBySsoidGroupByTaskId(Long accountId);

	AppPointTaskVo queryAppTaskListByTaskId(Long accountId, Long dailyId);

	boolean queryTaskStatus(Long id);
}
