package com.tcl.uf.points.service;
import java.util.Map;

import com.tcl.uf.points.vo.PointTaskPlanListVo;
import com.tcl.uf.points.vo.PointTaskPlanNewVo;


public interface PointTaskPlanService {


    Map<String, Object> findByType(Integer type, Integer page, Integer size);

    PointTaskPlanListVo findById(Long id);

    boolean  deletePlan( Long id);

    boolean  deleteTask( Long pointTaskId,Long pointTaskPlanId);

    boolean insertTaskPlan(PointTaskPlanNewVo pointTaskPlanNewVo);

    boolean updateTaskPlan(PointTaskPlanNewVo pointTaskPlanNewVo);

	boolean findTimeConflictList(Long startTime, Long endTime,Integer type,Long id);

	boolean taskIdNotExit(Long[] taskId);

	boolean planStatusIsEffect(Long pointTaskPlanId);

	boolean queryPlanIsExit(Long id);

	boolean isInvaild(Long id);




}
