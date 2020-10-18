package com.tcl.commondb.points.repository;

import com.tcl.commondb.points.model.PointCoupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PointCouponRepository extends JpaRepository<PointCoupon, Long> {
	
	PointCoupon findByIdEquals(Long id);

	Page<PointCoupon> findAllByIsDeleteIs(byte isDelete, Pageable pageable);

}
