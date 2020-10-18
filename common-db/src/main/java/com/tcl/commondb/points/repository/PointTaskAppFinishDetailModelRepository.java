package com.tcl.commondb.points.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tcl.commondb.points.model.PointTaskAppFinishDetailModel;

public interface PointTaskAppFinishDetailModelRepository extends JpaRepository<PointTaskAppFinishDetailModel, Integer>,
		JpaSpecificationExecutor<PointTaskAppFinishDetailModel> {

	@Query(nativeQuery = true, value = "SELECT task.*, app.current_num, app.finish_time FROM (" 
			+" SELECT b.id, c.id AS plan_id, b.title, b.url, b.app_path, b.max_num, b.reward AS amount," 
			+" c.type, c.start_time  FROM `point_task_point_task_plan` a, point_task b," 
			+" point_task_plan c WHERE a.point_task_id = b.id  AND a.point_task_plan_id = c.id and c.`status` = 2" 
			+" order by b.type) task LEFT OUTER JOIN ( SELECT current_num, ssoid, task_id, finish_time FROM point_task_app_finish_detail "
			+" WHERE ssoid = :ssoid and finish_time IN ( SELECT max( finish_time ) FROM point_task_app_finish_detail GROUP BY task_id  ) ) app "
			+" ON app.task_id = task.id ")
	List<Map<String, Object>> findAppTaskListBySsoid(@Param("ssoid") Long ssoid);

	
	PointTaskAppFinishDetailModel findFirstBySsoidAndTaskIdOrderByFinishTimeDesc(Long ssoid, Long taskId);


	@Query(value = "select task_id from point_task_app_finish_detail where ssoid = :ssoid group by task_id",
			nativeQuery = true)
	List<Long> findBySsoidGroupByTaskId(@Param("ssoid") Long ssoid);

	@Query(nativeQuery = true, value = "SELECT task.*, app.current_num, app.finish_time FROM (" 
			+" SELECT b.id, c.id AS plan_id, b.title, b.url, b.app_path, b.max_num, b.reward AS amount," 
			+" c.type, c.start_time  FROM `point_task_point_task_plan` a, point_task b," 
			+" point_task_plan c WHERE a.point_task_id = b.id  AND a.point_task_plan_id = c.id and c.`status` = 2 and b.id = :taskId " 
			+" order by b.type) task LEFT OUTER JOIN ( SELECT current_num, ssoid, task_id, finish_time FROM point_task_app_finish_detail "
			+" WHERE ssoid = :ssoid and finish_time IN ( SELECT max( finish_time ) FROM point_task_app_finish_detail where task_id = :taskId ) ) app "
			+" ON app.task_id = task.id")
	Map<String, Object> queryAppTaskListByTaskId(@Param("ssoid")Long ssoid,@Param("taskId") Long taskId);

}
