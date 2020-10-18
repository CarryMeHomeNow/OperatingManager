package com.tcl.commondb.activity.repository;


import com.tcl.commondb.activity.model.LotteryPrizeConfigurationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LotteryPrizeConfigurationModelRepository extends JpaRepository<LotteryPrizeConfigurationModel, Integer>, JpaSpecificationExecutor<LotteryPrizeConfigurationModel> {

    List<LotteryPrizeConfigurationModel> findByConfigurationNum(String configurationNum);

    List<LotteryPrizeConfigurationModel> findByConfigurationNumAndPrizeRemainderNumberGreaterThan(String configurationNum, int limit);

    List<LotteryPrizeConfigurationModel> findByconfigurationNumAndUseStatus(String configurationNum, Integer useStatus);

    LotteryPrizeConfigurationModel findByConfigurationNumAndPrizeNumberAndUseStatus(String configurationNum, String prizeNumber, Integer useStatus);
    //根据奖品编码查询奖品名称，maym
    LotteryPrizeConfigurationModel findByPrizeNumber(String prizenum);
}
