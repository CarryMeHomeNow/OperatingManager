package com.tcl.commondb.activity.repository;

import com.tcl.commondb.activity.model.LotteryNewsBroadcastModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LotteryNewsBroadcastModelRepository extends JpaRepository<LotteryNewsBroadcastModel, Integer>, JpaSpecificationExecutor<LotteryNewsBroadcastModel> {

    List<LotteryNewsBroadcastModel> findByConfigurationNum(String ConfigurationNum);

    @Transactional
    void deleteByConfigurationNum(String ConfigurationNum);
}
