package com.tcl.uf.points.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tcl.commondb.points.model.PointCoupon;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.points.vo.PointCouponVo;

public interface PointCouponService {

	public Page<PointCoupon> listPage(Pageable pageable);

	public Long save(PointCouponVo pointCoupon);

	public Long update(PointCouponVo pointCoupon);

	public boolean del(Long id);

	public boolean updateStatus(Long id, Integer status);

	public boolean exchangeCoupon(String token, Long ssoid, Long pointCoupinId) throws JMException;

	public PointCouponVo findById(Long id);
}
