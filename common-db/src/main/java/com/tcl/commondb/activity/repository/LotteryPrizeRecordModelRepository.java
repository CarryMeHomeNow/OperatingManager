package com.tcl.commondb.activity.repository;

import com.tcl.commondb.activity.model.LotteryPrizeRecordModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface LotteryPrizeRecordModelRepository extends JpaRepository<LotteryPrizeRecordModel, Integer>, JpaSpecificationExecutor<LotteryPrizeRecordModel> {

    int countByConfigurationNumAndOpenIdAndBusinessTimeAndSourceNot(String configurationNum, String openId, String businessTime, String source);
    
    int countByConfigurationNumAndOpenIdAndBusinessTimeAndSource(String configurationNum, String openId, String businessTime, String source);

    int countByConfigurationNumAndOpenIdAndBusinessTime(String configurationNum, String openId, String businessTime);
    
    int countByConfigurationNumAndOpenId(String configurationNum, String openId);

    @Query(value = "SELECT nick_name,prize_name,prize_number,open_id FROM men_lottery_prize_result_record  WHERE configuration_num=:configurationNum order by create_time DESC LIMIT 100", nativeQuery = true)
    List<Object[]> getLotteryPrizeRecord(@Param("configurationNum") String configurationNum);
    
    // 统计指定时间抽奖次数
    int countByConfigurationNumAndBusinessTime(String configurationNum, String businessTime);
    // 统计活动开始至今抽奖次数
    int countByConfigurationNum(String configurationNum);
    // 统计指定时间抽奖人数
    @Query(value = "SELECT count(distinct open_id) FROM men_lottery_prize_result_record WHERE configuration_num=:configurationNum and business_time=:businessTime and create_time<:createTime", nativeQuery = true)
    int countByConfigurationNumAndBusinessTimeAndCreateTimeLessThanDistOpenid(@Param("configurationNum") String configurationNum, @Param("businessTime") String businessTime, @Param("createTime") Timestamp createTime);
    // 统计活动开始至今抽奖人数
    @Query(value = "SELECT count(distinct open_id) FROM men_lottery_prize_result_record WHERE configuration_num=:configurationNum and create_time<:createTime", nativeQuery = true)
    int countByConfigurationNumAndCreateTimeLessThanDistOpenid(@Param("configurationNum") String configurationNum, @Param("createTime") Timestamp createTime);
    
    // 统计指定时间抽奖人数
    @Query(value = "SELECT count(distinct open_id) FROM men_lottery_prize_result_record WHERE configuration_num=:configurationNum and business_time=:businessTime", nativeQuery = true)
    int countByConfigurationNumAndBusinessTimeDistOpenid(@Param("configurationNum") String configurationNum, @Param("businessTime") String businessTime);
    // 统计活动开始至今抽奖人数
    @Query(value = "SELECT count(distinct open_id) FROM men_lottery_prize_result_record WHERE configuration_num=:configurationNum", nativeQuery = true)
    int countByConfigurationNumDistOpenid(@Param("configurationNum") String configurationNum);
    
    LotteryPrizeRecordModel findFirstByConfigurationNumAndBusinessTimeAndOpenIdOrderByCreateTimeAsc(String configurationNum, String businessTime, String openId);
    LotteryPrizeRecordModel findFirstByConfigurationNumAndOpenIdOrderByCreateTimeAsc(String configurationNum, String openId);
    
    // 统计指定时间奖品中奖次数
    int countByConfigurationNumAndPrizeNumberAndBusinessTime(String configurationNum, String prizeNumber, String businessTime);
    // 统计活动开始至今奖品中奖次数
    int countByConfigurationNumAndPrizeNumber(String configurationNum, String prizeNumber);
    
    int countByConfigurationNumAndPrizeNumberAndBusinessTimeAndWinTypeAndOpenId(String configurationNum, String prizeNumber, String businessTime, Integer winType, String openId);
    int countByConfigurationNumAndPrizeNumberAndWinTypeAndOpenId(String configurationNum, String prizeNumber, Integer winType, String openId);
    
    Page<LotteryPrizeRecordModel> findAllByConfigurationNumAndBusinessTimeOrderByCreateTimeDesc(String configurationNum, String businessTime, Pageable page);
    
    Page<LotteryPrizeRecordModel> findAllByConfigurationNumAndOpenIdOrderByCreateTimeDesc(String configurationNum, String openId, Pageable page);
    
}
