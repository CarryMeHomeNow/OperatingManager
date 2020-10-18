package com.tcl.uf.activity.controller;

import com.tcl.uf.activity.service.LotteryActivityService;
import com.tcl.uf.activity.vo.LotterNewsBroadcastVo;
import com.tcl.uf.activity.vo.LotteryBaseConfigurationH5Vo;
import com.tcl.uf.activity.vo.LotteryPrizeResultVo;
import com.tcl.uf.activity.vo.LotteryRecordVo;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.util.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/lottery/front")
@Api(description = "每日抽奖客户端功能")
public class LotteryActivityFrontController extends AbstractBaseController {

    Log _log = LogFactory.getLog(LotteryActivityFrontController.class);

    @Autowired
    private LotteryActivityService lotteryActivityService;

    @RequestMapping("/initPrize")
    @ApiOperation(value = "初始化抽奖盘奖品信息" , notes="初始化抽奖盘奖品信息",httpMethod = "GET")
    public ResponseData<LotteryBaseConfigurationH5Vo> initPrizeInformation(HttpServletRequest request,@RequestParam("type") String type){
        if(StringUtils.isEmpty(type)) {
            return new ResponseData<>(0,"type 参数值不能为空","");
        }
        TokenAppUserInfo tokenData = getAppUserInfo(request);
        LotteryBaseConfigurationH5Vo lotteryBaseConfigurationH5Vo = null;
        try {
            String openId = "";
            String memUid = "";
            //返回转盘奖品信息
            lotteryBaseConfigurationH5Vo=lotteryActivityService.findLotteryBaseConfigurationH5Detail(type,openId);
            if (lotteryBaseConfigurationH5Vo != null) {
                //增加抽奖机会
                lotteryActivityService.addDefaultLotteryChance(openId, memUid, type,lotteryBaseConfigurationH5Vo.getConfigurationNum());
                //查询当前用户剩余机会
                int chance = lotteryActivityService.countLotteryChance(lotteryBaseConfigurationH5Vo.getConfigurationNum(),openId);
                lotteryBaseConfigurationH5Vo.setChance(chance);
            }else{
                return new ResponseData<>(0,type.concat("活动类型下无任何有效配置"),"");
            }

        }catch (Exception e){
            _log.error("初始化抽奖盘奖品信息",e);
        }
        return new ResponseData<>(lotteryBaseConfigurationH5Vo);
    }

    @RequestMapping("/startLottery")
    @ApiOperation(value = "启动抽奖程序" , notes="根据对应配置启动对应抽奖程序",httpMethod = "GET")
    public ResponseData<LotteryPrizeResultVo> startLottery(HttpServletRequest request, HttpServletResponse reponse, @RequestParam("configurationNum") String configurationNum){
        if(StringUtils.isEmpty(configurationNum)) {
            return new ResponseData<>(0,"configurationNum 参数值不能为空","");
        }
        LotteryPrizeResultVo lotteryPrizeResultVo = null;
        try {
            String openId = "";
            String memUid = "";
            //执行抽奖
            lotteryPrizeResultVo = lotteryActivityService.startLottery(configurationNum,openId, memUid);
            //统计剩余抽奖机会
            int chance = lotteryActivityService.countLotteryChance(configurationNum, openId);
            lotteryPrizeResultVo.setChance(chance);
        } catch (Exception e) {
            _log.error("抽奖发生异常:",e);
        }
        return new ResponseData<>(lotteryPrizeResultVo);
    }

    @RequestMapping("/newsBroadcast")
    @ApiOperation(value = "中奖消息列表" , notes="查询中奖消息列表信息",httpMethod = "GET")
    public ResponseData<Object> newsBroadcast(HttpServletRequest request, HttpServletResponse reponse, @RequestParam("configurationNum") String configurationNum){
        List<LotterNewsBroadcastVo> lotterNewsBroadcastVo = null;
        try {
            lotterNewsBroadcastVo = lotteryActivityService.newsBroadcastList(configurationNum);
        } catch (Exception e) {
            _log.error("查询中奖消息列表信息发生异常", e);
        }
        return new ResponseData<>(lotterNewsBroadcastVo);
    }
    
