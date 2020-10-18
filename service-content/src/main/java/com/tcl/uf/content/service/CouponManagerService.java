package com.tcl.uf.content.service;

import com.tcl.commonservice.service.vo.OfficialMallCouponReponseDataVo;
import com.tcl.uf.common.base.ex.ProcessControlException;

public interface CouponManagerService {

    OfficialMallCouponReponseDataVo findCouponList(String currentPage, String pageSize, String couponTypeName, String collectType, String cansend, String startTime, String endTime) throws ProcessControlException;

    String sendCoupon(String ssoids,String couponTypeUuid) throws ProcessControlException;
}
