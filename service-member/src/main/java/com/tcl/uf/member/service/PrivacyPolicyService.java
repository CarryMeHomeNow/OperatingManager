package com.tcl.uf.member.service;

import com.tcl.commondb.member.model.PrivacyPolicyModel;
import com.tcl.uf.member.vo.LinkConfigureVo;
import com.tcl.uf.member.vo.PrivacyPolicyVo;

public interface PrivacyPolicyService {

	/**
	 * 保存或者修改新的隐私政策配置
	 * @return
	 */
	PrivacyPolicyVo update(PrivacyPolicyModel privacyPolicyModel);
	/**
	 * 查询最新的隐私政策配置
	 * @return
	 */
	LinkConfigureVo getPrivacyPolicy();
}
