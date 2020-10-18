package com.tcl.commondb.advert.repository;

import com.tcl.commondb.advert.model.AdvertLocationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface AdvertLocationModelRepository extends JpaRepository<AdvertLocationModel, Long>, JpaSpecificationExecutor<AdvertLocationModel> {

    AdvertLocationModel findByIdEquals(Long id);

    AdvertLocationModel findByIsDeletedAndId(Integer isDelete, Long id);

    AdvertLocationModel findByIsDeletedAndGidAndFrameId(Integer isDelete, Long id, Integer frameId);

    Page<AdvertLocationModel> findAllByIsDeletedAndStatusOrderById(Integer isDelete, Integer status, Pageable pageable);

    List<AdvertLocationModel> findAllByIsDeletedAndStatusAndGidOrderByFrameIdAsc(Integer isDelete, Integer status, Long gid);

    List<AdvertLocationModel> findAllByIdIn(Set<Long> ids);

    @Modifying
    @Transactional
    @Query(value = "update AdvertLocationModel t set t.isDeleted=1 where t.id in (:ids)")
    int deleteByIds(@Param("ids") List<Long> ids);

    @Modifying
    @Transactional
    @Query(value = "update AdvertLocationModel t set t.status=:status where t.id =:id")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}
