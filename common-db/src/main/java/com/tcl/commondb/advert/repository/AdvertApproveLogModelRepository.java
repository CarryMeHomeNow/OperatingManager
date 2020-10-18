package com.tcl.commondb.advert.repository;

import com.tcl.commondb.advert.model.AdvertApproveLogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AdvertApproveLogModelRepository extends JpaRepository<AdvertApproveLogModel, Long>, JpaSpecificationExecutor<AdvertApproveLogModel> {

    Optional<AdvertApproveLogModel> findById(Long id);

    List<AdvertApproveLogModel> findAllByApplyTypeAndApplyIdOrderByCreateTime(String applyType,Integer applyId);

}
