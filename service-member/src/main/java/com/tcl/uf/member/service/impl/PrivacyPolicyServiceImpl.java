package com.tcl.uf.member.service.impl;

import com.tcl.commondb.member.model.LinkConfigureModel;
import com.tcl.commondb.member.model.PrivacyPolicyModel;
import com.tcl.commondb.member.repository.LinkConfigureModelRepository;
import com.tcl.commondb.member.repository.PrivacyPolicyModelRepository;
import com.tcl.uf.member.service.PrivacyPolicyService;
import com.tcl.uf.member.vo.LinkConfigureVo;
import com.tcl.uf.member.vo.PrivacyPolicyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PrivacyPolicyServiceImpl implements PrivacyPolicyService {

	private static final Logger log = LoggerFactory.getLogger(PrivacyPolicyServiceImpl.class);

	@Autowired
	private PrivacyPolicyModelRepository repository;

	@Autowired
	private LinkConfigureModelRepository linkConfigureModelRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	@CachePut(value = "privacyPolicy", key = "'privacyPolicyCache'")
	public PrivacyPolicyVo update(PrivacyPolicyModel privacyPolicyModel) {
		privacyPolicyModel.setCreateBy("chenhongjun").setUpdateBy("chenhongjun");
		PrivacyPolicyVo model = new PrivacyPolicyVo();
		privacyPolicyModel.setUpdateTime(new Date());
		if (privacyPolicyModel.getId() != null) {
			repository.updatePrivacyPolicy(privacyPolicyModel.getPrivacyPolicy(), new Date(),
					privacyPolicyModel.getUpdateBy(), privacyPolicyModel.getId());
			model = privacyPolicyModelToVo(privacyPolicyModel);
		} else {
			model = privacyPolicyModelToVo(repository.saveAndFlush(privacyPolicyModel));
		}
		return model;
	}

	@Override
	public LinkConfigureVo getPrivacyPolicy() {
		LinkConfigureModel linkConfigureModel= linkConfigureModelRepository.findFirstByType("1");
		if(linkConfigureModel == null){
			return null;
		}
		LinkConfigureVo linkConfigureVo =new LinkConfigureVo();
		BeanUtils.copyProperties(linkConfigureModel,linkConfigureVo);
		return linkConfigureVo;
	}

	/**
	 * Vo与实体的转换
	 * @return
	 */
	private PrivacyPolicyVo privacyPolicyModelToVo(PrivacyPolicyModel privacyPolicy) {
		PrivacyPolicyVo privacyPolicyVo = new PrivacyPolicyVo();
		privacyPolicyVo.setPrivacyPolicy(privacyPolicy.getPrivacyPolicy())
				.setId(privacyPolicy.getId())
				.setUpdateBy(privacyPolicy.getUpdateBy())
				.setUpdateTime(privacyPolicy.getUpdateTime());
		return privacyPolicyVo;

	}
}
