package com.tcl.commondb.points.repository;

import com.tcl.commondb.points.model.PointTaskPlan;
import com.tcl.commondb.points.model.PointTaskPointTaskPlan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PointTaskPlanRepository
		extends JpaRepository<PointTaskPlan, Long>, JpaSpecificationExecutor<PointTaskPlan> {
	PointTaskPlan findByIdEquals(Long id);

	@Query(value = "select p from PointTaskPlan p where p.type =:type and p.isDelete = 1")
	Page<PointTaskPlan> findAllByType(@Param("type") Integer type, Pageable pageable);

	@Query(nativeQuery = true, value = "select max(end_time) from point_task_plan")
	Date getMaxTime();

	@Modifying
	@Transactional
	@Query("update PointTaskPlan p set p.isDelete = 2  where p.id = :id ")
	void updateIsDelete(@Param("id") Long id);

	@Modifying
	@Transactional
	@Query(value = "update PointTaskPlan t set t.status = 2 where t.startTime <= :time and t.endTime >= :time and t.type = 2 and t.isDelete = 1 ")
	void updateStatusValid(Long time);

	@Modifying
	@Transactional
	@Query(value = "update PointTaskPlan t set t.status = 1 where t.startTime > :time and t.type = 2 and t.isDelete = 1 ")
	void updateStatusInvalid(Long time);
	
	@Modifying
	@Transactional
	@Query(value = "update PointTaskPlan t set t.status = 3 where t.endTime < :time and t.type = 2 and t.isDelete = 1  ")
	void updateStatusFailure(Long time);

	/*@Query(value = "SELECT c.end_time FROM `point_task_point_task_plan` a, point_task b, point_task_plan c "
			+ " WHERE a.point_task_id = b.id  AND a.point_task_plan_id = c.id and b.id = :taskId and c.start_time= :startTime and `status` = 2 limit 1", nativeQuery = true)*/
	@Query(value = "SELECT end_time FROM point_task_plan WHERE  id = :planTd  and `status` = 2", nativeQuery = true)
	Long queryVaildTimeByTaskId(@Param("planTd") Long planTd);

	@Query(value = "select p from PointTaskPlan p where ((:startTime <= p.startTime and p.startTime < :endTime) or "
			+ "(:startTime < p.endTime and p.endTime<= :endTime) or (p.startTime < :startTime and :endTime < p.endTime)) "
			+ "and p.type = :type and p.isDelete = 1")
	List<PointTaskPlan> findOneTimeConflict(@Param("startTime") Long startTime, @Param("endTime") Long endTime,
			@Param("type") Integer type);

	@Modifying
	@Transactional
	@Query(value = "update PointTaskPlan t set t.updateTime = :time,t.title = :title,t.startTime = :startTime,t.endTime = :endTime where t.id = :id")
	void updateTaskPlan(@Param("time") Long time, @Param("id") Long id,@Param("title")String title,@Param("startTime") Long startTime,@Param("endTime") Long endTime);

	@Query(value = "select p from PointTaskPlan p where ((:startTime <= p.startTime and p.startTime < :endTime) or "
			+ "(:startTime < p.endTime and p.endTime<= :endTime) or (p.startTime < :startTime and :endTime < p.endTime)) "
			+ "and p.type = :type and p.isDelete = 1 and id != :id")
	List<PointTaskPlan> findOneTimeConflictForUpdate(Long startTime, Long endTime, Integer type, Long id);

	PointTaskPlan findFirstByTypeAndIsDelete(int type, int isDelete);

}
