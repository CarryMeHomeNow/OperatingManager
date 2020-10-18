package com.tcl.commondb.member.repository;

import com.tcl.commondb.member.model.IntegralRuleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegralRuleModelRepository extends JpaRepository<IntegralRuleModel, Integer> {

	/**
	 * 查询积分规则
	 * 
	 * @return
	 */
	IntegralRuleModel findFirstByOrderByCreateTimeDesc();
}