    @RequestMapping("/find/configure")
    @ApiOperation(value = "初始化抽奖盘奖品信息" , notes="初始化抽奖盘奖品信息",httpMethod = "GET")
    public ResponseData<LotteryBaseConfigurationH5Vo> findConfigure(HttpServletRequest request, @RequestParam("type") String type){
        if(StringUtils.isEmpty(type)) {
            return new ResponseData<>(0,"type 参数值不能为空","");
        }
        LotteryBaseConfigurationH5Vo lotteryBaseConfigurationH5Vo = null;
        try {
            //返回转盘奖品信息
            lotteryBaseConfigurationH5Vo=lotteryActivityService.findLotteryBaseConfigurationH5Detail(type,null);
            if(lotteryBaseConfigurationH5Vo == null) {
            	return new ResponseData<>(0,type.concat(" 活动类型下无任何有效配置"),"");
            }
        }catch (Exception e){
            _log.error("初始化抽奖盘奖品信息",e);
        }
        return new ResponseData<>(lotteryBaseConfigurationH5Vo);
    }
    
    @RequestMapping("/bindaddr")
    @ApiOperation(value = "绑定收货地址" , notes="绑定收货地址",httpMethod = "GET")
    public ResponseData<Object> bindaddr(@RequestParam("resultId") Integer resultId, @RequestParam("addrId") Long addrId) {
        try {
            lotteryActivityService.bindAddress(resultId, addrId);
        } catch (Exception e) {
            _log.error("绑定收货地址发生异常", e);
        }
        return new ResponseData<>("绑定成功");
    }

    @RequestMapping("/yesterdayRecords")
    @ApiOperation(value = "昨日中奖" , notes="昨日中奖",httpMethod = "GET")
    public ResponseData<Object> yesterdayRecords(@RequestParam("configurationNum") String configurationNum, Integer limit) {
    	Page<LotteryRecordVo> rstPage = new PageImpl<>(new ArrayList<>());
        try {
            rstPage = lotteryActivityService.listLotteryRecordByBusinessTime(configurationNum, TimeUtils.addDay(new Date(), -1, "yyyy-MM-dd"), limit);
        } catch (Exception e) {
            _log.error("获取昨日中奖记录发生异常", e);
        }
        return new ResponseData<>(1, "查询成功", "", rstPage);
    }
    
    @RequestMapping("/todayRecords")
    @ApiOperation(value = "今日中奖" , notes="今日中奖",httpMethod = "GET")
    public ResponseData<Object> todayRecords(@RequestParam("configurationNum") String configurationNum, Integer limit) {
    	Page<LotteryRecordVo> rstPage = new PageImpl<>(new ArrayList<>());
        try {
            rstPage = lotteryActivityService.listLotteryRecordByBusinessTime(configurationNum, TimeUtils.getDateStr(new Date()), limit);
        } catch (Exception e) {
            _log.error("获取今日中奖记录发生异常", e);
        }
        return new ResponseData<>(1, "查询成功", "", rstPage);
    }
    
    @RequestMapping("/myRecords")
    @ApiOperation(value = "我的中奖" , notes="我的中奖",httpMethod = "GET")
    public ResponseData<Object> myRecords(HttpServletRequest request, @RequestParam("configurationNum") String configurationNum, Integer limit) {
    	Page<LotteryRecordVo> rstPage = new PageImpl<>(new ArrayList<>());
        try {
        	String openId = "";
            rstPage = lotteryActivityService.listLotteryRecordByOpenId(configurationNum, openId, limit);
        } catch (Exception e) {
            _log.error("获取我的中奖记录发生异常", e);
        }
        return new ResponseData<>(1, "查询成功", "", rstPage);
    }
}
