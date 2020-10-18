package com.tcl.commondb.activity.repository;


import com.tcl.commondb.activity.model.LotteryPrizeChanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LotteryPrizeChanceModelRepository extends JpaRepository<LotteryPrizeChanceModel, Integer>, JpaSpecificationExecutor<LotteryPrizeChanceModel> {

    LotteryPrizeChanceModel getFirstByOpenIdAndConfigurationNumAndUseStatusAndActiveState(String openId, String configurationNum, String userStatus, String activeState);
    
    LotteryPrizeChanceModel getFirstByOpenIdAndConfigurationNumAndUseStatusAndActiveStateAndBusinessTimeAndSourceNotInOrderByCreateTimeAsc(String openId, String configurationNum, String userStatus, String activeState, String businessTime, String[] sources);

    int countByOpenIdAndUseStatusAndActiveState(String openId, String useStatus, String status);
    
    int countByConfigurationNumAndOpenIdAndUseStatusAndActiveStateAndBusinessTime(String configurationNum, String openId, String useStatus, String status, String businessTime);
    
    int countByConfigurationNumAndOpenIdAndUseStatusAndActiveStateAndBusinessTimeAndSource(String configurationNum, String openId, String useStatus, String status, String businessTime, String source);

    int countByConfigurationNumAndOpenIdAndUseStatusAndActiveStateAndBusinessTimeAndSourceNot(String configurationNum, String openId, String useStatus, String status, String businessTime, String source);

    List<LotteryPrizeChanceModel> findByOpenIdAndConfigurationNumAndBusinessTimeAndSource(String openId, String configurationNum, String businessTime, String source);

    List<LotteryPrizeChanceModel> findByActiveStateAndUseStatusAndSourceAndBusinessTime(String activeState, String useStatus, String source, String businessTime);

    int countByOpenIdAndUseStatus(String openId, String useStatus);
    
    int countByConfigurationNumAndOpenIdAndUseStatus(String configurationNum, String openId, String useStatus);

    int countByOpenIdAndConfigurationNumAndUseStatusAndActiveStateAndBusinessTime(String openId, String configurationNum, String useStatus, String status, String businessTime);

    LotteryPrizeChanceModel getFirstByOpenIdAndConfigurationNumAndUseStatusAndActiveStateAndBusinessTimeOrderByCreateTimeAsc(String openId, String configurationNum, String userStatus, String activeState, String businessTime);
    
    
    int countByConfigurationNumAndOpenIdAndBusinessTimeAndSource(String configurationNum, String openId, String businessTime, String source);
    
    int countByConfigurationNumAndOpenIdAndBusinessTime(String configurationNum, String openId, String businessTime);
    
    @Modifying
    @Transactional
    @Query(value = "update men_lottery_prize_chance_record set active_state = 0 where configuration_num = ?1 and business_time <= ?2 and source = ?3", nativeQuery = true)
    void updateActiveStatus(String configurationNum, String businessTime, String source);
}
