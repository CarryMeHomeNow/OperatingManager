package com.tcl.uf.member.service.impl;

import com.tcl.uf.member.service.MemberRightCalcService;
import org.springframework.stereotype.Service;

/**
 * @author youyun.xu
 * @ClassName: MemberRightCalcServiceImpl
 * @Description: 计算会员权益Service
 * @date 2020/8/20 11:22
 */
@Service("memberRightCalcService")
public class MemberRightCalcServiceImpl implements MemberRightCalcService {

    @Override
    public void addAllowanceRight() {
        System.out.print("Hello world addAllowanceRight");
    }

    @Override
    public void dealAddCouponRightFailure() {
        System.out.print("Hello world dealAddCouponRightFailure");
    }
}
