package com.tcl.commonservice.service;

import com.tcl.commonservice.service.dto.MemberRightsSet;
import com.tcl.commonservice.service.vo.AfterSaleConfigureVo;
import com.tcl.commonservice.service.vo.IntegralRuleConfigureVo;
import com.tcl.commonservice.service.vo.MemberVo;
import com.tcl.uf.common.base.dto.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("service-member")
public interface MemberService {

    /**
     * 定时任务调度会员权益计算(根据等级每月定时发放全品类津贴权益)
     * @return void
     */
    @RequestMapping(value = "/member/right/calc/allowance/right", method = RequestMethod.GET)
    ResponseData<Object> addAllowanceRight();

    /**
     * 定时任务调度会员权益计算(根据等级每月定时发放全品类津贴权益"失败场景")
     * @return void
     */
    @RequestMapping(value = "/member/right/calc/allowance/right/failure", method = RequestMethod.GET)
    ResponseData<Object> dealAddCouponRightFailure();

    /**
     * 查询所有权益信息
     * @return
     */
    @RequestMapping("/right/app/detail")
    ResponseData<List<MemberRightsSet>> findAll();

    /**
     * 根据ID查询权益信息
     * @param id 权益id
     * @return
     */
    @RequestMapping("/right/tangram/admin")
    MemberRightsSet findRightSet(@RequestParam("id") Integer id);

    /**
     * 会员等级与成长值
     * @return
     */
    @RequestMapping("/memberLevelInfo/tangram/queryMemberLevel")
    MemberVo queryMemberLevel();

    /**
     * 定时任务调度会员权益计算(根据等级每月定时发放全品类津贴权益)
     * @return void
     */
    @RequestMapping("/after/sale/configure/list")
    ResponseData<List<AfterSaleConfigureVo>> findAfterSaleConfigureList();

    /**
     * 成长值管理-添加成长值
     * @return ResponseData<Object>
     */
    //@RequestMapping(value = "/growth/operational/add", method = RequestMethod.POST)
    //ResponseData<Object> add(@RequestBody GrowthValueDto growthValueDto);

    /**
     * 成长值管理-减少成长值
     * @return ResponseData<Object>
     */
    //@RequestMapping(value = "/growth/operational/add", method = RequestMethod.POST)
    //ResponseData<Object> subtract(@RequestBody GrowthValueDto growthValueDto);

    /**
     * 成长值管理-重置个人成长值
     * @return ResponseData<Object>
     */
    //@RequestMapping(value = "/growth/operational/reset", method = RequestMethod.GET)
    //ResponseData<Object> reset(Long ssoid);

    /**
     * 会员规则
     * @return
     */
    @RequestMapping("/integral/rule/getIntegralRule")
    ResponseData<IntegralRuleConfigureVo> getIntegralRule();
}
