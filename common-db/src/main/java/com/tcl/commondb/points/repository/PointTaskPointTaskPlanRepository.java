package com.tcl.commondb.points.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tcl.commondb.points.model.PointTaskPointTaskPlan;

public interface PointTaskPointTaskPlanRepository
		extends JpaRepository<PointTaskPointTaskPlan, Long>, JpaSpecificationExecutor<PointTaskPointTaskPlan> {

	// 通过PointTaskPlanId 查询积分任务id
	Long findIdByPointTaskId(Long id);

	List<PointTaskPointTaskPlan> findAllByPointTaskPlanId(Long id);

	// 统计计划任务数量通过 PointTaskid 数量
	@Query(value = "select count (pointTaskId) from PointTaskPointTaskPlan where pointTaskPlanId=?1")
	int countPointTaskByPointTaskPlanId(Long pointTaskPlanId);

	@Query(value = "select a.pointTaskId from PointTaskPointTaskPlan a where a.pointTaskPlanId = :id")
	Long[] findByPointTaskPlanId(@Param("id") Long id);

	// 删除计划里面一个任务
	@Modifying
	@Transactional
	@Query(value = "delete from PointTaskPointTaskPlan where pointTaskPlanId = :pointTaskPlanId and pointTaskId = :pointTaskId")
	int deleteByPointTaskIdAndPointTaskPlanId(@Param("pointTaskId") Long pointTaskId,
			@Param("pointTaskPlanId") Long pointTaskPlanId);

	// 删除所有计划中关联了该任务的数据
	@Modifying
	@Transactional
	@Query(value = "delete from PointTaskPointTaskPlan a where  a.pointTaskId = :pointTaskId")
	void deleteByPointTaskId(@Param("pointTaskId") Long pointTaskId);

	// 删除计划和计划多个任务
	@Modifying
	@Transactional
	@Query(value = "delete from PointTaskPointTaskPlan a where a.pointTaskPlanId = :id")
	void deleteByPointTaskPlanId(@Param("id") Long id);

	@Query(value = "select a from PointTaskPointTaskPlan a where a.pointTaskPlanId = :pointTaskPlanId and a.pointTaskId = :pointTaskId")
	PointTaskPointTaskPlan findBypointTaskIdaAndpointTaskPlanId(@Param("pointTaskId") Long pointTaskId,
			@Param("pointTaskPlanId") Long pointTaskPlanId);

	@Query(value = "SELECT a.point_task_id,a.point_task_plan_id,a.id " + 
			"FROM `point_task_point_task_plan` a " + 
			"LEFT OUTER JOIN point_task_plan b ON a.point_task_plan_id = b.id " + 
			"WHERE b.`status` != 3 and a.point_task_id = :pointTaskId",nativeQuery = true)
	List<PointTaskPointTaskPlan> findExitList(@Param("pointTaskId")Long pointTaskId);

	@Query(value = "INSERT INTO point_task_point_task_plan(point_task_id,point_task_plan_id) VALUE(:#{#task.pointTaskId},:#{#task.pointTaskPlanId}) "
			+ "ON DUPLICATE KEY UPDATE point_task_plan_id = :#{#task.pointTaskPlanId}", nativeQuery = true)
	void saveLinkEntity(@Param("task") PointTaskPointTaskPlan task);

	@Query(value = "SELECT a.point_task_plan_id AS plan_id, b.* FROM `point_task_point_task_plan` a, point_task b "
			+ "WHERE a.point_task_id = b.id  AND point_task_plan_id in ?1 AND b.is_delete = 1", nativeQuery = true)
	List<Map<String, Object>> queryByIds(Long[] ids);

	

}
