package com.tcl.commondb.version.repository;

import com.tcl.commondb.version.model.InternalVersionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InternalVersionRepository  extends JpaRepository<InternalVersionModel, Long>, JpaSpecificationExecutor<InternalVersionModel> {

    @Modifying
    @Query(value = "update InternalVersionModel p set p.useStatus=0 where p.id in (:ids)")
    int deleteInternalVersion(@Param("ids") List<Long> ids);

    InternalVersionModel findByIdEquals(Long id);

    List<InternalVersionModel> findByUseStatus(Integer UseStatus);

}
