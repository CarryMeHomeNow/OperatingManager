package com.tcl.uf.member.service.impl;

import com.tcl.commondb.member.model.LevelRuleConfigureModel;
import com.tcl.commondb.member.model.MemberRightsAndInterestsModel;
import com.tcl.commondb.member.repository.LevelRuleConfigureModelRepository;
import com.tcl.commondb.member.repository.MemberRightsAndInterestsModelRepository;
import com.tcl.uf.common.base.util.tangram.TangramRichTextUtil;
import com.tcl.uf.member.dto.LevelRuleConfigureDto;
import com.tcl.uf.member.service.MemberGradeService;
import com.tcl.uf.member.service.MemberLevelInfoService;
import com.tcl.uf.member.vo.LevelRuleConfigureVo;
import com.tcl.uf.member.vo.MemberVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class MemberLevelInfoServiceImpl implements MemberLevelInfoService {

	@Autowired
	private MemberRightsAndInterestsModelRepository memberRightsAndInterestsModelRepository;

	@Autowired
	private LevelRuleConfigureModelRepository LevelRuleConfigureModelRepository;
	
	@Autowired
	private MemberGradeService memberGradeService;

	@Override
	public MemberVo queryMemberLevelInfo(Long ssoid) {
		MemberRightsAndInterestsModel growthValueModel =  memberRightsAndInterestsModelRepository.findGrowthValueBySsoid(ssoid);
		MemberVo returnVo = new MemberVo();
		if(growthValueModel != null) {
			returnVo = memberGradeService.findGradeByGrowth(growthValueModel.getGrowthValue());
		}else {
			returnVo.setGrowValue("0");
			returnVo.setLevel("V0");
		}
		return returnVo;
	}

	@Override
	@Cacheable(value = "levelRule", key = "'ruleCache'")
	public LevelRuleConfigureVo saveOrUpdate(LevelRuleConfigureDto rule, String username) {
		LevelRuleConfigureModel levelRuleConfigureModel = null;
		if (rule.getId() == null) {
			levelRuleConfigureModel=new LevelRuleConfigureModel();
			levelRuleConfigureModel.setCreator(username);
			levelRuleConfigureModel.setCreateTime(new Date());
		} else {
			levelRuleConfigureModel = LevelRuleConfigureModelRepository.findFirstByOrderByCreateTimeDesc();
			levelRuleConfigureModel.setEditor(username);
			levelRuleConfigureModel.setUpdateTime(new Date());
		}
		levelRuleConfigureModel.setName(rule.getName());
		levelRuleConfigureModel.setContent(rule.getContent());
		levelRuleConfigureModel.setContentFormat(TangramRichTextUtil.toTangramJson(rule.getContent()));
		LevelRuleConfigureModelRepository.saveAndFlush(levelRuleConfigureModel);

		LevelRuleConfigureVo ruleConfigureVo= new LevelRuleConfigureVo();
		BeanUtils.copyProperties(levelRuleConfigureModel,ruleConfigureVo);
		return ruleConfigureVo;
	}

	@Override
	public LevelRuleConfigureVo getRule() {
		LevelRuleConfigureVo ruleConfigureVo = null;
		LevelRuleConfigureModel integralRuleModel= LevelRuleConfigureModelRepository.findFirstByOrderByCreateTimeDesc();
		if(integralRuleModel != null){
			ruleConfigureVo = new LevelRuleConfigureVo();
			BeanUtils.copyProperties(integralRuleModel,ruleConfigureVo);
		}
		return ruleConfigureVo;
	}
}
