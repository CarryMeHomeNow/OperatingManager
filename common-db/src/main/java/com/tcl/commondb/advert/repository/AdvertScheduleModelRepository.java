package com.tcl.commondb.advert.repository;

import com.tcl.commondb.advert.model.AdvertScheduleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface AdvertScheduleModelRepository extends JpaRepository<AdvertScheduleModel, Long>, JpaSpecificationExecutor<AdvertScheduleModel> {

    AdvertScheduleModel findByIdEquals(Long id);

    AdvertScheduleModel findFirstByIsDeletedAndLocIdAndEffectiveDay(Integer isDeleted, Long locId, Integer effectiveDay);

    AdvertScheduleModel findFirstByIsDeletedAndLocIdAndEffectiveDayAndDepartmentId(Integer isDeleted, Long locId, Integer effectiveDay, Long departmentId);

    Page<AdvertScheduleModel> findAllByIsDeletedOrderById(Integer isDelete, Pageable pageable);

    @Query(value = "SELECT DISTINCT o.effectiveDate FROM AdvertScheduleModel o WHERE o.isDeleted=:isDeleted AND o.groupId=:groupId AND o.frameId=:frameId AND o.departmentId=:departmentId AND o.effectiveDay >= :today ORDER BY effectiveDate ASC")
    List<Timestamp> findEffectiveDateByDepartmentIdAndGroupIdAndFrameId(@Param("isDeleted") Integer isDeleted, @Param("groupId")  Long groupId, @Param("frameId") Integer frameId, @Param("departmentId") Long departmentId , @Param("today") Integer today);

    @Query(value = "SELECT DISTINCT o.effectiveDate FROM AdvertScheduleModel o WHERE o.isDeleted=:isDeleted AND o.groupId=:groupId AND o.frameId=:frameId AND o.effectiveDay >= :today ORDER BY effectiveDate ASC")
    List<Timestamp> findEffectiveDateByGroupIdAndFrameId(@Param("isDeleted") Integer isDeleted, @Param("groupId")  Long groupId, @Param("frameId") Integer frameId, @Param("today") Integer today);

}
