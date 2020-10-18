package com.tcl.commondb.activity.repository;

import com.tcl.commondb.activity.model.LotteryPrizeDailyStockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface LotteryPrizeDailyStockModelRepository extends JpaRepository<LotteryPrizeDailyStockModel, Integer>, JpaSpecificationExecutor<LotteryPrizeDailyStockModel> {
	LotteryPrizeDailyStockModel findFirstByConfigurationNumAndPrizeNumberAndDay(String configurationNum, String prizeNumber, String day);
}
