package com.tcl.uf.member.service.impl;

import com.tcl.commondb.member.model.IntegralRuleModel;
import com.tcl.commondb.member.repository.IntegralRuleModelRepository;
import com.tcl.uf.common.base.util.tangram.TangramRichTextUtil;
import com.tcl.uf.member.dto.IntegralRuleConfigureDto;
import com.tcl.uf.member.service.IntegralRuleService;
import com.tcl.uf.member.vo.IntegralRuleConfigureVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IntegralRuleServiceImpl implements IntegralRuleService {

	@Autowired
	IntegralRuleModelRepository integralRuleModelRepository;

	@Override
	public IntegralRuleConfigureVo getRule() {
		IntegralRuleConfigureVo ruleConfigureVo = null;
		IntegralRuleModel integralRuleModel= integralRuleModelRepository.findFirstByOrderByCreateTimeDesc();
		if(integralRuleModel != null){
			ruleConfigureVo = new IntegralRuleConfigureVo();
			BeanUtils.copyProperties(integralRuleModel,ruleConfigureVo);
		}
		return ruleConfigureVo;
	}

	public IntegralRuleConfigureVo saveOrUpdate(IntegralRuleConfigureDto rule,String username) {
		IntegralRuleModel integralRuleModel=new IntegralRuleModel();
		if (rule.getId() == null) {
			integralRuleModel=new IntegralRuleModel();
			integralRuleModel.setCreator(username);
			integralRuleModel.setCreateTime(new Date());
		} else {
			integralRuleModel = integralRuleModelRepository.findFirstByOrderByCreateTimeDesc();
			integralRuleModel.setEditor(username);
			integralRuleModel.setUpdateTime(new Date());
		}
		integralRuleModel.setName(rule.getName());
		integralRuleModel.setContent(rule.getContent());
		integralRuleModel.setContentFormat(TangramRichTextUtil.toTangramJson(rule.getContent()));
		integralRuleModelRepository.saveAndFlush(integralRuleModel);

		IntegralRuleConfigureVo ruleConfigureVo= new IntegralRuleConfigureVo();
		BeanUtils.copyProperties(integralRuleModel,ruleConfigureVo);
		return ruleConfigureVo;
	}
}
