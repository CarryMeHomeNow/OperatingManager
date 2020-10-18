package com.tcl.commondb.points.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcl.commondb.points.model.PointSignInRecord;

public interface PointSignInRecordRepository extends JpaRepository<PointSignInRecord, Long> {

	PointSignInRecord findFirstByAccountIdOrderBySignTimeDesc(Long ssoid);

	@Query(value = "SELECT a.* "
			+ " FROM `point_sign_in_record` a WHERE a.sign_time >= ( SELECT sign_time "
			+ " FROM `point_sign_in_record` WHERE day_num = 1 and account_id = :ssoid " 
			+ " ORDER BY sign_time DESC LIMIT 1 ) and a.account_id = :ssoid ",nativeQuery = true)
	List<PointSignInRecord> querySignRecord(Long ssoid);

}
