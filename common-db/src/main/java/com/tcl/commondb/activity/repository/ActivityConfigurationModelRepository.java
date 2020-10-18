package com.tcl.commondb.activity.repository;

import com.tcl.commondb.activity.model.ActivityConfigurationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActivityConfigurationModelRepository extends JpaRepository<ActivityConfigurationModel, Long>, JpaSpecificationExecutor<ActivityConfigurationModel> {

}
