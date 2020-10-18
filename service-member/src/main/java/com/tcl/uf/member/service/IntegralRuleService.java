package com.tcl.uf.member.service;

import com.tcl.uf.member.dto.IntegralRuleConfigureDto;
import com.tcl.uf.member.vo.IntegralRuleConfigureVo;

public interface IntegralRuleService {
	
	/**
	 * 保存或者修改新的积分规则配置
	 * @param rule
	 * @return
	 */
	IntegralRuleConfigureVo saveOrUpdate(IntegralRuleConfigureDto rule,String username);
	
	/**
	 * 查询最新的积分规则配置
	 * @return
	 */
	IntegralRuleConfigureVo getRule();
	
	

}
