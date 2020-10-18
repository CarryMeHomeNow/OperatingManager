package com.tcl.uf.activity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.activity.model.*;
import com.tcl.commondb.activity.repository.*;
import com.tcl.uf.activity.consts.MenberAvtivityConstants;
import com.tcl.uf.activity.dto.*;
import com.tcl.uf.activity.service.LotteryActivityService;
import com.tcl.uf.activity.vo.*;
import com.tcl.uf.common.base.util.StringsUtil;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Service("lotteryActivityService")
public class LotteryActivityImpl implements LotteryActivityService {

    private static final Log _log = LogFactory.getLog(LotteryActivityImpl.class);

    @Autowired
    private LotteryBaseConfigurationModelRepository lotteryBaseConfiguration;

    @Autowired
    private LotteryPrizeConfigurationModelRepository lotteryPrizeConfiguration;

    @Autowired
    private LotteryWinSettingModelRepository lotteryWinSetting;

    @Autowired
    private LotteryNewsBroadcastModelRepository lotteryNewsBroadcast;

    @Autowired
    private MemMarktingLuckCardlistRepository memMarktingLuckCardlistRepository;

    @Autowired
    private MemMarktingLuckEvedayRepository memMarktingLuckEvedayRepository;

    @Autowired
    private MemMarketingLuckInvoiceRepository memMarketingLuckInvoiceRepository;

    @Autowired
    private PropertiesConfigModelRepository propertiesConfigModelRepository;

    @Autowired
    private LotteryPrizeChanceModelRepository lotteryPrizeChanceModelRepository;

    @Autowired
    private LotteryPrizeRecordModelRepository lotteryPrizeRecordModelRepository;

    @Autowired
    private LotteryPrizeDailyStockModelRepository lotteryPrizeDailyStockModelRepository;

    @Override
    public ListWithPage<LotteryBaseConfigurationVo> advancedSearchWithCondition(LotterSearchParams paramBody, Pageable pageable, String userId) {
        Long sumTotal = null;
        List<LotteryBaseConfigurationVo> records = null;
        //构造初始状态排序条件
        List< Sort.Order> orders=new ArrayList< Sort.Order>();
        orders.add( new Sort.Order(Sort.Direction.DESC, "createTime"));
        Pageable page = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),Sort.by(orders));
