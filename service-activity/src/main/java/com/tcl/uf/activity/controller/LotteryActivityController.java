package com.tcl.uf.activity.controller;

import com.tcl.uf.activity.consts.MenberAvtivityConstants;
import com.tcl.uf.activity.dto.LotterPrizeConfigurationDto;
import com.tcl.uf.activity.dto.LotterResultSearchParamsDto;
import com.tcl.uf.activity.dto.LotterSearchParams;
import com.tcl.uf.activity.dto.LotteryBaseConfigurationDto;
import com.tcl.uf.activity.service.LotteryActivityService;
import com.tcl.uf.activity.vo.LotteryBaseConfigurationVo;
import com.tcl.uf.activity.vo.LotteryConfigureTypeVo;
import com.tcl.uf.activity.vo.LotteryRecordVo;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.util.http.HttpHeaderUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author youyun.xu
 * @ClassName: LotteryActivityController
 * @Description: 抽奖活动管理
 * @date 2020/7/30 9:15
 */
@RestController
@Api(description = "抽奖活动后台管理系统")
@RequestMapping("/lottery/back")
public class LotteryActivityController extends AbstractBaseController {

    private static final Logger _log = LoggerFactory.getLogger(LotteryActivityController.class);

    @Autowired
    private LotteryActivityService lotteryActivityService;

