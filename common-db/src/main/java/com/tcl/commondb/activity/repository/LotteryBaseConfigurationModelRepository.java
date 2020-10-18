package com.tcl.commondb.activity.repository;


import com.tcl.commondb.activity.model.LotteryBaseConfigurationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotteryBaseConfigurationModelRepository extends JpaRepository<LotteryBaseConfigurationModel, Integer>, JpaSpecificationExecutor<LotteryBaseConfigurationModel> {

    LotteryBaseConfigurationModel findByConfigurationNum(String configurationNum);

    LotteryBaseConfigurationModel getFirstByTypeAndUseStatus(String type, Integer useStatus);

    @Query(value = "SELECT type FROM activity_lottery_base_configuration  WHERE use_status=1 group by type", nativeQuery = true)
    List<Object[]> getLotteryBaseConfiguration();
}
