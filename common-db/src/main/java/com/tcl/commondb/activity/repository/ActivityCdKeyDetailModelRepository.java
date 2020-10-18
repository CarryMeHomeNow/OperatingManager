package com.tcl.commondb.activity.repository;


import com.tcl.commondb.activity.model.ActivityCdkeyDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActivityCdKeyDetailModelRepository extends JpaRepository<ActivityCdkeyDetailModel,Long>, JpaSpecificationExecutor<ActivityCdkeyDetailModel> {

    ActivityCdkeyDetailModel findByCode(String code);
}
