package com.tcl.commondb.activity.repository;

import com.tcl.commondb.activity.model.LotteryWinSettingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LotteryWinSettingModelRepository extends JpaRepository<LotteryWinSettingModel, Integer>, JpaSpecificationExecutor<LotteryWinSettingModel> {

    List<LotteryWinSettingModel> findByConfigurationNum(String configurationNum);

    @Transactional
    void deleteByConfigurationNum(String configurationNum);

    List<LotteryWinSettingModel> findByConfigurationNumOrderBySerialNumber(String configurationNum);
}