    @RequestMapping("/saveOrUpdate")
    @ApiOperation(value = "保存或修改抽奖配置", notes = "创建或修改抽奖配置接口(编码为空则新增,否则修改)", httpMethod = "POST")
    public ResponseData<Object> saveOrUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LotteryBaseConfigurationDto params) {
        try {
            if (params.getPrizeConfiguration() == null || params.getPrizeConfiguration().size() <= 0) {
                return new ResponseData(0, "保存失败，没有添加任何奖品", "");
            }
            boolean hasInfinityTotal = false;
            Integer sum = 0;
            LotterPrizeConfigurationDto outoffStockPrize = null;
            for (LotterPrizeConfigurationDto dto : params.getPrizeConfiguration()) {
                if (!hasInfinityTotal && dto.getInfinityTotal() != null && dto.getInfinityTotal() == 1) {
                    hasInfinityTotal = true;
                }
                if (dto.getWinType() == 3) { //概率
                    sum += Integer.parseInt(dto.getPrizeProbability());
                }
                if (outoffStockPrize == null && dto.getPrizeNumber().equals(params.getOutoffStockPrizeCode())) {
                    outoffStockPrize = dto;
                }
            }
            if (!hasInfinityTotal) {
                return new ResponseData(0, "保存失败-至少设置一个奖品库存为无限量", "");
            }
            if (!StringUtils.hasText(params.getOutoffStockPrizeCode()) || outoffStockPrize == null) {
                return new ResponseData(0, "保存失败，没有设置中了奖项没库存时获得的奖品", "");
            } else {
                if (MenberAvtivityConstants.LOTTERY_TYPE_5.equals(outoffStockPrize.getPrizeType())) {
                    return new ResponseData(0, "保存失败，兑换券不能设置为中了奖项没库存时获得的奖品", "");
                }
                if (outoffStockPrize.getWinTime() != 1) { // 中奖固定时间，1=每日，2=每月最后N天
                    return new ResponseData(0, "保存失败，奖项没库存时获得的奖品必须需要每日都能中", "");
                }
            }
            if (sum > 0 && sum != 100) {
                return new ResponseData(0, "保存失败，按概率中奖的奖品概率总和不等于100", "");
            }
            TokenData tokenData = getManageUserInfo(request);
            if (StringUtils.isEmpty(params.getConfigurationNum())) {
                lotteryActivityService.saveLotteryBaseConfiguration(params, tokenData.getUsername());
            } else {
                lotteryActivityService.updateLotteryBaseConfiguration(params, tokenData.getUsername());
            }
        } catch (Exception e) {
            _log.error("保存或修改抽奖配置发生异常 请求参数{} 异常信息{}", params, e);
            return new ResponseData(0, "保存失败", "");
        }
        return new ResponseData("保存成功");
    }

    @RequestMapping("/find/list")
    @ApiOperation(value = "抽奖列表查询接口", notes = "根据条件查询抽奖列表信息", httpMethod = "POST")
    public ResponseData<ListWithPage<LotteryBaseConfigurationVo>> findList(HttpServletRequest request, HttpServletResponse response, @RequestBody LotterSearchParams params) {
        ListWithPage<LotteryBaseConfigurationVo> list = null;
        TokenData tokenData = getManageUserInfo(request);
        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            list = lotteryActivityService.advancedSearchWithCondition(params, pageable, tokenData.getUsername());
        } catch (Exception e) {
            _log.error("抽奖列表查询接口发生异常 请求参数{} 异常信息{}", params, e);
        }
        return new ResponseData<ListWithPage<LotteryBaseConfigurationVo>>(list);
    }

    @RequestMapping("/find/detail")
    @ApiOperation(value = "查询抽奖单个配置详情接口", notes = "根据配配置编码查询抽奖详细信息", httpMethod = "GET")
    public ResponseData<LotteryBaseConfigurationVo> findDetail(HttpServletRequest request, HttpServletResponse response, @RequestParam("configurationNum") String configurationNum) {
        LotteryBaseConfigurationVo baseConfiguration = null;
        try {
            baseConfiguration = lotteryActivityService.findLotteryBaseConfigurationDetail(configurationNum);
        } catch (Exception e) {
            _log.error("查询抽奖单个配置详情接口发生异常 请求参数{} 异常信息{}", configurationNum, e);
        }
        return new ResponseData<LotteryBaseConfigurationVo>(baseConfiguration);
    }

    @RequestMapping("/result/list")
    @ApiOperation(value = "抽奖结果列表查询接口", notes = "根据条件查询抽奖结果列表信息", httpMethod = "POST")
    public ResponseData<ListWithPage<LotteryRecordVo>> resultList(HttpServletRequest request, HttpServletResponse response, @RequestBody LotterResultSearchParamsDto params) {
        ListWithPage<LotteryRecordVo> list = null;
        TokenData tokenData = getManageUserInfo(request);
        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            list = lotteryActivityService.advancedSearchLotteryResultWithCondition(params, pageable, tokenData.getUsername());
        } catch (Exception e) {
            _log.error("抽奖结果列表查询接口发生异常 请求参数{} 异常信息{}", params, e);
        }
        return new ResponseData<ListWithPage<LotteryRecordVo>>(list);
    }

    @RequestMapping("/configureType")
    @ApiOperation(value = "抽奖配置", notes = "根据条件查询抽奖结果列表信息", httpMethod = "POST")
    public ResponseData<List<LotteryConfigureTypeVo>> configureType(HttpServletRequest request, HttpServletResponse response) {
        List<LotteryConfigureTypeVo> LotteryconfigureTypeVo = new ArrayList<LotteryConfigureTypeVo>();
        try {
            LotteryconfigureTypeVo = lotteryActivityService.configureType();
        } catch (Exception e) {
            _log.error("抽奖配置接口发生异常 异常信息{}", e);
        }
        return new ResponseData<List<LotteryConfigureTypeVo>>(LotteryconfigureTypeVo);
    }

    @RequestMapping("/delete")
    @ApiOperation(value = "删除抽奖配置", notes = "根据配置编号删除对应抽奖配置", httpMethod = "POST")
    public ResponseData<Object> deleteLotteryConfigure(HttpServletRequest request, HttpServletResponse response, @RequestParam("configurationNum") String configurationNum) {
        try {
            lotteryActivityService.deleteLotteryConfigure(configurationNum);
        } catch (Exception e) {
            _log.error("删除抽奖配置接口发生异常 请求参数{} 异常信息{}", configurationNum, e);
            return new ResponseData<Object>(0, "删除抽奖配置失败", "");
        }
        return new ResponseData<Object>("删除成功");
    }
}
