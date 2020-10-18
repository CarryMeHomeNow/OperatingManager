package com.tcl.commondb.points.repository;

import com.tcl.commondb.points.model.PointCoupon;
import com.tcl.commondb.points.model.PointCouponExchangeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointCouponExchangeRecordRepository extends JpaRepository<PointCouponExchangeRecord, Long> {
    List<PointCouponExchangeRecord> findAllByAccountIdEqualsAndPointCouponIdEquals(Long accountId, Long pointCouponId);
}
