package com.tcl.uf.activity.service;

import com.tcl.uf.activity.dto.LotterResultSearchParamsDto;
import com.tcl.uf.activity.dto.LotterSearchParams;
import com.tcl.uf.activity.dto.LotteryBaseConfigurationDto;
import com.tcl.uf.activity.vo.*;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LotteryActivityService {

    /**
     * 获取抽奖配置列表信息(分页)
     * @param paramBody
     * @param pageable
     * @param uid
     * @return
     */
    ListWithPage<LotteryBaseConfigurationVo> advancedSearchWithCondition(LotterSearchParams paramBody, Pageable pageable, String userId);

    /**
     * 根据配置编码查询明细
     * @param cNo
     * @return OpenGroupVo
     */
    LotteryBaseConfigurationVo findLotteryBaseConfigurationDetail(String cNo);

    /**
     * 保存抽奖配置
     * @param paramBody
     * @param uid
     * @return void
     */
    void saveLotteryBaseConfiguration(LotteryBaseConfigurationDto paramBody, String username);

    /**
     * 修改抽奖配置
     * @param paramBody
     * @param uid
     * @return void
     */
    void updateLotteryBaseConfiguration(LotteryBaseConfigurationDto paramBody, String username);

    /**
     * 查询抽奖配置信息
     * @param type
     * @param openId
     * @return LotteryBaseConfigurationH5Vo
     */
    LotteryBaseConfigurationH5Vo findLotteryBaseConfigurationH5Detail(String type, String openId);

    /**
     * 启动抽奖程序
     * @param configurationNum
     * @param openId
     * @return LotteryBaseConfigurationH5Vo
     */
    LotteryPrizeResultVo startLottery(String configurationNum, String openId, String memUid);

    /**
     * 中奖信息播报
     * @return LotteryBaseConfigurationH5Vo
     */
    List<LotterNewsBroadcastVo> newsBroadcastList(String configurationNum);

    /**
     * 获取抽奖结果信息[历史]
     * @param paramBody
     * @param pageable
     * @param uid
     * @return
     */
    ListWithPage<LotteryRecordVo> advancedSearchLotteryResultWithCondition(LotterResultSearchParamsDto paramBody, Pageable pageable, String uid);

    /**
     * 添加默认抽奖机会
     * @param openId
     * @return
     */
    void addDefaultLotteryChance(String openId, String memUid, String type,String configurationNum);

    /**
     * 删除抽奖配置
     * @param configurationNum
     * @return
     */
    void deleteLotteryConfigure(String configurationNum);

    /**
     * 查询抽奖类型配置
     * @return
     */
    List<LotteryConfigureTypeVo> configureType();

    /**
     * 启动抽奖程序
     * @param configurationNum
     * @param openId
     * @return LotteryBaseConfigurationH5Vo
     */
    int countLotteryChance(String configurationNum, String openId);

    /**
     * 添加抽奖机会
     * @param openId
     * @param type
     * @return
     */
    void addQuestionnaireLotteryChance(String openId, String type);

    int addBigScreenLotteryChance(String openId);

    int remainBigScreenLotteryChance(String openId);

    void updateActiveStatus(String type, String businessTime, String source);

    /**
     * 添加抽奖机会
     * @return
     */
    boolean checkType();

    void bindAddress(Integer lotteryRecordId, Long addrId);

    Page<LotteryRecordVo> listLotteryRecordByBusinessTime(String configurationNum, String businessTime, Integer limit);

    Page<LotteryRecordVo> listLotteryRecordByOpenId(String configurationNum, String openId, Integer limit);
}
