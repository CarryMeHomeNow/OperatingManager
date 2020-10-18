package com.tcl.commondb.version.repository;

import com.tcl.commondb.version.model.ExternalVersionStatisticsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ExternalVersionStatisticsRepository extends JpaRepository<ExternalVersionStatisticsModel, Long>, JpaSpecificationExecutor<ExternalVersionStatisticsModel> {
}
