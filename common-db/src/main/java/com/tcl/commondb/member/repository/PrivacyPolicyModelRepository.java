package com.tcl.commondb.member.repository;

import com.tcl.commondb.member.model.PrivacyPolicyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface PrivacyPolicyModelRepository extends JpaRepository<PrivacyPolicyModel, Integer> {

	/**
	 * 查询TCL+的隐私政策
	 * 
	 * @return
	 */
	PrivacyPolicyModel findFirstByOrderByUpdateTimeDesc();

	/**
	 * 更新隐私政策
	 * 
	 * @param content
	 * @param updateTime
	 */
	@Modifying
	@Query("UPDATE PrivacyPolicyModel a SET a.privacyPolicy = :privacyPolicy,a.updateTime = :updateTime,updateBy = :updateBy where id = :id")
	void updatePrivacyPolicy(@Param("privacyPolicy") String privacyPolicy, @Param("updateTime") Date updateTime,
			 @Param("updateBy") String updateBy, @Param("id") Integer id);


}
