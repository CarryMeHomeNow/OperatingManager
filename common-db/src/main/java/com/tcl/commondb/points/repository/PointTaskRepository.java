package com.tcl.commondb.points.repository;

import com.tcl.commondb.points.model.PointTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PointTaskRepository extends JpaRepository<PointTask, Long>, JpaSpecificationExecutor<PointTask> {
//    @Query(value = "select p from PointTask p where p.isDelete = 1")
	PointTask findByIdEquals(Long id);

	@Modifying
	@Transactional
	@Query("update PointTask p set p.isDelete = 2  where p.id = :id ")
	Integer updateIsDelete(@Param("id") Long id);

	@Query(value = "select p from PointTask p where p.isDelete = 1")
	Page<PointTask> findAll(Pageable pageable);

	// 根据任务类型查询 是否排除已经软删除的数据,1是正常，2是删除
	@Query(value = "select p from PointTask p where p.type =:type and p.isDelete = 1")
	Page<PointTask> findByType(@Param("type") Integer type, Pageable pageable);

	@Query(value = "select task from PointTask task where task.id in ?1")
	List<PointTask> findByIds(Long[] ids);

	@Transactional
	@Modifying
	@Query(value = "update PointTask p set p.title = :#{#task.title},p.appPath = :#{#task.appPath},"
			+ "p.content = :#{#task.content},p.h5Path = :#{#task.h5Path},"
			+ "p.maxNum = :#{#task.maxNum},p.reward = :#{#task.reward},p.type = :#{#task.type},"
			+ "p.updateTime = :#{#task.updateTime},p.url = :#{#task.url},p.xcxAppId = :#{#task.xcxAppId},"
			+ "p.xcxGhId = :#{#task.xcxGhId},p.xcxPath = :#{#task.xcxPath} where id = :#{#task.id}")
	Integer updateTask(@Param("task") PointTask pointTask);

	
	@Query(value = "SELECT b.*  FROM `point_task_point_task_plan` a" + 
			" LEFT JOIN point_task b ON a.point_task_id = b.id " + 
			" WHERE  b.is_delete =1 and a.point_task_plan_id IN (" + 
			" SELECT id  FROM point_task_plan  WHERE  is_delete =1 and `status` = 2  )",nativeQuery = true)
	List<PointTask> queryValidTask();

	@Query(value = "SELECT b.id  FROM `point_task_point_task_plan` a" + 
			" LEFT JOIN point_task b ON a.point_task_id = b.id " + 
			" WHERE b.is_delete =1 and a.point_task_plan_id IN (" + 
			" SELECT id  FROM point_task_plan where is_delete =1 and type = :type and  " +
			"`status` = 2) and type_code = :typeCode limit 1",nativeQuery = true)
	Long findByValidTaskByTypeCode(@Param("typeCode") int typeCode, @Param("type")int type);

	List<PointTask> findByTypeAndIsDelete(int type, int isDelete);

	
	@Query(value = "SELECT b.* FROM `point_task_point_task_plan` a LEFT JOIN point_task b ON a.point_task_id = b.id " 
			+ "WHERE b.is_delete = 1  AND a.point_task_plan_id IN ( SELECT id FROM point_task_plan " 
			+ "WHERE is_delete = 1 AND `status` = 2 AND type = 2 ) AND b.id = :id",nativeQuery = true)
	PointTask queryTaskStatus(Long id);

}
