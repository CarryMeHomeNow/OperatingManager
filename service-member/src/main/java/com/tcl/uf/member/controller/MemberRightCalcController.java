package com.tcl.uf.member.controller;

import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.member.service.MemberRightCalcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youyun.xu
 * @ClassName: MemberRightSchedulerController
 * @Description: 会员权益计算调度任务
 * @date 2020/8/20 10:59
 */
@Api(value = "权益管理调度器(优惠券、积分、...)")
@RestController
@RequestMapping(value = "/member/right/calc")
public class MemberRightCalcController {

    @Autowired
    private MemberRightCalcService memberRightCalcService;

    @RequestMapping(value = "/allowance/right", method = RequestMethod.GET)
    @ApiOperation(value = "根据等级每月定时发放全品类津贴权益" , notes="根据等级每月定时发放全品类津贴权益",httpMethod = "GET")
    public ResponseData<Object> addAllowanceRight()
    {
        try {
            memberRightCalcService.addAllowanceRight();
        } catch (Exception e) {
            return new ResponseData<>(0,"执行失败","");
        }
        return new ResponseData<>("执行完毕");
    }

    @RequestMapping(value = "/allowance/right/failure", method = RequestMethod.GET)
    @ApiOperation(value = "定时任务调度会员津贴权益计算(优惠券权益发放失败场景)" , notes="定时任务调度会员权益计算(优惠券权益发放失败场景)",httpMethod = "GET")
    public ResponseData<Object> dealAddCouponRightFailure()
    {
        try {
            memberRightCalcService.dealAddCouponRightFailure();
        } catch (Exception e) {
            return new ResponseData<>(0,"执行失败","");
        }
        return new ResponseData<>("执行完毕");
    }
}