//        Pageable page = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),new Sort(orders));
        Page<LotteryBaseConfigurationModel> result = lotteryBaseConfiguration.findAll(new Specification<LotteryBaseConfigurationModel>() {
            @Override
            public Predicate toPredicate(Root<LotteryBaseConfigurationModel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return createBaseConfigurationRecordQueryCondition(root,criteriaQuery,criteriaBuilder,paramBody);
            }
        },page);
        //数据库实体对象转VO对象
        if (result != null){
            sumTotal = result.getTotalElements();
            records = memoryValueToViewObject(result.getContent());
        }else {
            sumTotal = Long.getLong("0") ;
            records = new ArrayList<LotteryBaseConfigurationVo>();
        }
        return PageUtils.formatData(records, pageable,sumTotal);
    }

    @Override
    public LotteryBaseConfigurationVo findLotteryBaseConfigurationDetail(String configurationNum) {
        LotteryBaseConfigurationModel baseConfiguration=lotteryBaseConfiguration.findByConfigurationNum(configurationNum);
        if(baseConfiguration == null){
            return null;
        }
        //查询基础配置
        LotteryBaseConfigurationVo lotteryBaseConfigurationVo=new LotteryBaseConfigurationVo();
        lotteryBaseConfigurationVo.setConfigurationNum(configurationNum);
        lotteryBaseConfigurationVo.setActivityName(baseConfiguration.getActivityName());
        lotteryBaseConfigurationVo.setEffectiveStartTime(baseConfiguration.getEffectiveStartTime());
        lotteryBaseConfigurationVo.setEffectiveEndTime(baseConfiguration.getEffectiveEndTime());
        lotteryBaseConfigurationVo.setEveryDayLimit(baseConfiguration.getEveryDayLimit());
        lotteryBaseConfigurationVo.setEveryDayFree(baseConfiguration.getEveryDayFree());
        lotteryBaseConfigurationVo.setCostGoldNumber(baseConfiguration.getCostGoldNumber());
        lotteryBaseConfigurationVo.setActivityRule(baseConfiguration.getActivityRule());
        lotteryBaseConfigurationVo.setOpenStatus(baseConfiguration.getOpenStatus());
        lotteryBaseConfigurationVo.setType(baseConfiguration.getType());
        // 新增字段20200722
        lotteryBaseConfigurationVo.setEveryDayShareAddLimit(baseConfiguration.getEveryDayShareAddLimit());
        lotteryBaseConfigurationVo.setShareAddFree(baseConfiguration.getShareAddFree());
        lotteryBaseConfigurationVo.setShareAddPrizeCode(baseConfiguration.getShareAddPrizeCode());
        lotteryBaseConfigurationVo.setWinSettingPeriod(baseConfiguration.getWinSettingPeriod());
        lotteryBaseConfigurationVo.setPrizePeriod(baseConfiguration.getPrizePeriod());
        lotteryBaseConfigurationVo.setBroadcastInterval(baseConfiguration.getBroadcastInterval());
        lotteryBaseConfigurationVo.setOutoffStockPrizeCode(baseConfiguration.getOutoffStockPrizeCode());

        //查询有效的奖品配置
        List<LotteryPrizeConfigurationModel> prizeConfigurationList=lotteryPrizeConfiguration.findByconfigurationNumAndUseStatus(configurationNum,MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
        if(prizeConfigurationList != null && !prizeConfigurationList.isEmpty()){
            List<LotteryPrizeConfigurationVo> prizeConfigurationVoList=new ArrayList<LotteryPrizeConfigurationVo>();
            for(LotteryPrizeConfigurationModel lotterPrizeConfiguration:prizeConfigurationList){
                LotteryPrizeConfigurationVo lotteryPrizeConfigurationVo =new LotteryPrizeConfigurationVo();
                lotteryPrizeConfigurationVo.setPrizeNumber(lotterPrizeConfiguration.getPrizeNumber());
                lotteryPrizeConfigurationVo.setPrizeName(lotterPrizeConfiguration.getPrizeName());
                lotteryPrizeConfigurationVo.setPrizeIcon(lotterPrizeConfiguration.getPrizeIcon());
                // 处理奖品权重
                if (3 == lotterPrizeConfiguration.getWinType()) {
                    String prizeProbability  = lotterPrizeConfiguration.getPrizeProbability();
                    if(!StringUtils.isEmpty(prizeProbability)){
                        BigDecimal probability= new BigDecimal(prizeProbability);
                        BigDecimal value=probability.multiply(new BigDecimal("100"));
                        lotteryPrizeConfigurationVo.setPrizeProbability(String.valueOf(value.stripTrailingZeros().toPlainString()));
                    }else{
                        lotteryPrizeConfigurationVo.setPrizeProbability("0");
                    }
                } else {
                    lotteryPrizeConfigurationVo.setPrizeProbability(lotterPrizeConfiguration.getPrizeProbability());
                }
                lotteryPrizeConfigurationVo.setPrizeTotalNumber(lotterPrizeConfiguration.getPrizeTotalNumber());
                lotteryPrizeConfigurationVo.setPrizeType(lotterPrizeConfiguration.getPrizeType());
                lotteryPrizeConfigurationVo.setPrizeLocationIndex(lotterPrizeConfiguration.getPrizeLocationIndex());
                lotteryPrizeConfigurationVo.setConfigurationNum(lotterPrizeConfiguration.getConfigurationNum());
                lotteryPrizeConfigurationVo.setPrizeValue(lotterPrizeConfiguration.getPrizeValue());
                if (baseConfiguration.getPrizePeriod() == 1) { // 存周期，1=永久，2=日
                    lotteryPrizeConfigurationVo.setPrizeRemainderNumber(lotterPrizeConfiguration.getPrizeRemainderNumber());
                } else {
                    LotteryPrizeDailyStockModel dailyStock = lotteryPrizeDailyStockModelRepository.findFirstByConfigurationNumAndPrizeNumberAndDay(configurationNum, lotterPrizeConfiguration.getPrizeNumber(), TimeUtils.getDateStr(new Date()));
                    if (dailyStock != null) {
                        lotteryPrizeConfigurationVo.setPrizeRemainderNumber(dailyStock.getRemainStock());
                    } else {
                        lotteryPrizeConfigurationVo.setPrizeRemainderNumber(lotterPrizeConfiguration.getPrizeTotalNumber());
                    }
                }
                // 新增字段20200722
                lotteryPrizeConfigurationVo.setWinTime(lotterPrizeConfiguration.getWinTime());
                lotteryPrizeConfigurationVo.setWinTimeValue(lotterPrizeConfiguration.getWinTimeValue());
                lotteryPrizeConfigurationVo.setWinType(lotterPrizeConfiguration.getWinType());
                lotteryPrizeConfigurationVo.setInfinityTotal(lotterPrizeConfiguration.getInfinityTotal());
                lotteryPrizeConfigurationVo.setCouponUrl(lotterPrizeConfiguration.getCouponUrl());
                if (MenberAvtivityConstants.LOTTERY_TYPE_5.equals(lotterPrizeConfiguration.getPrizeType())) {
                    lotteryPrizeConfigurationVo.setCouponTotal(memMarktingLuckCardlistRepository.countByPrizeNumber(lotterPrizeConfiguration.getPrizeNumber()));
                    lotteryPrizeConfigurationVo.setCouponUse(memMarktingLuckCardlistRepository.countByPrizeNumberAndStatus(lotterPrizeConfiguration.getPrizeNumber(), 1));
                    lotteryPrizeConfigurationVo.setCouponNotUse(memMarktingLuckCardlistRepository.countByPrizeNumberAndStatus(lotterPrizeConfiguration.getPrizeNumber(), 0));
                    lotteryPrizeConfigurationVo.setCouponExpired(memMarktingLuckCardlistRepository.countByPrizeNumberAndEndTimeLessThan(lotterPrizeConfiguration.getPrizeNumber(), TimeUtils.getLongDateStr()));
                }
                lotteryPrizeConfigurationVo.setNotifyUser(lotterPrizeConfiguration.getNotifyUser());
                lotteryPrizeConfigurationVo.setSmsTpl(lotterPrizeConfiguration.getSmsTpl());
                prizeConfigurationVoList.add(lotteryPrizeConfigurationVo);
            }
            lotteryBaseConfigurationVo.setPrizeConfiguration(prizeConfigurationVoList);
        }

        List<LotteryWinSettingModel>  winSetList= lotteryWinSetting.findByConfigurationNum(configurationNum);
        if (winSetList != null && !winSetList.isEmpty()) {
            List<LotterWinSettingVo> lotteryWinSettingVoList=new ArrayList<LotterWinSettingVo>();
            for(LotteryWinSettingModel lotteryWinSettingModel :winSetList){
                LotterWinSettingVo lotterWinSettingVo=new LotterWinSettingVo();
                lotterWinSettingVo.setPrizeNumber(lotteryWinSettingModel.getPrizeNumber());
                lotterWinSettingVo.setSerialNumber(String.valueOf(lotteryWinSettingModel.getSerialNumber()));
                lotterWinSettingVo.setPrizeName(lotteryWinSettingModel.getPrizeName());
                lotteryWinSettingVoList.add(lotterWinSettingVo);
            }
            lotteryBaseConfigurationVo.setWinSetting(lotteryWinSettingVoList);
        }

        List<LotteryNewsBroadcastModel> lotteryNewsBroadcastList=lotteryNewsBroadcast.findByConfigurationNum(configurationNum);
        if (lotteryNewsBroadcastList != null && !lotteryNewsBroadcastList.isEmpty()) {
            List<LotterNewsBroadcastVo> lotteryWinSettingVoList=new ArrayList<LotterNewsBroadcastVo>();
            for(LotteryNewsBroadcastModel lotteryNewsBroadcast:lotteryNewsBroadcastList){
                LotterNewsBroadcastVo lotteryWinSettingVo=new LotterNewsBroadcastVo();
                lotteryWinSettingVo.setPrizeNumber(lotteryNewsBroadcast.getPrizeNumber());
                lotteryWinSettingVo.setPrizeName(lotteryNewsBroadcast.getPrizeName());
                lotteryWinSettingVo.setNickName(lotteryNewsBroadcast.getNickName());
                lotteryWinSettingVoList.add(lotteryWinSettingVo);
            }
            lotteryBaseConfigurationVo.setNewsBroadcast(lotteryWinSettingVoList);
        }
        return lotteryBaseConfigurationVo;
    }

    @Override
    public void saveLotteryBaseConfiguration(LotteryBaseConfigurationDto paramBody, String username) {
        //保存抽奖配置时读取用户信息
        LotteryBaseConfigurationModel baseConfiguration=new LotteryBaseConfigurationModel();
        synchronized(LotteryActivityImpl.class) {
            // 查询数据最大数据编号
            long count = lotteryBaseConfiguration.count();
            // 格式化最大编号
            String formatNumber = StringsUtil.generateAutocompleteCode(count + 1, 6);
            String configurationNum = TimeUtils.getTimsNumber() + formatNumber;
            // 保存抽奖配置信息
            baseConfiguration.setConfigurationNum(configurationNum);
            baseConfiguration.setActivityName(paramBody.getActivityName());
            baseConfiguration.setEffectiveStartTime(paramBody.getEffectiveStartTime());
            baseConfiguration.setEffectiveEndTime(paramBody.getEffectiveEndTime());
            baseConfiguration.setEveryDayLimit(paramBody.getEveryDayLimit());
            baseConfiguration.setEveryDayFree(paramBody.getEveryDayFree());
            baseConfiguration.setCostGoldNumber(paramBody.getCostGoldNumber());
            baseConfiguration.setActivityRule(paramBody.getActivityRule());
            baseConfiguration.setOpenStatus(paramBody.getOpenStatus());
            baseConfiguration.setCreator(username);
            baseConfiguration.setUseStatus(MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
            baseConfiguration.setType(paramBody.getType());
            // 新增字段20200722
            baseConfiguration.setEveryDayShareAddLimit(paramBody.getEveryDayShareAddLimit());
            baseConfiguration.setShareAddFree(paramBody.getShareAddFree());
            baseConfiguration.setShareAddPrizeCode(paramBody.getShareAddPrizeCode());
            baseConfiguration.setWinSettingPeriod(paramBody.getWinSettingPeriod());
            baseConfiguration.setPrizePeriod(paramBody.getPrizePeriod());
            baseConfiguration.setBroadcastInterval(paramBody.getBroadcastInterval());
            baseConfiguration.setOutoffStockPrizeCode(paramBody.getOutoffStockPrizeCode());

            lotteryBaseConfiguration.saveAndFlush(baseConfiguration);
        }
        String configurationNum = baseConfiguration.getConfigurationNum();
        Map<String,String> prizeName = new HashMap<String,String>();
        List<LotterPrizeConfigurationDto>  prizeConfiguration= paramBody.getPrizeConfiguration();
        String today = TimeUtils.getDateStr(new Date());
        for(LotterPrizeConfigurationDto configurationDto:prizeConfiguration){
            LotteryPrizeConfigurationModel prizeConfigurationEntity=new LotteryPrizeConfigurationModel();
            prizeConfigurationEntity.setPrizeNumber(configurationDto.getPrizeNumber());
            prizeConfigurationEntity.setPrizeName(configurationDto.getPrizeName());
            prizeConfigurationEntity.setPrizeIcon(configurationDto.getPrizeIcon());
            // 处理奖品权重
            if (3 == configurationDto.getWinType()) {
                String prizeProbability  = configurationDto.getPrizeProbability();
                if(!StringUtils.isEmpty(prizeProbability)){
                    BigDecimal probability= new BigDecimal(prizeProbability);
                    BigDecimal value=probability.divide(new BigDecimal("100"));
                    prizeConfigurationEntity.setPrizeProbability(String.valueOf(value));
                }else{
                    prizeConfigurationEntity.setPrizeProbability("0");
                }
            } else {
                prizeConfigurationEntity.setPrizeProbability(configurationDto.getPrizeProbability());
            }
            prizeConfigurationEntity.setPrizeTotalNumber(configurationDto.getPrizeTotalNumber());

            // 设置库存  库存周期，1=永久，2=日
            if (baseConfiguration.getPrizePeriod() == 1) {
                if (configurationDto.getInfinityTotal() == null || configurationDto.getInfinityTotal() != 1) { // 库存无限量，1=是，0=不是
                    prizeConfigurationEntity.setPrizeRemainderNumber(configurationDto.getPrizeTotalNumber());
                } else {
                    prizeConfigurationEntity.setPrizeRemainderNumber(null);
                }
            } else {
                LotteryPrizeDailyStockModel dailyStock = new LotteryPrizeDailyStockModel();
                dailyStock.setConfigurationNum(configurationNum);
                dailyStock.setCreateTime(new Timestamp(new Date().getTime()));
                dailyStock.setDay(today);
                dailyStock.setPrizeNumber(configurationDto.getPrizeNumber());
                dailyStock.setStock(configurationDto.getPrizeTotalNumber());
                if (configurationDto.getInfinityTotal() == null || configurationDto.getInfinityTotal() != 1) { // 库存无限量，1=是，0=不是
                    dailyStock.setRemainStock(configurationDto.getPrizeTotalNumber());
                } else {
                    dailyStock.setRemainStock(null);
                }
                prizeConfigurationEntity.setPrizeRemainderNumber(null);
                lotteryPrizeDailyStockModelRepository.save(dailyStock);
            }

            prizeConfigurationEntity.setPrizeType(configurationDto.getPrizeType());
            prizeConfigurationEntity.setPrizeLocationIndex(configurationDto.getPrizeLocationIndex());
            prizeConfigurationEntity.setConfigurationNum(configurationNum);
            prizeConfigurationEntity.setUseStatus(MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
            prizeConfigurationEntity.setPrizeValue(configurationDto.getPrizeValue());
            // 新增字段20200722
            prizeConfigurationEntity.setWinTime(configurationDto.getWinTime());
            prizeConfigurationEntity.setWinTimeValue(configurationDto.getWinTimeValue());
            prizeConfigurationEntity.setWinType(configurationDto.getWinType());
            prizeConfigurationEntity.setInfinityTotal(configurationDto.getInfinityTotal());
            prizeConfigurationEntity.setCouponUrl(configurationDto.getCouponUrl());
            prizeConfigurationEntity.setNotifyUser(configurationDto.getNotifyUser());
            prizeConfigurationEntity.setSmsTpl(configurationDto.getSmsTpl());

            lotteryPrizeConfiguration.saveAndFlush(prizeConfigurationEntity);
            prizeName.put(configurationDto.getPrizeNumber(),configurationDto.getPrizeName());
        }

        List<LotterWinSettingDto> lotteryWinList= paramBody.getWinSetting();
        for(LotterWinSettingDto lotteryWin:lotteryWinList){
            LotteryWinSettingModel lotteryWinSettingModel= new LotteryWinSettingModel();
            lotteryWinSettingModel.setPrizeNumber(lotteryWin.getPrizeNumber());
            lotteryWinSettingModel.setSerialNumber(Integer.parseInt(lotteryWin.getSerialNumber()));
            lotteryWinSettingModel.setPrizeName(prizeName.get(lotteryWin.getPrizeNumber()));
            lotteryWinSettingModel.setConfigurationNum(configurationNum);
            lotteryWinSetting.saveAndFlush(lotteryWinSettingModel);
        }

        List<LotterNewsBroadcastDto> lotteryNewsBroadcastList=paramBody.getNewsBroadcast();
        for(LotterNewsBroadcastDto newsBroadcast:lotteryNewsBroadcastList){
            LotteryNewsBroadcastModel lotteryNewsBroadcastModel= new LotteryNewsBroadcastModel();
            lotteryNewsBroadcastModel.setPrizeNumber(newsBroadcast.getPrizeNumber());
            lotteryNewsBroadcastModel.setNickName(newsBroadcast.getNickName());
            lotteryNewsBroadcastModel.setPrizeName(prizeName.get(newsBroadcast.getPrizeNumber()));
            lotteryNewsBroadcastModel.setConfigurationNum(configurationNum);
            lotteryNewsBroadcast.saveAndFlush(lotteryNewsBroadcastModel);
        }
    }

    @Override
    public void updateLotteryBaseConfiguration(LotteryBaseConfigurationDto paramBody, String username) {
        String configurationNum= paramBody.getConfigurationNum();
        LotteryBaseConfigurationModel baseConfiguration= lotteryBaseConfiguration.findByConfigurationNum(configurationNum);
        baseConfiguration.setConfigurationNum(configurationNum);
        baseConfiguration.setActivityName(paramBody.getActivityName());
        baseConfiguration.setEffectiveStartTime(paramBody.getEffectiveStartTime());
        baseConfiguration.setEffectiveEndTime(paramBody.getEffectiveEndTime());
        baseConfiguration.setEveryDayLimit(paramBody.getEveryDayLimit());
        baseConfiguration.setEveryDayFree(paramBody.getEveryDayFree());
        baseConfiguration.setCostGoldNumber(paramBody.getCostGoldNumber());
        baseConfiguration.setActivityRule(paramBody.getActivityRule());
        baseConfiguration.setOpenStatus(paramBody.getOpenStatus());
        baseConfiguration.setType(paramBody.getType());
        baseConfiguration.setEveryDayShareAddLimit(paramBody.getEveryDayShareAddLimit());
        baseConfiguration.setShareAddFree(paramBody.getShareAddFree());
        baseConfiguration.setShareAddPrizeCode(paramBody.getShareAddPrizeCode());
        baseConfiguration.setWinSettingPeriod(paramBody.getWinSettingPeriod());
        baseConfiguration.setPrizePeriod(paramBody.getPrizePeriod());
        baseConfiguration.setBroadcastInterval(paramBody.getBroadcastInterval());
        baseConfiguration.setOutoffStockPrizeCode(paramBody.getOutoffStockPrizeCode());

        List<LotterPrizeConfigurationDto> prizeConfigurationDto=paramBody.getPrizeConfiguration();
        List<LotteryPrizeConfigurationModel> lastLotteryPrizeConfigurationList=lotteryPrizeConfiguration.findByconfigurationNumAndUseStatus(paramBody.getConfigurationNum(), MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
        //判断新提交的记录在上一次DB中记录中是否存在
        Map<String,String> prizeName =new HashMap<String,String>();
        String today = TimeUtils.getDateStr(new Date());
        for(LotterPrizeConfigurationDto prizeConfiguration: prizeConfigurationDto){
            //修改：当前记录在历史记录中 , 新增：当前记录不在历史记录中
            if(!checkDbIsExists(lastLotteryPrizeConfigurationList,prizeConfiguration)){
                LotteryPrizeConfigurationModel lotteryPrizeConfigurationModel=new LotteryPrizeConfigurationModel();
                lotteryPrizeConfigurationModel.setPrizeNumber(prizeConfiguration.getPrizeNumber());
                lotteryPrizeConfigurationModel.setPrizeName(prizeConfiguration.getPrizeName());
                lotteryPrizeConfigurationModel.setPrizeIcon(prizeConfiguration.getPrizeIcon());
                // 处理奖品权重
                if (3 == prizeConfiguration.getWinType()) {
                    String prizeProbability  = prizeConfiguration.getPrizeProbability();
                    if(!StringUtils.isEmpty(prizeProbability)){
                        BigDecimal probability= new BigDecimal(prizeProbability);
                        BigDecimal value=probability.divide(new BigDecimal("100"));
                        lotteryPrizeConfigurationModel.setPrizeProbability(String.valueOf(value));
                    }else{
                        lotteryPrizeConfigurationModel.setPrizeProbability("0");
                    }
                } else {
                    lotteryPrizeConfigurationModel.setPrizeProbability(prizeConfiguration.getPrizeProbability());
                }
                lotteryPrizeConfigurationModel.setPrizeTotalNumber(prizeConfiguration.getPrizeTotalNumber());

                // 设置库存  库存周期，1=永久，2=日
                if (baseConfiguration.getPrizePeriod() == 1) {
                    if (prizeConfiguration.getInfinityTotal() == null || prizeConfiguration.getInfinityTotal() != 1) { // 库存无限量，1=是，0=不是
                        lotteryPrizeConfigurationModel.setPrizeRemainderNumber(prizeConfiguration.getPrizeTotalNumber());
                    } else {
                        lotteryPrizeConfigurationModel.setPrizeRemainderNumber(null);
                    }
                } else {
                    LotteryPrizeDailyStockModel dailyStock = new LotteryPrizeDailyStockModel();
                    dailyStock.setConfigurationNum(configurationNum);
                    dailyStock.setCreateTime(new Timestamp(new Date().getTime()));
                    dailyStock.setDay(today);
                    dailyStock.setPrizeNumber(prizeConfiguration.getPrizeNumber());
                    dailyStock.setStock(prizeConfiguration.getPrizeTotalNumber());
                    if (prizeConfiguration.getInfinityTotal() == null || prizeConfiguration.getInfinityTotal() != 1) { // 库存无限量，1=是，0=不是
                        dailyStock.setRemainStock(prizeConfiguration.getPrizeTotalNumber());
                    } else {
                        dailyStock.setRemainStock(null);
                    }
                    lotteryPrizeConfigurationModel.setPrizeRemainderNumber(null);
                    lotteryPrizeDailyStockModelRepository.save(dailyStock);
                }

                lotteryPrizeConfigurationModel.setPrizeType(prizeConfiguration.getPrizeType());
                lotteryPrizeConfigurationModel.setPrizeLocationIndex(prizeConfiguration.getPrizeLocationIndex());
                lotteryPrizeConfigurationModel.setConfigurationNum(prizeConfiguration.getConfigurationNum());
                lotteryPrizeConfigurationModel.setPrizeValue(prizeConfiguration.getPrizeValue());
                // 新增字段20200722
                lotteryPrizeConfigurationModel.setWinTime(prizeConfiguration.getWinTime());
                lotteryPrizeConfigurationModel.setWinTimeValue(prizeConfiguration.getWinTimeValue());
                lotteryPrizeConfigurationModel.setWinType(prizeConfiguration.getWinType());
                lotteryPrizeConfigurationModel.setInfinityTotal(prizeConfiguration.getInfinityTotal());
                lotteryPrizeConfigurationModel.setCouponUrl(prizeConfiguration.getCouponUrl());
                lotteryPrizeConfigurationModel.setNotifyUser(prizeConfiguration.getNotifyUser());
                lotteryPrizeConfigurationModel.setSmsTpl(prizeConfiguration.getSmsTpl());
                lotteryPrizeConfigurationModel.setUseStatus(MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
                lotteryPrizeConfiguration.saveAndFlush(lotteryPrizeConfigurationModel);
            } else {
                LotteryPrizeConfigurationModel  lotteryPrizeConfigurationModel=lotteryPrizeConfiguration.findByConfigurationNumAndPrizeNumberAndUseStatus(paramBody.getConfigurationNum(),prizeConfiguration.getPrizeNumber(),MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
                lotteryPrizeConfigurationModel.setPrizeNumber(prizeConfiguration.getPrizeNumber());
                lotteryPrizeConfigurationModel.setPrizeName(prizeConfiguration.getPrizeName());
                lotteryPrizeConfigurationModel.setPrizeIcon(prizeConfiguration.getPrizeIcon());
                // 处理奖品权重
                if (3 == prizeConfiguration.getWinType()) {
                    String prizeProbability  = prizeConfiguration.getPrizeProbability();
                    if(!StringUtils.isEmpty(prizeProbability)){
                        BigDecimal probability= new BigDecimal(prizeProbability);
                        BigDecimal value=probability.divide(new BigDecimal("100"));
                        lotteryPrizeConfigurationModel.setPrizeProbability(String.valueOf(value));
                    }else{
                        lotteryPrizeConfigurationModel.setPrizeProbability("0");
                    }
                } else {
                    lotteryPrizeConfigurationModel.setPrizeProbability(prizeConfiguration.getPrizeProbability());
                }

                // 计算剩余库存
                Integer currentPrizePeriod = paramBody.getPrizePeriod(); // 库存周期，1=永久，2=日
                Integer useStock = 0;
                if (currentPrizePeriod == 1) {
                    useStock = lotteryPrizeRecordModelRepository.countByConfigurationNumAndPrizeNumber(configurationNum, prizeConfiguration.getPrizeNumber());
                    // 删除每日库存
                    LotteryPrizeDailyStockModel dailyStock = lotteryPrizeDailyStockModelRepository.findFirstByConfigurationNumAndPrizeNumberAndDay(configurationNum, prizeConfiguration.getPrizeNumber(), today);
                    if (dailyStock != null) {
                        // upgrade (hepeng)
                        lotteryPrizeDailyStockModelRepository.delete(dailyStock);
//                        lotteryPrizeDailyStockModelRepository.delete(dailyStock.getId());
                    }
                    if (prizeConfiguration.getInfinityTotal() == null || prizeConfiguration.getInfinityTotal() != 1) { // 库存无限量，1=是，0=不是
                        lotteryPrizeConfigurationModel.setPrizeRemainderNumber(prizeConfiguration.getPrizeTotalNumber() > useStock ? (prizeConfiguration.getPrizeTotalNumber() - useStock) : 0);
                    } else {
                        lotteryPrizeConfigurationModel.setPrizeRemainderNumber(null);
                    }
                } else {
                    useStock = lotteryPrizeRecordModelRepository.countByConfigurationNumAndPrizeNumberAndBusinessTime(configurationNum, prizeConfiguration.getPrizeNumber(), today);
                    // 增加每日库存

                    LotteryPrizeDailyStockModel dailyStock = lotteryPrizeDailyStockModelRepository.findFirstByConfigurationNumAndPrizeNumberAndDay(configurationNum, prizeConfiguration.getPrizeNumber(), today);
                    if (dailyStock == null) {
                        dailyStock = new LotteryPrizeDailyStockModel();
                        dailyStock.setCreateTime(new Timestamp(new Date().getTime()));
                    }
                    dailyStock.setConfigurationNum(configurationNum);
                    dailyStock.setDay(today);
                    dailyStock.setPrizeNumber(prizeConfiguration.getPrizeNumber());
                    dailyStock.setStock(prizeConfiguration.getPrizeTotalNumber());
                    if (prizeConfiguration.getInfinityTotal() == null || prizeConfiguration.getInfinityTotal() != 1) { // 库存无限量，1=是，0=不是
                        dailyStock.setRemainStock(prizeConfiguration.getPrizeTotalNumber() > useStock ? (prizeConfiguration.getPrizeTotalNumber() - useStock) : 0);
                    } else {
                        dailyStock.setRemainStock(null);
                    }
                    lotteryPrizeConfigurationModel.setPrizeRemainderNumber(null);
                    lotteryPrizeDailyStockModelRepository.save(dailyStock);
                }

                lotteryPrizeConfigurationModel.setPrizeTotalNumber(prizeConfiguration.getPrizeTotalNumber());
                lotteryPrizeConfigurationModel.setPrizeType(prizeConfiguration.getPrizeType());
                lotteryPrizeConfigurationModel.setPrizeLocationIndex(prizeConfiguration.getPrizeLocationIndex());
                lotteryPrizeConfigurationModel.setConfigurationNum(prizeConfiguration.getConfigurationNum());
                lotteryPrizeConfigurationModel.setUseStatus(MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
                lotteryPrizeConfigurationModel.setPrizeValue(prizeConfiguration.getPrizeValue());
                lotteryPrizeConfigurationModel.setWinTime(prizeConfiguration.getWinTime());
                lotteryPrizeConfigurationModel.setWinTimeValue(prizeConfiguration.getWinTimeValue());
                lotteryPrizeConfigurationModel.setWinType(prizeConfiguration.getWinType());
                lotteryPrizeConfigurationModel.setInfinityTotal(prizeConfiguration.getInfinityTotal());
                lotteryPrizeConfigurationModel.setCouponUrl(prizeConfiguration.getCouponUrl());
                lotteryPrizeConfigurationModel.setNotifyUser(prizeConfiguration.getNotifyUser());
                lotteryPrizeConfigurationModel.setSmsTpl(prizeConfiguration.getSmsTpl());
                lotteryPrizeConfiguration.saveAndFlush(lotteryPrizeConfigurationModel);
            }
            prizeName.put(prizeConfiguration.getPrizeNumber(),prizeConfiguration.getPrizeName());
        }
        //上一次DB中记录中存在,新提交的记录不存在
        for(LotteryPrizeConfigurationModel prizeConfiguration:lastLotteryPrizeConfigurationList){
            //删除：历史记录中存在新列表中不存在[伪删除]
            String prizeNumber=prizeConfiguration.getPrizeNumber();
            boolean isExists = false;
            for(LotterPrizeConfigurationDto reward: prizeConfigurationDto){
                String sn=reward.getPrizeNumber();
                if(prizeNumber.equals(sn)){
                    isExists = true;
                }
            }
            if(!isExists){
                prizeConfiguration.setUseStatus(MenberAvtivityConstants.DELETE_FLAG_INVALID);
                lotteryPrizeConfiguration.saveAndFlush(prizeConfiguration);
            }
        }

        List<LotterWinSettingDto> lotteryWinList= paramBody.getWinSetting();
        lotteryWinSetting.deleteByConfigurationNum(configurationNum);
        for(LotterWinSettingDto lotteryWin:lotteryWinList){
            LotteryWinSettingModel lotteryWinSettingModel= new LotteryWinSettingModel();
            lotteryWinSettingModel.setPrizeNumber(lotteryWin.getPrizeNumber());
            lotteryWinSettingModel.setSerialNumber(Integer.parseInt(lotteryWin.getSerialNumber()));
            lotteryWinSettingModel.setPrizeName(prizeName.get(lotteryWin.getPrizeNumber()));
            lotteryWinSettingModel.setConfigurationNum(configurationNum);
            lotteryWinSetting.saveAndFlush(lotteryWinSettingModel);
        }

        List<LotterNewsBroadcastDto> lotteryNewsBroadcastList=paramBody.getNewsBroadcast();
        lotteryNewsBroadcast.deleteByConfigurationNum(configurationNum);
        for(LotterNewsBroadcastDto newsBroadcast:lotteryNewsBroadcastList){
            LotteryNewsBroadcastModel lotteryNewsBroadcastModel= new LotteryNewsBroadcastModel();
            lotteryNewsBroadcastModel.setConfigurationNum(configurationNum);
            lotteryNewsBroadcastModel.setPrizeNumber(newsBroadcast.getPrizeNumber());
            lotteryNewsBroadcastModel.setNickName(newsBroadcast.getNickName());
            lotteryNewsBroadcastModel.setPrizeName(prizeName.get(newsBroadcast.getPrizeNumber()));
            lotteryNewsBroadcast.saveAndFlush(lotteryNewsBroadcastModel);
        }
    }

    @Override
    public LotteryBaseConfigurationH5Vo findLotteryBaseConfigurationH5Detail(String type, String openId) {
        LotteryBaseConfigurationModel lotteryBaseConfigurationModel= lotteryBaseConfiguration.getFirstByTypeAndUseStatus(type,MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
        if(lotteryBaseConfigurationModel == null){
            return null;
        }
        LotteryBaseConfigurationH5Vo baseConfiguration=new LotteryBaseConfigurationH5Vo();
        baseConfiguration.setConfigurationNum(lotteryBaseConfigurationModel.getConfigurationNum());
        baseConfiguration.setActivityName(lotteryBaseConfigurationModel.getActivityName());
        baseConfiguration.setEffectiveStartTime(lotteryBaseConfigurationModel.getEffectiveStartTime());
        baseConfiguration.setEffectiveEndTime(lotteryBaseConfigurationModel.getEffectiveEndTime());
        baseConfiguration.setCostGoldNumber(lotteryBaseConfigurationModel.getCostGoldNumber());
        baseConfiguration.setType(lotteryBaseConfigurationModel.getType());
        baseConfiguration.setActivityRule(lotteryBaseConfigurationModel.getActivityRule());
        baseConfiguration.setOpenStatus(lotteryBaseConfigurationModel.getOpenStatus());
        // 新增字段20200722
        baseConfiguration.setEveryDayShareAddLimit(lotteryBaseConfigurationModel.getEveryDayShareAddLimit());
        baseConfiguration.setShareAddFree(lotteryBaseConfigurationModel.getShareAddFree());
        baseConfiguration.setShareAddPrizeCode(lotteryBaseConfigurationModel.getShareAddPrizeCode());
        baseConfiguration.setWinSettingPeriod(lotteryBaseConfigurationModel.getWinSettingPeriod());
        baseConfiguration.setPrizePeriod(lotteryBaseConfigurationModel.getPrizePeriod());
        baseConfiguration.setBroadcastInterval(lotteryBaseConfigurationModel.getBroadcastInterval());
        baseConfiguration.setOutoffStockPrizeCode(lotteryBaseConfigurationModel.getOutoffStockPrizeCode());

        //返回奖品转盘实体
        List<LotteryPrizeConfigurationVo> lotteryPrizeConfigurationVoList = new ArrayList<LotteryPrizeConfigurationVo>();
        List<LotteryPrizeConfigurationModel> prizeConfigurationList=lotteryPrizeConfiguration.findByconfigurationNumAndUseStatus(lotteryBaseConfigurationModel.getConfigurationNum(), MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
        for (LotteryPrizeConfigurationModel configurationModel:prizeConfigurationList) {
            LotteryPrizeConfigurationVo lotteryPrizeConfigurationVo =new LotteryPrizeConfigurationVo();
            lotteryPrizeConfigurationVo.setPrizeNumber(configurationModel.getPrizeNumber());
            lotteryPrizeConfigurationVo.setPrizeName(configurationModel.getPrizeName());
            lotteryPrizeConfigurationVo.setPrizeIcon(configurationModel.getPrizeIcon());
            lotteryPrizeConfigurationVo.setPrizeType(configurationModel.getPrizeType());
            lotteryPrizeConfigurationVo.setPrizeLocationIndex(configurationModel.getPrizeLocationIndex());
            lotteryPrizeConfigurationVo.setConfigurationNum(configurationModel.getConfigurationNum());
            // 新增字段20200722
            lotteryPrizeConfigurationVo.setWinTime(configurationModel.getWinTime());
            lotteryPrizeConfigurationVo.setWinTimeValue(configurationModel.getWinTimeValue());
            lotteryPrizeConfigurationVo.setWinType(configurationModel.getWinType());
            lotteryPrizeConfigurationVo.setInfinityTotal(configurationModel.getInfinityTotal());
            lotteryPrizeConfigurationVo.setCouponUrl(configurationModel.getCouponUrl());
            if (MenberAvtivityConstants.LOTTERY_TYPE_5.equals(configurationModel.getPrizeType())) {
                lotteryPrizeConfigurationVo.setCouponTotal(memMarktingLuckCardlistRepository.countByPrizeNumber(configurationModel.getPrizeNumber()));
                lotteryPrizeConfigurationVo.setCouponUse(memMarktingLuckCardlistRepository.countByPrizeNumberAndStatus(configurationModel.getPrizeNumber(), 1));
                lotteryPrizeConfigurationVo.setCouponNotUse(memMarktingLuckCardlistRepository.countByPrizeNumberAndStatus(configurationModel.getPrizeNumber(), 0));
                lotteryPrizeConfigurationVo.setCouponExpired(memMarktingLuckCardlistRepository.countByPrizeNumberAndEndTimeLessThan(configurationModel.getPrizeNumber(), TimeUtils.getLongDateStr()));
            }
            lotteryPrizeConfigurationVo.setNotifyUser(configurationModel.getNotifyUser());
            lotteryPrizeConfigurationVo.setSmsTpl(configurationModel.getSmsTpl());
            lotteryPrizeConfigurationVoList.add(lotteryPrizeConfigurationVo);
        }
        baseConfiguration.setPrizeConfiguration(lotteryPrizeConfigurationVoList);

        // 处理天天抽锦鲤活动需求,判断活动是否开始以及信息是否审核通过，今日抽奖记录20-24点方可查看
       if (MenberAvtivityConstants.LOTTERY_TYPE_CJ0004.equals(type)) {
            MemMarktingLuckEvedayModel memMarktingLuckEvedayModel = memMarktingLuckEvedayRepository.findFirstByLuckAid(lotteryBaseConfigurationModel.getId().longValue());
            baseConfiguration.setActivityConfig(memMarktingLuckEvedayModel);
            if (memMarktingLuckEvedayModel != null) {
                Date startTime = TimeUtils.parseDate(memMarktingLuckEvedayModel.getStartTime(), "yyyy-MM-dd");
                Date endTime = TimeUtils.parseDate(memMarktingLuckEvedayModel.getEndTime(), "yyyy-MM-dd");
                Date now = TimeUtils.parseDate(TimeUtils.formatDate(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd");
                baseConfiguration.setIsActivityStart(startTime != null && (startTime.equals(now) || startTime.before(now)) && endTime != null && (endTime.equals(now) || endTime.after(now)));
                baseConfiguration.setIsActivityEnd(endTime != null && endTime.before(now));

              /*  MemUserInfoModel userInfo = memUserInfoModelRepository.findFirstByOpenidEquals(openId);
                if (userInfo != null) {
                    String memUid = userInfo.getMemUid();
                    MemMarketingLuckInvoiceModel memMarketingLuckInvoiceModel = memMarketingLuckInvoiceRepository.findFirstByLuckEverdayIdAndMemUid(memMarktingLuckEvedayModel.getId(), memUid);
                    baseConfiguration.setHasJoinActivity(memMarketingLuckInvoiceModel != null);
                    baseConfiguration.setIsInfoVerifyPass(memMarketingLuckInvoiceModel != null && memMarketingLuckInvoiceModel.getStatus() == 1);
                    if (memMarketingLuckInvoiceModel != null) {
                        baseConfiguration.setCateType(memMarketingLuckInvoiceModel.getSort());
                    }
                } else {
                    baseConfiguration.setHasJoinActivity(false);
                    baseConfiguration.setIsInfoVerifyPass(false);
                }*/

                String pvalue = "20-24";
                PropertiesConfigModel pc = propertiesConfigModelRepository.findFirstByPkeyEquals("TODAY_RECORDS_SEE_TIMERANGE");
                if (pc != null && StringUtils.hasText(pc.getPvalue())) {
                    pvalue = pc.getPvalue();
                }
                String[] parts = pvalue.split("-");
                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                baseConfiguration.setCanSeeTodayRecord(hour >= Integer.parseInt(parts[0]) && hour <= Integer.parseInt(parts[1]));
                baseConfiguration.setCanNotSeeTodayRecordToast(memMarktingLuckEvedayModel.getLuckerToast());
            }
        }
        return baseConfiguration;
    }

    @Override
    public List<LotterNewsBroadcastVo> newsBroadcastList(String configurationNum) {
        List<LotterNewsBroadcastVo> lotteryNewsBroadcastVoList = new ArrayList<LotterNewsBroadcastVo>();
        List<Object[]> newsBroadcastList = lotteryPrizeRecordModelRepository.getLotteryPrizeRecord(configurationNum);
        if (newsBroadcastList != null) {
            for (int i = 0; i < newsBroadcastList.size(); i++) {
                LotterNewsBroadcastVo lotterNewsBroadcastVo = new LotterNewsBroadcastVo();
                Object[] model = newsBroadcastList.get(i);
                String openId = String.valueOf(model[3]);
                lotterNewsBroadcastVo.setNickName(String.valueOf(model[0]));
                lotterNewsBroadcastVo.setPrizeName(String.valueOf(model[1]));
                lotterNewsBroadcastVo.setPrizeNumber(String.valueOf(model[2]));
               /* MemUserWXBaseInfoModel memUserWXBaseInfoModel = memUserWXBaseInfoModelRepository.findFirstByOpenidEquals(openId);
                if (memUserWXBaseInfoModel != null) {
                    lotterNewsBroadcastVo.setNickName(memUserWXBaseInfoModel.getNickName());
                    lotterNewsBroadcastVo.setHeadImgUrl(memUserWXBaseInfoModel.getHeadImgUrl());
                }*/
                lotteryNewsBroadcastVoList.add(lotterNewsBroadcastVo);
            }
            //不足100条自动补足
            if (lotteryNewsBroadcastVoList.size() < 100) {
                List<LotteryNewsBroadcastModel> broadcast = lotteryNewsBroadcast.findByConfigurationNum(configurationNum);
                for (LotteryNewsBroadcastModel lotteryNewsBroadcastModel : broadcast) {
                    LotterNewsBroadcastVo lotterNewsBroadcastVo = new LotterNewsBroadcastVo();
                    lotterNewsBroadcastVo.setPrizeNumber(lotteryNewsBroadcastModel.getPrizeNumber());
                    lotterNewsBroadcastVo.setNickName(lotteryNewsBroadcastModel.getNickName());
                    lotterNewsBroadcastVo.setPrizeName(lotteryNewsBroadcastModel.getPrizeName());
                    if (lotteryNewsBroadcastVoList.size() < 10) {
                        lotteryNewsBroadcastVoList.add(lotterNewsBroadcastVo);
                    }
                }
            }
        } else {
            List<LotteryNewsBroadcastModel> broadcast = lotteryNewsBroadcast.findByConfigurationNum(configurationNum);
            for (LotteryNewsBroadcastModel lotteryNewsBroadcastModel : broadcast) {
                LotterNewsBroadcastVo lotterNewsBroadcastVo = new LotterNewsBroadcastVo();
                lotterNewsBroadcastVo.setPrizeNumber(lotteryNewsBroadcastModel.getPrizeNumber());
                lotterNewsBroadcastVo.setNickName(lotteryNewsBroadcastModel.getNickName());
                lotterNewsBroadcastVo.setPrizeName(lotteryNewsBroadcastModel.getPrizeName());
                lotteryNewsBroadcastVoList.add(lotterNewsBroadcastVo);
            }
        }
        return lotteryNewsBroadcastVoList;
    }

    @Override
    public LotteryPrizeResultVo startLottery(String configurationNum, String openId,String memUid ) {
        LotteryPrizeResultVo lotteryPrizeResult = new LotteryPrizeResultVo();
        LotteryBaseConfigurationModel lotteryBaseConfigurationModel = lotteryBaseConfiguration.findByConfigurationNum(configurationNum);
        String cTime = TimeUtils.getCurrentTime();
        if(TimeUtils.compareDateBefore(lotteryBaseConfigurationModel.getEffectiveStartTime(),cTime)){
            lotteryPrizeResult.setStatus(1);
            lotteryPrizeResult.setNotice("活动未开始!");
            return lotteryPrizeResult;
        }
        if(TimeUtils.compareDateAfter(lotteryBaseConfigurationModel.getEffectiveEndTime(),cTime)){
            lotteryPrizeResult.setStatus(1);
            lotteryPrizeResult.setNotice("活动已结束!");
            return lotteryPrizeResult;
        }
        if (!"1".equals(lotteryBaseConfigurationModel.getOpenStatus())) {
            lotteryPrizeResult.setStatus(1);
            lotteryPrizeResult.setNotice("活动尚未开启");
            return lotteryPrizeResult;
        }
        if (1 != lotteryBaseConfigurationModel.getUseStatus()) {
            lotteryPrizeResult.setStatus(1);
            lotteryPrizeResult.setNotice("活动不可用");
            return lotteryPrizeResult;
        }
        // 检查抽奖次数限制
        String today = TimeUtils.getDateStr(new Date());
        int everyDayLimit = lotteryBaseConfigurationModel.getEveryDayLimit();
        int useInitCount = lotteryPrizeRecordModelRepository.countByConfigurationNumAndOpenIdAndBusinessTimeAndSource(configurationNum, openId, today, MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_2);
        int totalWithoutInit = lotteryPrizeChanceModelRepository.countByConfigurationNumAndOpenIdAndUseStatusAndActiveStateAndBusinessTimeAndSourceNot(configurationNum, openId, MenberAvtivityConstants.LOTTERY_CHANCE_NOT_USE,MenberAvtivityConstants.LOTTERY_CHANCE_ACTIVE_STATE_Y, today, MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_2);
        _log.info("每日初始化限制次数" + everyDayLimit + "，已使用初始化次数" + useInitCount + "，除初始化剩余次数" + totalWithoutInit);
        if (totalWithoutInit <= 0 && useInitCount >= everyDayLimit) {
            lotteryPrizeResult.setStatus(1);
            lotteryPrizeResult.setChance(0);
            lotteryPrizeResult.setNotice("您抽奖次数已达到上限,欢迎明天再来！");
            return lotteryPrizeResult;
        }

        LotteryPrizeChanceModel lotteryPrizeChanceModel= null;
        synchronized (LotteryActivityImpl.class) {
            // 抽奖机会按渠道优先级，渠道赠送>系统赠送>分享获得
            lotteryPrizeChanceModel = lotteryPrizeChanceModelRepository.getFirstByOpenIdAndConfigurationNumAndUseStatusAndActiveStateAndBusinessTimeAndSourceNotInOrderByCreateTimeAsc(openId,configurationNum,MenberAvtivityConstants.LOTTERY_CHANCE_NOT_USE,MenberAvtivityConstants.LOTTERY_CHANCE_ACTIVE_STATE_Y, TimeUtils.getDateStr(new Date()), new String []{MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_2, MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_6});
            boolean isShare = false; // 是否是分享获得
            if(lotteryPrizeChanceModel == null && useInitCount < everyDayLimit) {
                _log.info("没有获取到除分享外获得和系统赠送的抽奖机会，获取系统赠送的抽奖机会");
                lotteryPrizeChanceModel = lotteryPrizeChanceModelRepository.getFirstByOpenIdAndConfigurationNumAndUseStatusAndActiveStateAndBusinessTimeAndSourceNotInOrderByCreateTimeAsc(openId,configurationNum,MenberAvtivityConstants.LOTTERY_CHANCE_NOT_USE,MenberAvtivityConstants.LOTTERY_CHANCE_ACTIVE_STATE_Y, TimeUtils.getDateStr(new Date()), new String []{MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_6});
            }

            if(lotteryPrizeChanceModel == null) {
                _log.info("没有获取到系统赠送的抽奖机会，获取包含分享获得的抽奖机会");
                lotteryPrizeChanceModel = lotteryPrizeChanceModelRepository.getFirstByOpenIdAndConfigurationNumAndUseStatusAndActiveStateAndBusinessTimeOrderByCreateTimeAsc(openId,configurationNum,MenberAvtivityConstants.LOTTERY_CHANCE_NOT_USE,MenberAvtivityConstants.LOTTERY_CHANCE_ACTIVE_STATE_Y, TimeUtils.getDateStr(new Date()));
                isShare = lotteryPrizeChanceModel != null;
            }

            if(lotteryPrizeChanceModel == null) {
                lotteryPrizeResult.setStatus(1);
                lotteryPrizeResult.setNotice("您今日抽奖机会已经全部用完,明天再来吧！");
                return lotteryPrizeResult;
            }

            // 获取奖品信息
            List<LotteryPrizeConfigurationModel> prizeConfigurationList = lotteryPrizeConfiguration.findByconfigurationNumAndUseStatus(configurationNum, MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
            if(prizeConfigurationList.isEmpty()) {
                lotteryPrizeResult.setStatus(1);
                lotteryPrizeResult.setNotice("对不起,来晚了一步,奖池已没有任何奖品！");
                return lotteryPrizeResult;
            }

            // 开始抽奖
            LotteryPrizeConfigurationModel matchPrize = null;
            if (isShare) {
                _log.info("抽奖机会为分享获得");
                String shareAddPrizeCode = lotteryBaseConfigurationModel.getShareAddPrizeCode(); // 分享获得的次数抽奖结果设置
                if (StringUtils.hasText(shareAddPrizeCode)) { // 分享获得的次数抽奖结果设置-指定商品
                    _log.info("抽奖机会为分享获得-指定了奖品");
                    matchPrize = lotteryPrizeConfiguration.findByConfigurationNumAndPrizeNumberAndUseStatus(lotteryBaseConfigurationModel.getConfigurationNum(), shareAddPrizeCode.trim(), MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
                } else { // 分享获得的次数抽奖结果设置-随机
                    _log.info("抽奖机会为分享获得-没指定奖品-进行随机抽奖");
                    matchPrize = randomMatch(lotteryBaseConfigurationModel, prizeConfigurationList, openId);
                }
            } else {
                _log.info("抽奖机会不是分享获得，检查必中奖品");
                // 必中奖品
                List<LotteryWinSettingModel> lotteryWinList = lotteryWinSetting.findByConfigurationNumOrderBySerialNumber(configurationNum);
                int lotteryCount, winListSize = lotteryWinList.size();
                Integer winSettingPeriod = lotteryBaseConfigurationModel.getWinSettingPeriod(); // 必中周期，1=永久，2=日
                if (winSettingPeriod == 2) {
                    _log.info("必中奖品必中周期为日");
                    lotteryCount = lotteryPrizeRecordModelRepository.countByConfigurationNumAndOpenIdAndBusinessTime(configurationNum, openId, today);
                } else {
                    _log.info("必中奖品必中周期为永久");
                    lotteryCount = lotteryPrizeRecordModelRepository.countByConfigurationNumAndOpenId(configurationNum, openId);
                }
                lotteryCount += 1;
                if (lotteryCount <= winListSize) { // 获取必中奖品
                    for (LotteryWinSettingModel lotteryWin : lotteryWinList) {
                        int serialNumber = lotteryWin.getSerialNumber();
                        if (lotteryCount == serialNumber) {
                            _log.info("匹配到必中奖品");
                            matchPrize = lotteryPrizeConfiguration.findByConfigurationNumAndPrizeNumberAndUseStatus(lotteryBaseConfigurationModel.getConfigurationNum(), lotteryWin.getPrizeNumber(), MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
                            if (matchPrize != null && matchPrize.getWinTime() == 2) { // 中奖固定时间，1=每日，2=每月最后N天
                                _log.info("中奖时间为每月最后" + matchPrize.getWinTimeValue() + "天");
                                Date now = TimeUtils.parseDate(TimeUtils.formatDate(new Date(), "yyyyMMdd"), "yyyyMMdd");
                                Date[] dates = TimeUtils.getLastNDayOfMonth(now, matchPrize.getWinTimeValue() - 1);
                                _log.info("中奖时间范围：" + TimeUtils.formatDate(dates[0], "yyyy-MM-dd") + "~" + TimeUtils.formatDate(dates[1], "yyyy-MM-dd"));
                                if (now.before(dates[0]) && !now.equals(dates[0])) { // 不在中奖日期
                                    _log.info("不在中奖日期");
                                    matchPrize = null;
                                }
                            }
                            break;
                        }
                    }
                }

                // 按规则获取奖品
                if (matchPrize == null) {
                    _log.info("未匹配到必中奖品，随机抽奖");
                    matchPrize = randomMatch(lotteryBaseConfigurationModel, prizeConfigurationList, openId);
                }
            }

            if (matchPrize == null) { // 以防最终获取不到奖品
                _log.info("未匹配到中奖奖品，提示错误");
                lotteryPrizeResult.setStatus(1);
                lotteryPrizeResult.setNotice("对不起,来晚了一步,奖池已没有任何奖品！");
                return lotteryPrizeResult;
            }

            _log.error(JSON.toJSONString(matchPrize, true));

            _log.info("检查奖品是否是有库存");
            // 检查库存量 ， 中奖奖品库存不足，获取对应设置的奖品，该奖品库存必须是不限量的
            LotteryPrizeDailyStockModel dailyStock = null;
            Integer prizePeriod = lotteryBaseConfigurationModel.getPrizePeriod(); // 库存周期，1=永久，2=日
            Integer infinityTotal = matchPrize.getInfinityTotal(); // 库存无限量，1=是，0=不是
            if (infinityTotal == null || infinityTotal != 1) {
                _log.info("奖品库存为有限量");
                Integer stock = 0;
                if (prizePeriod == 2) { // 库存周期为日，按日查找库存，没有记录则新增
                    _log.info("奖品库存周期为日");
                    dailyStock = lotteryPrizeDailyStockModelRepository.findFirstByConfigurationNumAndPrizeNumberAndDay(configurationNum, matchPrize.getPrizeNumber(), today);
                    if (dailyStock == null) {
                        _log.info("奖品库存周期为日，没有库存记录-新建");
                        dailyStock = new LotteryPrizeDailyStockModel();
                        dailyStock.setConfigurationNum(configurationNum);
                        dailyStock.setCreateTime(new Timestamp(new Date().getTime()));
                        dailyStock.setDay(today);
                        dailyStock.setPrizeNumber(matchPrize.getPrizeNumber());
                        dailyStock.setStock(matchPrize.getPrizeTotalNumber());
                        dailyStock.setRemainStock(matchPrize.getPrizeTotalNumber());
                        lotteryPrizeDailyStockModelRepository.save(dailyStock);
                    }
                    stock = dailyStock.getRemainStock() == null ? 0 : dailyStock.getRemainStock();
                } else {
                    _log.info("奖品库存周期为永久");
                    stock = matchPrize.getPrizeRemainderNumber() == null ? 0 : matchPrize.getPrizeRemainderNumber();
                }

                if (stock <= 0) {
                    _log.error("中奖产品库存不足，获取对应设置的奖品");
                    matchPrize = lotteryPrizeConfiguration.findByConfigurationNumAndPrizeNumberAndUseStatus(lotteryBaseConfigurationModel.getConfigurationNum(), lotteryBaseConfigurationModel.getOutoffStockPrizeCode(), MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
                }
            } else {
                _log.info("库存为无限量，无需扣除库存");
            }

            // 5 兑换券，如果没有可用卡密，则库存是0
            if (MenberAvtivityConstants.LOTTERY_TYPE_5.equals(matchPrize.getPrizeType())) {
                _log.info("奖品为兑换券");
                String now = TimeUtils.getLongDateStr();
                MemMarktingLuckCardlistModel cardInfo = memMarktingLuckCardlistRepository.findFirstByPrizeNumberAndStatusAndStartTimeLessThanAndEndTimeGreaterThanOrderByCteateTimeAsc(matchPrize.getPrizeNumber(), 0, now, now);
                if (cardInfo == null) {
                    _log.info("奖品为兑换券，没有有效的兑换券，获取对应设置的奖品");
                    matchPrize = lotteryPrizeConfiguration.findByConfigurationNumAndPrizeNumberAndUseStatus(lotteryBaseConfigurationModel.getConfigurationNum(), lotteryBaseConfigurationModel.getOutoffStockPrizeCode(), MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
                }
            }

            _log.error(JSON.toJSONString(matchPrize, true));

            String typeDesc = MenberAvtivityConstants.LOTTERY_TYPE_CJ0004.equals(lotteryBaseConfigurationModel.getType()) ? "天天抽锦鲤"
                    : (MenberAvtivityConstants.LOTTERY_TYPE_CJ0003.equals(lotteryBaseConfigurationModel.getType()) ? "五一抽奖"
                    : (MenberAvtivityConstants.LOTTERY_TYPE_CJ0002.equals(lotteryBaseConfigurationModel.getType()) ? "元旦抽奖" : "每日抽奖"));
            // 扣除金币
            Integer costGoldNumber = lotteryBaseConfigurationModel.getCostGoldNumber(); // 每次消耗金币数
            Integer shareAddFree = lotteryBaseConfigurationModel.getShareAddFree(); // 分享获得的次数是否免费，1=是，0=否
            Integer everyDayFree = lotteryBaseConfigurationModel.getEveryDayFree();
            if (costGoldNumber != null && costGoldNumber > 0 && ((isShare && shareAddFree != null && shareAddFree == 0) || useInitCount >= everyDayFree)) {
                _log.info("分享次数需要消耗金币，今日赠送免费次数达到上限");
                /*ResponseData<MemCoinDetailModel> respData = memService.addcoin(memUid, MenberAvtivityConstants.SOURCE_EVERYDAY_LOTTERY_CONSUME, -costGoldNumber, typeDesc + "-扣除金币");
                _log.info(JSON.toJSONString(respData, true));
                if (respData.getCode() == 1002) { // 金币不足
                    lotteryPrizeResult.setStatus(1);
                    lotteryPrizeResult.setNotice("对不起,您的金币不足！");
                    return lotteryPrizeResult;
                }*/
            }

            // 扣除奖品库存
            if (matchPrize.getInfinityTotal() == null || matchPrize.getInfinityTotal() != 1) {
                _log.info("库存为有限量，扣除库存");
                // 库存周期，1=永久，2=日    扣除奖品库存
                if (prizePeriod == 2) { // 库存周期为日，按日查找库存，没有记录则新增
                    dailyStock = lotteryPrizeDailyStockModelRepository.findFirstByConfigurationNumAndPrizeNumberAndDay(configurationNum, matchPrize.getPrizeNumber(), today);
                    if (dailyStock == null) {
                        dailyStock = new LotteryPrizeDailyStockModel();
                        dailyStock.setConfigurationNum(configurationNum);
                        dailyStock.setCreateTime(new Timestamp(new Date().getTime()));
                        dailyStock.setDay(today);
                        dailyStock.setPrizeNumber(matchPrize.getPrizeNumber());
                        dailyStock.setStock(matchPrize.getPrizeTotalNumber());
                        dailyStock.setRemainStock(matchPrize.getPrizeTotalNumber());
                        lotteryPrizeDailyStockModelRepository.save(dailyStock);
                    }
                    dailyStock.setRemainStock(dailyStock.getRemainStock() - 1);
                    lotteryPrizeDailyStockModelRepository.save(dailyStock);
                } else {
                    matchPrize.setPrizeRemainderNumber(matchPrize.getPrizeRemainderNumber() - 1);
                    lotteryPrizeConfiguration.saveAndFlush(matchPrize);
                }
            } else {
                _log.info("库存为无限量，无需扣除库存");
            }

            _log.info("扣除中奖机会");
            // 扣除抽奖机会
            lotteryPrizeChanceModel.setUseStatus(MenberAvtivityConstants.LOTTERY_CHANCE_USE);
            lotteryPrizeChanceModelRepository.saveAndFlush(lotteryPrizeChanceModel);

            _log.info("兑换奖品");
            //保存中奖信息[并进行兑换]
            this.exChangePrize(lotteryBaseConfigurationModel, lotteryPrizeChanceModel, matchPrize, lotteryPrizeResult, memUid, openId, today);
        }

        return lotteryPrizeResult;
    }

    @Override
    public ListWithPage<LotteryRecordVo> advancedSearchLotteryResultWithCondition(LotterResultSearchParamsDto paramBody, Pageable pageable, String userId) {
        Long sumTotal = null;
        List<LotteryRecordVo> records = null;
        //构造初始状态排序条件
        List< Sort.Order> orders=new ArrayList< Sort.Order>();
        orders.add( new Sort.Order(Sort.Direction.DESC, "createTime"));
        Pageable page = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), Sort.by(orders));
//        Pageable page = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),new Sort(orders));
        Page<LotteryPrizeRecordModel> result = lotteryPrizeRecordModelRepository.findAll(new Specification<LotteryPrizeRecordModel>() {
            @Override
            public Predicate toPredicate(Root<LotteryPrizeRecordModel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return createLotteryRecordQueryCondition(root,criteriaQuery,criteriaBuilder,paramBody);
            }
        },page);
        //数据库实体对象转VO对象
        if (result != null){
            sumTotal = result.getTotalElements();
            records = lotteryRecordMemoryValueToViewObject(result.getContent());
        }else {
            sumTotal = Long.getLong("0") ;
            records = new ArrayList<LotteryRecordVo>();
        }
        return PageUtils.formatData(records, pageable,sumTotal);
    }

    @Override
    public void addDefaultLotteryChance(String openId, String memUid, String type,String configurationNum) {
        LotteryBaseConfigurationModel lotteryBaseConfigurationModel = lotteryBaseConfiguration.findByConfigurationNum(configurationNum);
        if(lotteryBaseConfigurationModel != null) {
            if (MenberAvtivityConstants.LOTTERY_TYPE_CJ0004.equals(type)) { // 天天抽锦鲤活动，需要审核通过才能增加次数
                MemMarktingLuckEvedayModel memMarktingLuckEvedayModel = memMarktingLuckEvedayRepository.findFirstByLuckAid(lotteryBaseConfigurationModel.getId().longValue());
                if (memMarktingLuckEvedayModel == null) {
                    _log.info("天天抽锦鲤活动记录不存在");
                    return;
                }
                if (memMarktingLuckEvedayModel.getStatus() != 1) {
                    _log.info("天天抽锦鲤活动状态不是开启");
                    return;
                }
                Date startTime = TimeUtils.parseDate(memMarktingLuckEvedayModel.getStartTime(), "yyyy-MM-dd");
                Date endTime = TimeUtils.parseDate(memMarktingLuckEvedayModel.getEndTime(), "yyyy-MM-dd");
                Date now = TimeUtils.parseDate(TimeUtils.formatDate(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd");
                boolean start = startTime != null && (startTime.equals(now) || startTime.before(now)) && endTime != null && (endTime.equals(now) || endTime.after(now));
                if (!start) {
                    _log.info("天天抽锦鲤活动不在活动期间");
                    return;
                }
                if (!StringUtils.hasText(memUid)) {
                    //memUid = memUserInfoModelRepository.findFirstByOpenidEquals(openId).getMemUid();
                }
                MemMarketingLuckInvoiceModel memMarketingLuckInvoiceModel = memMarketingLuckInvoiceRepository.findFirstByLuckEverdayIdAndMemUid(memMarktingLuckEvedayModel.getId(), memUid);
                boolean pass = memMarketingLuckInvoiceModel != null && memMarketingLuckInvoiceModel.getStatus() == 1;
                if (!pass) {
                    _log.info("天天抽锦鲤活动提交信息没有审核通过");
                    return;
                }
            }

            String cTime = TimeUtils.getCurrentTime();
            if(TimeUtils.compareDateBefore(lotteryBaseConfigurationModel.getEffectiveStartTime(),cTime)){
                _log.info("还没有到抽奖时间");
                return;
            }

            if(TimeUtils.compareDateAfter(lotteryBaseConfigurationModel.getEffectiveEndTime(),cTime)){
                _log.info("抽奖时间结束");
                return;
            }

            //检查当日是否增加了抽奖机会
            List<LotteryPrizeChanceModel> lotteryPrizeChanceModel= lotteryPrizeChanceModelRepository.findByOpenIdAndConfigurationNumAndBusinessTimeAndSource(openId,lotteryBaseConfigurationModel.getConfigurationNum(),TimeUtils.getDateStr(new Date()),MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_2);
            if (lotteryPrizeChanceModel == null || lotteryPrizeChanceModel.isEmpty()) {
                int everyDayLimit = lotteryBaseConfigurationModel.getEveryDayLimit();
                for(int i = 1; i <= everyDayLimit; i++){
                    LotteryPrizeChanceModel prizeChanceModel = new LotteryPrizeChanceModel();
                    prizeChanceModel.setChanceId(StringsUtil.getUUID32());
                    prizeChanceModel.setConfigurationNum(lotteryBaseConfigurationModel.getConfigurationNum());
                    prizeChanceModel.setOpenId(openId);
                    prizeChanceModel.setUseStatus(MenberAvtivityConstants.LOTTERY_CHANCE_NOT_USE);//未使用
                    prizeChanceModel.setSource(MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_2);//每日赠送
                    prizeChanceModel.setBusinessTime(TimeUtils.getDateStr(new Date()));
                    prizeChanceModel.setActiveState(MenberAvtivityConstants.LOTTERY_CHANCE_ACTIVE_STATE_Y);//有效的
                    lotteryPrizeChanceModelRepository.saveAndFlush(prizeChanceModel);
                }
            }
        }
    }

    @Override
    public void deleteLotteryConfigure(String configurationNum) {
        //基础配置设置为无效
        LotteryBaseConfigurationModel lotteryBaseConfigurationModel= lotteryBaseConfiguration.findByConfigurationNum(configurationNum);
        if(lotteryBaseConfigurationModel == null){
            return;
        }
        lotteryBaseConfigurationModel.setUseStatus(MenberAvtivityConstants.DELETE_FLAG_INVALID);
        lotteryBaseConfiguration.saveAndFlush(lotteryBaseConfigurationModel);
        //奖品配置设置为无效
        List<LotteryPrizeConfigurationModel> prizeConfigurationList= lotteryPrizeConfiguration.findByConfigurationNum(configurationNum);
        for(LotteryPrizeConfigurationModel PrizeConfiguration:prizeConfigurationList){
            PrizeConfiguration.setUseStatus(MenberAvtivityConstants.DELETE_FLAG_INVALID);
            lotteryPrizeConfiguration.saveAndFlush(PrizeConfiguration);
        }
    }

    @Override
    public List<LotteryConfigureTypeVo> configureType() {
        PropertiesConfigModel propertiesConfig= propertiesConfigModelRepository.findFirstByPkeyEquals("LOTTERY_CONFIGURE_TYPE");
        String value= propertiesConfig.getPvalue();
        List<LotteryConfigureTypeVo> list = new ArrayList<LotteryConfigureTypeVo>();
        JSONArray valueArray= JSON.parseArray(value);
        for (int i = 0; i <valueArray.size() ; i++) {
            JSONObject jsonObject= valueArray.getJSONObject(i);
            LotteryConfigureTypeVo LotteryConfigureTypeVo =new LotteryConfigureTypeVo();
            LotteryConfigureTypeVo.setType(jsonObject.getString("type"));
            LotteryConfigureTypeVo.setName(jsonObject.getString("name"));
            list.add(LotteryConfigureTypeVo);
        }
        return list;
    }

    @Override
    public int countLotteryChance(String configurationNum, String openId) {
        LotteryBaseConfigurationModel lotteryBaseConfigurationModel = lotteryBaseConfiguration.findByConfigurationNum(configurationNum);
        String today = TimeUtils.getDateStr(new Date());
        int useInitCount = lotteryPrizeRecordModelRepository.countByConfigurationNumAndOpenIdAndBusinessTimeAndSource(configurationNum, openId, today, MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_2);
        int remainInitCount = lotteryBaseConfigurationModel.getEveryDayLimit() > useInitCount ? (lotteryBaseConfigurationModel.getEveryDayLimit() - useInitCount) : 0;
        int existInitCount = lotteryPrizeChanceModelRepository.countByConfigurationNumAndOpenIdAndUseStatusAndActiveStateAndBusinessTimeAndSource(configurationNum, openId, MenberAvtivityConstants.LOTTERY_CHANCE_NOT_USE,MenberAvtivityConstants.LOTTERY_CHANCE_ACTIVE_STATE_Y, today, MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_2);
        int totalWithoutInit = lotteryPrizeChanceModelRepository.countByConfigurationNumAndOpenIdAndUseStatusAndActiveStateAndBusinessTimeAndSourceNot(configurationNum, openId, MenberAvtivityConstants.LOTTERY_CHANCE_NOT_USE,MenberAvtivityConstants.LOTTERY_CHANCE_ACTIVE_STATE_Y, today, MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_2);
        _log.info("已用初始化次数" + useInitCount + "，每日初始化限制次数" + lotteryBaseConfigurationModel.getEveryDayLimit() + "，剩余初始化次数" + remainInitCount + "，除初始化剩余次数" + totalWithoutInit);
        return totalWithoutInit + (remainInitCount < existInitCount ? remainInitCount : existInitCount);
    }

    @Override
    public void bindAddress(Integer lotteryRecordId, Long addrId) {
        LotteryPrizeRecordModel one = lotteryPrizeRecordModelRepository.getOne(lotteryRecordId);
//        LotteryPrizeRecordModel one = lotteryPrizeRecordModelRepository.findOne(lotteryRecordId);
        one.setAddrId(addrId);
        one.setUpdateTime(new Timestamp(new Date().getTime()));
        lotteryPrizeRecordModelRepository.save(one);
    }

    @Override
    public Page<LotteryRecordVo> listLotteryRecordByBusinessTime(String configurationNum, String businessTime, Integer limit) {
        PropertiesConfigModel pc = propertiesConfigModelRepository.findFirstByPkeyEquals("JR_ZR_LOTTERY_RRECORDS_MAX_SHOW");
        if (pc != null && StringUtils.hasText(pc.getPvalue())) {
            limit = Integer.parseInt(pc.getPvalue());
        }
        // upgrade (hepeng)
//        Pageable page = new PageRequest(0, limit == null ? 100 : limit);
        Pageable page = PageRequest.of(0, limit == null ? 100 : limit);
        Page<LotteryPrizeRecordModel> rstPage = lotteryPrizeRecordModelRepository.findAllByConfigurationNumAndBusinessTimeOrderByCreateTimeDesc(configurationNum, businessTime, page);
        LotteryRecordVo vo;
        //MemUserWXBaseInfoModel memUserWXBaseInfoModel;
        //MemUserInfoModel memUserInfoModel;
        List<LotteryRecordVo> contentVoList = new ArrayList<>();
        for (LotteryPrizeRecordModel row : rstPage.getContent()) {
            if (!MenberAvtivityConstants.LOTTERY_TYPE_1.equals(row.getPrizeType()) && !MenberAvtivityConstants.LOTTERY_TYPE_5.equals(row.getPrizeType())) {
                continue;
            }
            vo = new LotteryRecordVo();
            /*memUserWXBaseInfoModel = memUserWXBaseInfoModelRepository.findFirstByOpenidEquals(row.getOpenId());
            if (memUserWXBaseInfoModel != null) {
                vo.setMobile(memUserWXBaseInfoModel.getMobile());
                if (!StringUtils.hasText(vo.getMobile())) {
                    memUserInfoModel = memUserInfoModelRepository.findFirstByOpenidEquals(row.getOpenId());
                    if (memUserInfoModel != null) {
                        vo.setMobile(memUserInfoModel.getMobile());
                    }
                }
            }
            vo.setMobile(StrUtils.hidePartOfMobileNo(vo.getMobile()));*/
            vo.setPrizeName(row.getPrizeName());
            contentVoList.add(vo);
        }

        Page<LotteryRecordVo> pageVo = new PageImpl<>(contentVoList, page, rstPage.getTotalElements());
        return pageVo;
    }

    @Override
    public Page<LotteryRecordVo> listLotteryRecordByOpenId(String configurationNum, String openId, Integer limit) {
        PropertiesConfigModel pc = propertiesConfigModelRepository.findFirstByPkeyEquals("MY_LOTTERY_RRECORDS_MAX_SHOW");
        if (pc != null && StringUtils.hasText(pc.getPvalue())) {
            limit = Integer.parseInt(pc.getPvalue());
        }
//        Pageable page = new PageRequest(0, limit == null ? 100 : limit);
        Pageable page = PageRequest.of(0, limit == null ? 100 : limit);
        Page<LotteryPrizeRecordModel> rstPage = lotteryPrizeRecordModelRepository.findAllByConfigurationNumAndOpenIdOrderByCreateTimeDesc(configurationNum, openId, page);
        LotteryRecordVo vo;
        List<LotteryRecordVo> contentVoList = new ArrayList<>();
        for (LotteryPrizeRecordModel row : rstPage.getContent()) {
            if (MenberAvtivityConstants.LOTTERY_TYPE_6.equals(row.getPrizeType())) {
                continue;
            }

            vo = new LotteryRecordVo();
            vo.setId(row.getId());
            vo.setPrizeName(row.getPrizeName());
            vo.setPrizeValue(row.getPrizeValue());
            vo.setTime(TimeUtils.formatDate(row.getCreateTime(), "MM.dd HH:mm"));
            vo.setCardNo(row.getCardNo());
            vo.setCardPwd(row.getCardPwd());
            if (row.getCardId() != null) {
                MemMarktingLuckCardlistModel cardInfo = memMarktingLuckCardlistRepository.getOne(row.getCardId());
//                MemMarktingLuckCardlistModel cardInfo = memMarktingLuckCardlistRepository.findOne(row.getCardId());
                if (cardInfo != null) {
                    vo.setCardNo(cardInfo.getCardNo());
                    vo.setCardPwd(cardInfo.getCardPwd());
                }
            }
            vo.setPrizeType(row.getPrizeType());
            vo.setPrizeTypeTxt(MenberAvtivityConstants.LOTTERY_TYPE_1.equals(row.getPrizeType()) ? "实物" : (MenberAvtivityConstants.LOTTERY_TYPE_2.equals(row.getPrizeType()) ? "金币" : (MenberAvtivityConstants.LOTTERY_TYPE_3.equals(row.getPrizeType()) ? "积分" : (MenberAvtivityConstants.LOTTERY_TYPE_4.equals(row.getPrizeType()) ? "优惠券" : (MenberAvtivityConstants.LOTTERY_TYPE_5.equals(row.getPrizeType()) ? "兑换券" : (MenberAvtivityConstants.LOTTERY_TYPE_6.equals(row.getPrizeType()) ? "谢谢参与" : (MenberAvtivityConstants.LOTTERY_TYPE_7.equals(row.getPrizeType()) ? "赠送次数" : (MenberAvtivityConstants.LOTTERY_TYPE_8.equals(row.getPrizeType()) ? "现金" : "未知"))))))));
            if (row.getAddrId() != null) {
                /*MemUserAddressModel addrInfo = memUserAddressModelRepository.findOne(row.getAddrId());
                if (addrInfo != null) {
                    vo.setReceiver(addrInfo.getRealName());
                    vo.setReceiverMobile(addrInfo.getMobile());
                    String addr = "";
                    MemRegionModel region;
                    if (StringUtils.hasText(addrInfo.getProvince())) {
                        region = memRegionModelRepository.findFirstByRegionId(addrInfo.getProvince().trim());
                        if (region != null) {
                            addr += region.getName();
                        }
                    }
                    if (StringUtils.hasText(addrInfo.getCity())) {
                        region = memRegionModelRepository.findFirstByRegionId(addrInfo.getCity().trim());
                        if (region != null) {
                            addr += region.getName();
                        }
                    }
                    if (StringUtils.hasText(addrInfo.getAddress())) {
                        addr += addrInfo.getAddress().trim();
                    }
                    vo.setReceiverAddress(addr);
                }*/
            }

            contentVoList.add(vo);
        }

        Page<LotteryRecordVo> pageVo = new PageImpl<>(contentVoList, page, rstPage.getTotalElements());
        return pageVo;
    }

    private Predicate createBaseConfigurationRecordQueryCondition(Root<LotteryBaseConfigurationModel> root, CriteriaQuery<?> query, CriteriaBuilder cb, LotterSearchParams paramBody){
        List<Predicate> list = new ArrayList<Predicate>();
        list.add(cb.equal(root.get("useStatus").as(Integer.class), MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE));

        if(!StringUtils.isEmpty(paramBody.getActivityName())){
            list.add(cb.like(root.get("activityName").as(String.class), "%" + paramBody.getActivityName().replace("_", "\\_").replace("+", "\\+") + "%"));
        }
        Predicate[] p = new Predicate[list.size()];
        Predicate res = cb.and(list.toArray(p));
        query.where(res);
        return query.getRestriction();
    }

    private List<LotteryBaseConfigurationVo> memoryValueToViewObject(List<LotteryBaseConfigurationModel> records){
        List<LotteryBaseConfigurationVo> list = new ArrayList<LotteryBaseConfigurationVo>();
        for (LotteryBaseConfigurationModel entity:records){
            LotteryBaseConfigurationVo viewObject = new LotteryBaseConfigurationVo();
            viewObject.setConfigurationNum(entity.getConfigurationNum());
            viewObject.setActivityName(entity.getActivityName());
            viewObject.setEffectiveStartTime(entity.getEffectiveStartTime());
            viewObject.setEffectiveEndTime(entity.getEffectiveEndTime());
            viewObject.setEveryDayLimit(entity.getEveryDayLimit());
            viewObject.setEveryDayFree(entity.getEveryDayFree());
            viewObject.setCostGoldNumber(entity.getCostGoldNumber());
            viewObject.setActivityRule(entity.getActivityRule());
            viewObject.setOpenStatus(entity.getOpenStatus());
            viewObject.setType(entity.getType());
            viewObject.setCreateTime(TimeUtils.getLongDateStr(entity.getCreateTime()));
            list.add(viewObject);
        }
        return list;
    }

    private List<LotteryRecordVo> lotteryRecordMemoryValueToViewObject(List<LotteryPrizeRecordModel> records){
        List<LotteryRecordVo> list = new ArrayList<LotteryRecordVo>();
        for (LotteryPrizeRecordModel entity:records){
            LotteryRecordVo viewObject = new LotteryRecordVo();
            viewObject.setId(entity.getId());
            /*MemUserWXBaseInfoModel memUserWXBaseInfoModel = memUserWXBaseInfoModelRepository.findFirstByOpenidEquals(entity.getOpenId());
            if (memUserWXBaseInfoModel != null) {
                viewObject.setMobile(memUserWXBaseInfoModel.getMobile());
                if (!StringUtils.hasText(viewObject.getMobile())) {
                    MemUserInfoModel memUserInfoModel = memUserInfoModelRepository.findFirstByOpenidEquals(entity.getOpenId());
                    if (memUserInfoModel != null) {
                        viewObject.setMobile(memUserInfoModel.getMobile());
                    }
                }
            }*/
            viewObject.setNickName(entity.getNickName());
            viewObject.setActivityName(entity.getActivityName());
            viewObject.setPrizeName(entity.getPrizeName());
            viewObject.setTime(TimeUtils.getLongDateStr(entity.getCreateTime()));
            viewObject.setPrizeType(entity.getPrizeType());
            viewObject.setPrizeTypeTxt(MenberAvtivityConstants.LOTTERY_TYPE_1.equals(entity.getPrizeType()) ? "实物" : (MenberAvtivityConstants.LOTTERY_TYPE_2.equals(entity.getPrizeType()) ? "金币" : (MenberAvtivityConstants.LOTTERY_TYPE_3.equals(entity.getPrizeType()) ? "积分" : (MenberAvtivityConstants.LOTTERY_TYPE_4.equals(entity.getPrizeType()) ? "优惠券" : (MenberAvtivityConstants.LOTTERY_TYPE_5.equals(entity.getPrizeType()) ? "兑换券" : (MenberAvtivityConstants.LOTTERY_TYPE_6.equals(entity.getPrizeType()) ? "谢谢参与" : (MenberAvtivityConstants.LOTTERY_TYPE_7.equals(entity.getPrizeType()) ? "赠送次数" : (MenberAvtivityConstants.LOTTERY_TYPE_8.equals(entity.getPrizeType()) ? "现金" : "未知"))))))));
            viewObject.setCardNo(entity.getCardNo());
            if (entity.getCardId() != null) {
                MemMarktingLuckCardlistModel cardInfo = memMarktingLuckCardlistRepository.getOne(entity.getCardId());
//                MemMarktingLuckCardlistModel cardInfo = memMarktingLuckCardlistRepository.findOne(entity.getCardId());
                if (cardInfo != null) {
                    viewObject.setCardNo(cardInfo.getCardNo());
                }
            }
            if (MenberAvtivityConstants.LOTTERY_TYPE_4.equals(entity.getPrizeType()) && StringUtils.hasText(viewObject.getCardNo()) && viewObject.getCardNo().length() > 2) {
                viewObject.setCardNo(viewObject.getCardNo().substring(0, 2) + "*******");
            }
            viewObject.setCardPwd("*********");

            if (entity.getAddrId() != null) {
               /* MemUserAddressModel addrInfo = memUserAddressModelRepository.findOne(entity.getAddrId());
                if (addrInfo != null) {
                    viewObject.setReceiver(addrInfo.getRealName());
                    viewObject.setReceiverMobile(addrInfo.getMobile());
                    String addr = "";
                    MemRegionModel region;
                    if (StringUtils.hasText(addrInfo.getProvince())) {
                        region = memRegionModelRepository.findFirstByRegionId(addrInfo.getProvince().trim());
                        if (region != null) {
                            addr += region.getName();
                        }
                    }
                    if (StringUtils.hasText(addrInfo.getCity())) {
                        region = memRegionModelRepository.findFirstByRegionId(addrInfo.getCity().trim());
                        if (region != null) {
                            addr += region.getName();
                        }
                    }
                    if (StringUtils.hasText(addrInfo.getAddress())) {
                        addr += addrInfo.getAddress().trim();
                    }
                    viewObject.setReceiverAddress(addr);
                }*/
            }
            if ("1".equals(entity.getExchangeStatus())) { //奖品兑付状态 1:已兑换 2:未兑换
                viewObject.setExchangeStatus("已兑换");
                viewObject.setExchangeInformation(entity.getExchangeInformation());
            }else{
                viewObject.setExchangeStatus("未兑换");
                viewObject.setExchangeInformation("");
            }
            list.add(viewObject);
        }
        return list;
    }

    private void exChangePrize(LotteryBaseConfigurationModel lotteryBaseConfigurationModel, LotteryPrizeChanceModel lotteryPrizeChanceModel, LotteryPrizeConfigurationModel matchPrize, LotteryPrizeResultVo lotteryPrizeResult, String memUid, String openId, String today) {
        LotteryPrizeRecordModel lotteryPrizeRecordModel = new LotteryPrizeRecordModel();
        lotteryPrizeRecordModel.setConfigurationNum(lotteryBaseConfigurationModel.getConfigurationNum());
        lotteryPrizeRecordModel.setChanceId(lotteryPrizeChanceModel.getChanceId());
        lotteryPrizeRecordModel.setOpenId(openId);
        lotteryPrizeRecordModel.setSource(lotteryPrizeChanceModel.getSource());
        lotteryPrizeRecordModel.setBusinessTime(today);
        lotteryPrizeRecordModel.setType(lotteryBaseConfigurationModel.getType());
        lotteryPrizeRecordModel.setPrizeNumber(matchPrize.getPrizeNumber());
        lotteryPrizeRecordModel.setPrizeName(matchPrize.getPrizeName());
        lotteryPrizeRecordModel.setPrizeIcon(matchPrize.getPrizeIcon());
        lotteryPrizeRecordModel.setPrizeType(matchPrize.getPrizeType());
        lotteryPrizeRecordModel.setCreateTime(new Timestamp(new java.util.Date().getTime()));
        lotteryPrizeRecordModel.setActivityName(lotteryBaseConfigurationModel.getActivityName());
        lotteryPrizeRecordModel.setPrizeId(matchPrize.getId());
        lotteryPrizeRecordModel.setWinTime(matchPrize.getWinTime());
        lotteryPrizeRecordModel.setWinTimeValue(matchPrize.getWinTimeValue());
        lotteryPrizeRecordModel.setWinType(matchPrize.getWinType());
        lotteryPrizeRecordModel.setPrizeProbability(matchPrize.getPrizeProbability());
        lotteryPrizeRecordModel.setInfinityTotal(matchPrize.getInfinityTotal());

        //返回封装信息
        LotteryPrizeInformation prizeInformation = new LotteryPrizeInformation();
        lotteryPrizeResult.setStatus(0);
        lotteryPrizeResult.setPrizeInformation(prizeInformation);
        prizeInformation.setPrizeNumber(matchPrize.getPrizeNumber());
        prizeInformation.setPrizeName(matchPrize.getPrizeName());
        prizeInformation.setPrizeIcon(matchPrize.getPrizeIcon());
        prizeInformation.setPrizeType(matchPrize.getPrizeType());
        prizeInformation.setPrizeLocationIndex(matchPrize.getPrizeLocationIndex());

        //处理昵称信息
       /* MemUserWXBaseInfoModel memUserWXBaseInfoModel = memUserWXBaseInfoModelRepository.findFirstByOpenidEquals(openId);
        if (memUserWXBaseInfoModel != null) {
            lotteryPrizeRecordModel.setNickName(memUserWXBaseInfoModel.getNickName());
            prizeInformation.setMobile(memUserWXBaseInfoModel.getMobile());
            if (!StringUtils.hasText(prizeInformation.getMobile())) {
                MemUserInfoModel memUserInfoModel = memUserInfoModelRepository.findFirstByOpenidEquals(openId);
                if (memUserInfoModel != null) {
                    prizeInformation.setMobile(memUserInfoModel.getMobile());
                }
            }
        }*/
        //prizeInformation.setMobile(StrUtils.hidePartOfMobileNo(prizeInformation.getMobile()));
        prizeInformation.setActivityName(lotteryBaseConfigurationModel.getActivityName());

        // 每天首次参加每日抽奖默认赠送[50]金币
        if (MenberAvtivityConstants.LOTTERY_TYPE_CJ0001.equals(lotteryBaseConfigurationModel.getType())) {
            int todayUserCount = lotteryPrizeRecordModelRepository.countByConfigurationNumAndOpenIdAndBusinessTime(matchPrize.getConfigurationNum(), openId, TimeUtils.getDateStr(new Date()));
            if(todayUserCount <= 0) {
                //asyncService.addcoin(memUid, MenberAvtivityConstants.SOURCE_EVERYDAY_LOTTERY_TASK, null, null);
            }
        }

        String typeDesc = MenberAvtivityConstants.LOTTERY_TYPE_CJ0004.equals(lotteryBaseConfigurationModel.getType()) ? "天天抽锦鲤"
                : (MenberAvtivityConstants.LOTTERY_TYPE_CJ0003.equals(lotteryBaseConfigurationModel.getType()) ? "五一抽奖"
                : (MenberAvtivityConstants.LOTTERY_TYPE_CJ0002.equals(lotteryBaseConfigurationModel.getType()) ? "元旦抽奖" : "每日抽奖"));

        //执行兑换过程
        String prizeType = matchPrize.getPrizeType(), prizeValue = "";
        MemMarktingLuckCardlistModel cardInfo = null;
        _log.info("开始兑换 用户编码" + memUid + " 奖品类型" + prizeType + " 兑换记录编码");
        switch (prizeType) {
            case MenberAvtivityConstants.LOTTERY_TYPE_1: // 1 实物
                break;
            case MenberAvtivityConstants.LOTTERY_TYPE_2: // 2 金币
                Integer coin = randomPrizeIntegerValue(matchPrize.getPrizeValue());
                //asyncService.addcoin(memUid,  MenberAvtivityConstants.SOURCE_EVERYDAY_LOTTERY_WIN, coin, typeDesc);
                lotteryPrizeRecordModel.setExchangeInformation(coin + " 金币");
                prizeValue = String.valueOf(coin);
                break;
            case MenberAvtivityConstants.LOTTERY_TYPE_3: // 3 积分
                Integer point = randomPrizeIntegerValue(matchPrize.getPrizeValue());
                //asyncService.addpoint(memUid, MenberAvtivityConstants.POINT_LOTTERY, point, typeDesc, null);
                lotteryPrizeRecordModel.setExchangeInformation(point + " 积分");
                prizeValue = String.valueOf(point);
                break;
            case MenberAvtivityConstants.LOTTERY_TYPE_4: // 4 优惠券
                break;
            case MenberAvtivityConstants.LOTTERY_TYPE_5: // 5 兑换券
                String now = TimeUtils.getLongDateStr();
                cardInfo = memMarktingLuckCardlistRepository.findFirstByPrizeNumberAndStatusAndStartTimeLessThanAndEndTimeGreaterThanOrderByCteateTimeAsc(matchPrize.getPrizeNumber(), 0, now, now);
                if (cardInfo != null) {
                    prizeInformation.setCardNo(cardInfo.getCardNo());
                    prizeInformation.setCardPwd(cardInfo.getCardPwd());
                    prizeInformation.setCardStartTime(cardInfo.getStartTime());
                    prizeInformation.setCardEndTime(cardInfo.getEndTime());
                    lotteryPrizeRecordModel.setCardNo(cardInfo.getCardNo());
                    lotteryPrizeRecordModel.setCardPwd(cardInfo.getCardPwd());
                    lotteryPrizeRecordModel.setCardId(cardInfo.getId()); // 关联卡密ID
                } else {
                    _log.error("兑换券库存不足");
                }
                break;
            case MenberAvtivityConstants.LOTTERY_TYPE_7: // 7 赠送次数
                Integer time = randomPrizeIntegerValue(matchPrize.getPrizeValue());
                // 增加抽奖次数
                for (int i = 1; i <= time; i++) {
                    LotteryPrizeChanceModel prizeChanceModel = new LotteryPrizeChanceModel();
                    prizeChanceModel.setChanceId(StringsUtil.getUUID32());
                    prizeChanceModel.setConfigurationNum(lotteryBaseConfigurationModel.getConfigurationNum());
                    prizeChanceModel.setOpenId(openId);
                    prizeChanceModel.setUseStatus(MenberAvtivityConstants.LOTTERY_CHANCE_NOT_USE); // 未使用
                    prizeChanceModel.setSource(MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_3); // 九宫格抽中
                    prizeChanceModel.setBusinessTime(TimeUtils.getDateStr(new Date()));
                    prizeChanceModel.setActiveState(MenberAvtivityConstants.LOTTERY_CHANCE_ACTIVE_STATE_Y); // 有效的
                    lotteryPrizeChanceModelRepository.saveAndFlush(prizeChanceModel);
                }
                lotteryPrizeRecordModel.setExchangeInformation(time + " 次");
                prizeValue = String.valueOf(time);
                break;
            case MenberAvtivityConstants.LOTTERY_TYPE_8: // 8 现金
                BigDecimal money = randomPrizeBigDecimalValue(matchPrize.getPrizeValue());
                //asyncService.addmoney(memUid, MenberAvtivityConstants.MONEY_LOTTERY_MATCH, null, money, typeDesc);
                lotteryPrizeRecordModel.setExchangeInformation(money + " 元");
                prizeValue = String.valueOf(money);
                break;
            default :
                break;
        }

        prizeInformation.setPrizeValue(prizeValue);
        lotteryPrizeRecordModel.setPrizeValue(prizeValue);

        // 保存中奖记录
        lotteryPrizeRecordModel.setExchangeStatus("1");
        lotteryPrizeRecordModel = lotteryPrizeRecordModelRepository.saveAndFlush(lotteryPrizeRecordModel);
        prizeInformation.setId(lotteryPrizeRecordModel.getId());
        prizeInformation.setTime(TimeUtils.formatDate(lotteryPrizeRecordModel.getCreateTime(), "MM.dd HH:mm"));
        prizeInformation.setPrizeTypeTxt(MenberAvtivityConstants.LOTTERY_TYPE_1.equals(prizeType) ? "实物" : (MenberAvtivityConstants.LOTTERY_TYPE_2.equals(prizeType) ? "金币" : (MenberAvtivityConstants.LOTTERY_TYPE_3.equals(prizeType) ? "积分" : (MenberAvtivityConstants.LOTTERY_TYPE_4.equals(prizeType) ? "优惠券" : (MenberAvtivityConstants.LOTTERY_TYPE_5.equals(prizeType) ? "兑换券" : (MenberAvtivityConstants.LOTTERY_TYPE_6.equals(prizeType) ? "谢谢参与" : (MenberAvtivityConstants.LOTTERY_TYPE_7.equals(prizeType) ? "赠送次数" : (MenberAvtivityConstants.LOTTERY_TYPE_8.equals(prizeType) ? "现金" : "未知"))))))));
        prizeInformation.setExchangeStatus(lotteryPrizeRecordModel.getExchangeStatus().equals("1") ? "已兑换" : "未兑换");
        prizeInformation.setExchangeInformation(lotteryPrizeRecordModel.getExchangeInformation());

        if (cardInfo != null) {
            // 设置兑换券为已使用
            cardInfo.setStatus(1);
            cardInfo.setWinId(lotteryPrizeRecordModel.getId().longValue());
            memMarktingLuckCardlistRepository.save(cardInfo);

            // 通知中奖人
            if (matchPrize.getNotifyUser() == 1 && StringUtils.hasText(matchPrize.getSmsTpl())) {
                _log.info("异步发送短信");
                String sms = matchPrize.getSmsTpl();
                sms = sms.replace("{card}", cardInfo.getCardNo());
                sms = sms.replace("{pass}", cardInfo.getCardPwd());
                sms = sms.replace("{startTime}", cardInfo.getStartTime());
                sms = sms.replace("{endtime}", cardInfo.getEndTime());
                //asyncService.sendSms(openId, sms);
            }
        }
    }

    private LotteryPrizeConfigurationModel randomMatch(LotteryBaseConfigurationModel lotteryBaseConfigurationModel, List<LotteryPrizeConfigurationModel> prizes, String openId) {
        LotteryPrizeConfigurationModel matchPrize = null;
        List<LotteryPrizeConfigurationModel> manCountList = new ArrayList<>(); // 按人数奖品列表
        List<LotteryPrizeConfigurationModel> timeCountList = new ArrayList<>(); // 按次数奖品列表
        List<LotteryPrizeConfigurationModel> randomList = new ArrayList<>(); // 按概率奖品列表
        for (LotteryPrizeConfigurationModel prize : prizes) { // 中奖设置，1=人数，2=次数，3=概率
            if (prize.getWinType() == 1) {
                manCountList.add(prize);
            }
            if (prize.getWinType() == 2) {
                timeCountList.add(prize);
            }
            if (prize.getWinType() == 3) {
                randomList.add(prize);
            }
        }

        // 优先级：人数>次数>概率
        matchPrize = getFormulaPrize(lotteryBaseConfigurationModel, manCountList, openId); // 按人数匹配
        if (matchPrize == null) { // 按次数匹配
            matchPrize = getFormulaPrize(lotteryBaseConfigurationModel, timeCountList, openId);
        }
        if (matchPrize == null) { // 随机匹配
            matchPrize = getRandomPrize(randomList);
        }
        if (matchPrize == null) { // 未能匹配，则去无库存时设置的奖品
            matchPrize = lotteryPrizeConfiguration.findByConfigurationNumAndPrizeNumberAndUseStatus(lotteryBaseConfigurationModel.getConfigurationNum(), lotteryBaseConfigurationModel.getOutoffStockPrizeCode(), MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
        }
        return matchPrize;
    }

    /**
     * 比如按照人数,那我这次抽奖的是第200人,公式为100N+5. 用200/100=2,那公式的值为: 100*2+5 = 205, 205!=200
     */
    private LotteryPrizeConfigurationModel getFormulaPrize(LotteryBaseConfigurationModel lotteryBaseConfigurationModel, List<LotteryPrizeConfigurationModel> prizeList, String openId) {
        LotteryPrizeConfigurationModel matchPrize = null;
        Date now = TimeUtils.parseDate(TimeUtils.formatDate(new Date(), "yyyyMMdd"), "yyyyMMdd");
        Date[] dates;
        Integer[] nums;
        Integer prizePeriod = lotteryBaseConfigurationModel.getPrizePeriod(); // 库存周期，1=永久，2=日
        int count = 0;
        for (LotteryPrizeConfigurationModel prize : prizeList) {
            if (prize.getWinTime() == 2) { // 中奖固定时间，1=每日，2=每月最后N天
                _log.info("中奖时间为每月最后" + prize.getWinTimeValue() + "天");
                dates = TimeUtils.getLastNDayOfMonth(now, prize.getWinTimeValue() - 1);
                _log.info("中奖时间范围：" + TimeUtils.formatDate(dates[0], "yyyy-MM-dd") + "~" + TimeUtils.formatDate(dates[1], "yyyy-MM-dd"));
                if (now.before(dates[0]) && !now.equals(dates[0])) { // 不在中奖日期
                    _log.info("不在中奖日期");
                    continue;
                }
            }

            if (prize.getWinType() == 1) {
                if (prizePeriod == 2) {// 库存周期，1=永久，2=日
                    if (lotteryPrizeRecordModelRepository.countByConfigurationNumAndPrizeNumberAndBusinessTimeAndWinTypeAndOpenId(lotteryBaseConfigurationModel.getConfigurationNum(), prize.getPrizeNumber(), TimeUtils.getDateStr(new Date()), prize.getWinType(), openId) > 0) {
                        _log.info("库存周期为日，当天已经中过该奖品，不能再中：" + prize.getPrizeName());
                        continue;
                    }
                } else {
                    if (lotteryPrizeRecordModelRepository.countByConfigurationNumAndPrizeNumberAndWinTypeAndOpenId(lotteryBaseConfigurationModel.getConfigurationNum(), prize.getPrizeNumber(), prize.getWinType(), openId) > 0) {
                        _log.info("库存周期为永久，活动期间已经中过该奖品，不能再中：" + prize.getPrizeName());
                        continue;
                    }
                }
            }

            if (prize.getWinType() == 1) {// 中奖设置，1=人数，2=次数，3=概率
                _log.info("中奖设置为人数");
                LotteryPrizeRecordModel lotteryPrizeRecordModel = null;
                if (prizePeriod == 2) {// 库存周期，1=永久，2=日
                    _log.info("库存周期为日");
                    lotteryPrizeRecordModel = lotteryPrizeRecordModelRepository.findFirstByConfigurationNumAndBusinessTimeAndOpenIdOrderByCreateTimeAsc(lotteryBaseConfigurationModel.getConfigurationNum(), TimeUtils.getDateStr(new Date()), openId);
                    if (lotteryPrizeRecordModel == null) {
                        _log.info("活动期间-今天，没有抽过奖");
                        count = lotteryPrizeRecordModelRepository.countByConfigurationNumAndBusinessTimeDistOpenid(lotteryBaseConfigurationModel.getConfigurationNum(), TimeUtils.getDateStr(new Date()));
                    } else {
                        _log.info("活动期间-今天，抽过奖");
                        count = lotteryPrizeRecordModelRepository.countByConfigurationNumAndBusinessTimeAndCreateTimeLessThanDistOpenid(lotteryBaseConfigurationModel.getConfigurationNum(), TimeUtils.getDateStr(new Date()), lotteryPrizeRecordModel.getCreateTime());
                    }
                } else {
                    _log.info("库存周期为永久");
                    lotteryPrizeRecordModel = lotteryPrizeRecordModelRepository.findFirstByConfigurationNumAndOpenIdOrderByCreateTimeAsc(lotteryBaseConfigurationModel.getConfigurationNum(), openId);
                    if (lotteryPrizeRecordModel == null) {
                        _log.info("活动期间，没有抽过奖");
                        count = lotteryPrizeRecordModelRepository.countByConfigurationNumDistOpenid(lotteryBaseConfigurationModel.getConfigurationNum());
                    } else {
                        _log.info("活动期间，抽过奖");
                        count = lotteryPrizeRecordModelRepository.countByConfigurationNumAndCreateTimeLessThanDistOpenid(lotteryBaseConfigurationModel.getConfigurationNum(), lotteryPrizeRecordModel.getCreateTime());
                    }
                }
            } else if (prize.getWinType() == 2) {
                _log.info("中奖设置为次数");
                if (prizePeriod == 2) {// 库存周期，1=永久，2=日
                    _log.info("库存周期为日");
                    count = lotteryPrizeRecordModelRepository.countByConfigurationNumAndBusinessTime(lotteryBaseConfigurationModel.getConfigurationNum(), TimeUtils.getDateStr(new Date()));
                } else {
                    _log.info("库存周期为永久");
                    count = lotteryPrizeRecordModelRepository.countByConfigurationNum(lotteryBaseConfigurationModel.getConfigurationNum());
                }
            } else {
                _log.error("概率另外获取");
                continue;
            }

            count += 1;
            _log.info("第" + count + "人/次，" + prize.getPrizeProbability());
            nums = parseNumsFromFormula(prize.getPrizeProbability());
            _log.info("倍数为：" + nums[0] + ", 加数为：" + nums[1]);
            int N = count / nums[0];
            N = N <= 0 ? 1 : N;
            int calcNum = N * nums[0] + nums[1];
            _log.info("按中奖公式计算得到：" + calcNum);
            if (calcNum == count) { // 比如按照人数,那我这次抽奖的是第200人,公式为100N+5. 用200/100=2,那公式的值为: 100*2+5 = 205, 205!=200
                matchPrize = prize;
                break;
            }
        }
        return matchPrize;
    }

    /**
     * 解析公式
     */
    private Integer[] parseNumsFromFormula(String formula) {
        Integer[] nums = new Integer[]{0, 0};
        if (StringUtils.hasText(formula)) {
            formula = formula.trim();
            try {
                if (formula.contains("N+")) {
                    String[] parts = formula.split("N\\+");
                    nums[0] = Integer.parseInt(parts[0]);
                    nums[1] = Integer.parseInt(parts[1]);
                } else {
                    nums[0] = Integer.parseInt(formula.replace("N", ""));
                }
            } catch (Exception e) {
                _log.error("解析中奖公式出错", e);
            }
        } else {
            _log.error("中奖公式为空");
        }
        return nums;
    }

    /**
     * 根据Math.random()产生一个double型的随机数，判断每个奖品出现的概率
     * @return random：奖品列表prizes中的序列（prizes中的第random个就是抽中的奖品）
     */
    private LotteryPrizeConfigurationModel getRandomPrize(List<LotteryPrizeConfigurationModel> randomList) {
        if (randomList == null || randomList.isEmpty()) {
            return null;
        }

        int random = -1;
        try {
            //计算总权重
            double sumWeight = 0;
            for (LotteryPrizeConfigurationModel p : randomList) {
                sumWeight += Float.parseFloat(p.getPrizeProbability());
            }
            //产生随机数
            double randomNumber = Math.random();

            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;
            for (int i = 0; i < randomList.size(); i++) {
                d2 += Double.parseDouble(randomList.get(i).getPrizeProbability()) / sumWeight;
                if (i == 0) {
                    d1 = 0;
                } else {
                    d1 += Double.parseDouble(randomList.get(i - 1).getPrizeProbability()) / sumWeight;
                }
                if (randomNumber >= d1 && randomNumber <= d2) {
                    random = i;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("生成抽奖随机数出错，出错原因：" + e.getMessage());
        }

        LotteryPrizeConfigurationModel matchPrize = random < 0 ? null : randomList.get(random);
        if (matchPrize != null && matchPrize.getWinTime() == 2) { // 中奖固定时间，1=每日，2=每月最后N天
            Date now = TimeUtils.parseDate(TimeUtils.formatDate(new Date(), "yyyyMMdd"), "yyyyMMdd");
            _log.info("中奖时间为每月最后" + matchPrize.getWinTimeValue() + "天");
            Date[] dates = TimeUtils.getLastNDayOfMonth(now, matchPrize.getWinTimeValue() - 1);
            _log.info("中奖时间范围：" + TimeUtils.formatDate(dates[0], "yyyy-MM-dd") + "~" + TimeUtils.formatDate(dates[1], "yyyy-MM-dd"));
            if (now.before(dates[0]) && !now.equals(dates[0])) { // 不在中奖日期
                _log.info("不在中奖日期");
                matchPrize = null;
            }
        }

        return matchPrize;
    }

    /**
     * 1.支持多个值,以英文逗号隔开,如:3,5,7;那么中了该奖再随机中其中一个值
     * 2.支持区间,以中线连接,如: 5-10,0.1-0.25;整数时为随机5到10其中一个数值, 小数点时需要乘以10的n次方判断开始和结束值是否为整数,随机出一个值再除以10的n次方
     */
    private static Integer randomPrizeIntegerValue(String prizeValueStr) {
        List<Integer> values = new ArrayList<>();
        if (prizeValueStr.contains(",")) {
            String[] parts = prizeValueStr.split(",");
            for (String part : parts) {
                try {
                    values.add(Integer.parseInt(part));
                } catch (Exception e) {
                    _log.error("解析奖品值异常", e);
                }
            }
        } else if (prizeValueStr.contains("-")) {
            String[] parts = prizeValueStr.split("-");
            Integer start = 0, end = 0, tmp;
            try {
                start = Integer.parseInt(parts[0]);
            } catch (Exception e) {
                _log.error("解析奖品值异常", e);
            }
            try {
                end = Integer.parseInt(parts[1]);
            } catch (Exception e) {
                _log.error("解析奖品值异常", e);
            }

            if (start > end) {
                tmp = end;
                end  = start;
                start = tmp;
            }

            for (int i = start; i <= end; i++) {
                values.add(i);
            }
        } else {
            values.add(Integer.parseInt(prizeValueStr));
        }

        int size = values.size();
        if (size == 0) {
            return 0;
        }
        int random = (int)(Math.random() * values.size());
        return values.get(random);
    }

    /**
     * 1.支持多个值,以英文逗号隔开,如:3,5,7;那么中了该奖再随机中其中一个值
     * 2.支持区间,以中线连接,如: 5-10,0.1-0.25;整数时为随机5到10其中一个数值, 小数点时需要乘以10的n次方判断开始和结束值是否为整数,随机出一个值再除以10的n次方
     */
    private static BigDecimal randomPrizeBigDecimalValue(String prizeValueStr) {
        List<BigDecimal> values = new ArrayList<>();
        if (prizeValueStr.contains(",")) {
            String[] parts = prizeValueStr.split(",");
            for (String part : parts) {
                try {
                    values.add(new BigDecimal(part));
                } catch (Exception e) {
                    _log.error("解析奖品值异常", e);
                }
            }
        } else if (prizeValueStr.contains("-")) {
            String[] parts = prizeValueStr.split("-");
            int idx = 0;
            if (parts[idx].contains(".") && !parts[idx].endsWith(".")) {
                while(parts[idx].endsWith("0")) {
                    parts[idx] = parts[idx].substring(0, parts[idx].length() - 1);
                }
            }
            idx = 1;
            if (parts[idx].contains(".") && !parts[idx].endsWith(".")) {
                while(parts[idx].endsWith("0")) {
                    parts[idx] = parts[idx].substring(0, parts[idx].length() - 1);
                }
            }

            BigDecimal start = new BigDecimal(parts[0]), end = new BigDecimal(parts[1]), tmp;
            if (start.compareTo(end) > 0) {
                tmp = end;
                end = start;
                start = tmp;
            }

            int decimalCount = start.scale() > end.scale() ? start.scale() : end.scale();
            BigDecimal step = decimalCount > 0 ? new BigDecimal("1").divide(new BigDecimal(decimalCount * 10)) : new BigDecimal("1");
            for (BigDecimal bd = start; bd.compareTo(end) <= 0; bd = bd.add(step)) {
                values.add(bd);
            }
        } else {
            values.add(new BigDecimal(prizeValueStr));
        }

        int size = values.size();
        if (size == 0) {
            return new BigDecimal("0");
        }
        int random = (int)(Math.random() * values.size());
        return values.get(random);
    }

    private Predicate createLotteryRecordQueryCondition(Root<LotteryPrizeRecordModel> root, CriteriaQuery<?> query, CriteriaBuilder cb, LotterResultSearchParamsDto paramBody){
        List<Predicate> list = new ArrayList<Predicate>();
        if (paramBody.getId() != null) {
            list.add(cb.equal(root.get("id").as(Integer.class), paramBody.getId()));
        }
        if(!StringUtils.isEmpty(paramBody.getNickName())){
            list.add(cb.like(root.get("nickName").as(String.class), "%" + paramBody.getNickName().replace("_", "\\_").replace("+", "\\+") + "%"));
        }

        if(!StringUtils.isEmpty(paramBody.getActivityName())){
            list.add(cb.like(root.get("activityName").as(String.class), "%" + paramBody.getActivityName().replace("_", "\\_").replace("+", "\\+") + "%"));
        }

        if(!StringUtils.isEmpty(paramBody.getPrizeName())){
            list.add(cb.like(root.get("prizeName").as(String.class), "%" + paramBody.getPrizeName().replace("_", "\\_").replace("+", "\\+") + "%"));
        }

        if(!StringUtils.isEmpty(paramBody.getType())){
            list.add(cb.equal(root.get("type").as(String.class), paramBody.getType()));
        }

        if(!StringUtils.isEmpty(paramBody.getPrizeType())){
            list.add(cb.equal(root.get("prizeType").as(Integer.class), paramBody.getPrizeType()));
        }

        if(!StringUtils.isEmpty(paramBody.getStartTime())){
            String startTime = TimeUtils.getLongDateStr(Long.parseLong(paramBody.getStartTime()));
            list.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class),startTime));
        }

        if(!StringUtils.isEmpty(paramBody.getEndTime())){
            String endTime = TimeUtils.getLongDateStr(Long.parseLong(paramBody.getEndTime()));
            list.add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class),endTime));
        }

        if(!StringUtils.isEmpty(paramBody.getCardNo())){
            list.add(cb.like(root.get("cardNo").as(String.class), "%" + paramBody.getCardNo() + "%"));
        }

        if(!StringUtils.isEmpty(paramBody.getCardPwd())){
            list.add(cb.like(root.get("cardPwd").as(String.class), "%" + paramBody.getCardPwd() + "%"));
        }

        Predicate[] p = new Predicate[list.size()];
        Predicate res = cb.and(list.toArray(p));
        query.where(res);
        return query.getRestriction();
    }

    private boolean checkDbIsExists(List<LotteryPrizeConfigurationModel> lastPrizeConfigurationList,LotterPrizeConfigurationDto prizeConfigurationDto){
        boolean mark = false;
        for(LotteryPrizeConfigurationModel prizeConfigurationModel: lastPrizeConfigurationList){
            if(prizeConfigurationModel.getPrizeNumber().equals(prizeConfigurationDto.getPrizeNumber())){
                mark = true;
                break;
            }
        }
        return mark;
    }

    @Override
    public void addQuestionnaireLotteryChance(String openId, String type) {
        LotteryBaseConfigurationModel lotteryBaseConfigurationModel = lotteryBaseConfiguration.getFirstByTypeAndUseStatus(type,MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
        if(lotteryBaseConfigurationModel != null){
            LotteryPrizeChanceModel prizeChanceModel = new LotteryPrizeChanceModel();
            prizeChanceModel.setChanceId(StringsUtil.getUUID32());
            prizeChanceModel.setConfigurationNum(lotteryBaseConfigurationModel.getConfigurationNum());
            prizeChanceModel.setOpenId(openId);
            prizeChanceModel.setUseStatus(MenberAvtivityConstants.LOTTERY_CHANCE_NOT_USE);//未使用
            prizeChanceModel.setSource(MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_1);//每日赠送
            prizeChanceModel.setBusinessTime(TimeUtils.getDateStr(new Date()));
            prizeChanceModel.setActiveState(MenberAvtivityConstants.LOTTERY_CHANCE_ACTIVE_STATE_Y);//有效的
            lotteryPrizeChanceModelRepository.saveAndFlush(prizeChanceModel);
        }
    }

    @Override
    public int addBigScreenLotteryChance(String openId) {
        LotteryBaseConfigurationModel lotteryBaseConfigurationModel = lotteryBaseConfiguration.getFirstByTypeAndUseStatus("CJ0001", MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
        if(lotteryBaseConfigurationModel != null) {
            int max = 10;
            PropertiesConfigModel pc = propertiesConfigModelRepository.findFirstByPkeyEquals("MAX_SHARE_ADD_FREE_LOTTERY");
            if (pc != null && pc.getPvalue().trim().length() > 0) {
                max = Integer.parseInt(pc.getPvalue().trim());
            }
            int bsExistCount = lotteryPrizeChanceModelRepository.countByConfigurationNumAndOpenIdAndBusinessTimeAndSource(lotteryBaseConfigurationModel.getConfigurationNum(), openId, TimeUtils.getDateStr(new Date()),MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_4);
            if (bsExistCount < max) {
                LotteryPrizeChanceModel prizeChanceModel = new LotteryPrizeChanceModel();
                prizeChanceModel.setChanceId(StringsUtil.getUUID32());
                prizeChanceModel.setConfigurationNum(lotteryBaseConfigurationModel.getConfigurationNum());
                prizeChanceModel.setOpenId(openId);
                prizeChanceModel.setUseStatus(MenberAvtivityConstants.LOTTERY_CHANCE_NOT_USE);//未使用
                prizeChanceModel.setSource(MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_4);//70寸大屏活动
                prizeChanceModel.setBusinessTime(TimeUtils.getDateStr(new Date()));
                prizeChanceModel.setActiveState(MenberAvtivityConstants.LOTTERY_CHANCE_ACTIVE_STATE_Y);//有效的
                lotteryPrizeChanceModelRepository.saveAndFlush(prizeChanceModel);
                return max - bsExistCount - 1;
            }
        }
        return 0;
    }

    @Override
    public int remainBigScreenLotteryChance(String openId) {
        LotteryBaseConfigurationModel lotteryBaseConfigurationModel = lotteryBaseConfiguration.getFirstByTypeAndUseStatus("CJ0001", MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
        if(lotteryBaseConfigurationModel != null) {
            int max = 10;
            PropertiesConfigModel pc = propertiesConfigModelRepository.findFirstByPkeyEquals("MAX_SHARE_ADD_FREE_LOTTERY");
            if (pc != null && pc.getPvalue().trim().length() > 0) {
                max = Integer.parseInt(pc.getPvalue().trim());
            }
            int bsExistCount = lotteryPrizeChanceModelRepository.countByConfigurationNumAndOpenIdAndBusinessTimeAndSource(lotteryBaseConfigurationModel.getConfigurationNum(), openId, TimeUtils.getDateStr(new Date()),MenberAvtivityConstants.LOTTERY_CHANCE_SOURCE_4);
            if (bsExistCount < max) {
                return max - bsExistCount;
            }
        }
        return 0;
    }

    @Override
    @Transactional
    public void updateActiveStatus(String type, String businessTime, String source) {
        LotteryBaseConfigurationModel lotteryBaseConfigurationModel = lotteryBaseConfiguration.getFirstByTypeAndUseStatus(type, MenberAvtivityConstants.DELETE_FLAG_EFFECTIVE);
        if(lotteryBaseConfigurationModel != null) {
            lotteryPrizeChanceModelRepository.updateActiveStatus(lotteryBaseConfigurationModel.getConfigurationNum(), businessTime, source);
        }
    }

    @Override
    public boolean checkType() {
        boolean check = false;
        List<Object[]> existList= lotteryBaseConfiguration.getLotteryBaseConfiguration();
        PropertiesConfigModel propertiesConfig= propertiesConfigModelRepository.findFirstByPkeyEquals("LOTTERY_CONFIGURE_TYPE");
        String value= propertiesConfig.getPvalue();
        List<LotteryConfigureTypeVo> list = new ArrayList<LotteryConfigureTypeVo>();
        JSONArray valueArray= JSON.parseArray(value);
        for (int i = 0; i <valueArray.size() ; i++) {
            JSONObject jsonObject= valueArray.getJSONObject(i);
            String type = jsonObject.getString("type");
            if(checkExist(existList,type)){
                continue;
            }
            LotteryConfigureTypeVo LotteryConfigureTypeVo =new LotteryConfigureTypeVo();
            LotteryConfigureTypeVo.setType(jsonObject.getString("type"));
            LotteryConfigureTypeVo.setName(jsonObject.getString("name"));
            list.add(LotteryConfigureTypeVo);
        }
        if(list.size()<=0){
            check=true;
        }
        return check;
    }

    public boolean checkExist(List<Object[]> list, String checkValue) {
        boolean exist = false;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object model = list.get(i);
                String type = String.valueOf(model);
                if (checkValue.equals(type)) {
                    exist = true;
                    break;
                }
            }
        }
        return exist;
    }
}
