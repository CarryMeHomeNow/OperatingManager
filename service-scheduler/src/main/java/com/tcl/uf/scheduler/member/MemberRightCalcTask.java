package com.tcl.uf.scheduler.member;

import com.tcl.commonservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MemberRightCalcTask {

    @Autowired
    private MemberService memberService;

    /**
     * 定时任务调度会员权益计算(根据等级每月定时发放全品类津贴权益)
     * @return void
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void addCouponRightTask() {
        memberService.addAllowanceRight();
    }

    /**
     * 定时任务调度会员权益计算(根据等级每月定时发放全品类津贴权益"失败场景")
     * @return void
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void dealCouponRightFailureTask() {
        memberService.dealAddCouponRightFailure();
    }
}
