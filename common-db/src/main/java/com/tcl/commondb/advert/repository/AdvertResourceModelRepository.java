package com.tcl.commondb.advert.repository;

import com.tcl.commondb.advert.model.AdvertResourceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdvertResourceModelRepository extends JpaRepository<AdvertResourceModel, Long>, JpaSpecificationExecutor<AdvertResourceModel> {

    AdvertResourceModel findByIdEquals(Long id);

    AdvertResourceModel findByIsDeletedAndId(Integer isDelete, Long id);

    AdvertResourceModel findFirstByIsDeletedAndStatusAndAuditStatusAndLocIdAndEffectiveDayOrderByCreateTimeDesc(Integer isDelete, Integer status, Integer auditStatus, Long locId, Integer effectiveDay);

    AdvertResourceModel findFirstByIsDeletedAndLocIdAndEffectiveDayOrderByCreateTimeDesc(Integer isDelete, Long locId, Integer effectiveDay);

    AdvertResourceModel findFirstByLocIdAndTestFlagOrderByCreateTimeDesc(Long locId, Integer isTest);

    Page<AdvertResourceModel> findAllByIsDeletedAndStatusOrderById(Integer isDelete, Integer status, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE AdvertResourceModel t SET t.testFlag=0 WHERE t.locId = :locId AND t.effectiveDay = :effectiveDay ")
    void clearTestFlag(@Param("locId") Long locId, @Param("effectiveDay") Integer effectiveDay);

    @Query(value = "SELECT max(seq_no) FROM advert_location_resource WHERE loc_id=:locId AND effective_day=:effectiveDay ", nativeQuery = true)
    int findMaxSeqNoByLocIdAndEffectiveDay(@Param("locId") Long locId, @Param("effectiveDay") Integer effectiveDay);

    @Modifying
    @Query(value = "update AdvertResourceModel t set t.seqNo = :seqNo where t.id = :resourceId")
    int setSeqNo(@Param("resourceId") Long resourceId, @Param("seqNo") Integer seqNo);

    @Modifying
    @Transactional
    @Query(value = "update AdvertResourceModel t set t.isDeleted=1 where t.id in (:ids)")
    int deleteByIds(@Param("ids") List<Long> ids);

    @Modifying
    @Transactional
    @Query(value = "update AdvertResourceModel t set t.status=:status where t.id =:id")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE AdvertResourceModel t SET t.status=:status WHERE t.locId=:locId AND t.effectiveDay=:effectiveDay AND t.auditStatus=:auditStatus")
    int batchUpdateStatus(@Param("locId") Long locId, @Param("effectiveDay") Integer effectiveDay, @Param("auditStatus") Integer auditStatus, @Param("status") Integer status);

    @Modifying
    @Transactional
    @Query(value = "update AdvertResourceModel t set t.auditStatus=:auditStatus where t.id =:id")
    int updateAuditStatus(@Param("id") Long id, @Param("auditStatus") Integer auditStatus);
}
