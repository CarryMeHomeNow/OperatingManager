package com.tcl.uf.member.service;

import com.tcl.uf.member.dto.LevelRuleConfigureDto;
import com.tcl.uf.member.vo.LevelRuleConfigureVo;
import com.tcl.uf.member.vo.MemberVo;

public interface MemberLevelInfoService {

	MemberVo queryMemberLevelInfo(Long ssoid);

	LevelRuleConfigureVo saveOrUpdate(LevelRuleConfigureDto rule, String username);

	LevelRuleConfigureVo getRule();
}
