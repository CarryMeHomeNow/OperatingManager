package com.tcl.uf.member.controller;

import com.tcl.commondb.member.model.PrivacyPolicyModel;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.member.service.PrivacyPolicyService;
import com.tcl.uf.member.vo.LinkConfigureVo;
import com.tcl.uf.member.vo.PrivacyPolicyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "TCL+隐私政策配置")
@RestController
@RequestMapping("/privacyPolicy")
public class PrivacyPolicyController {
	
	
	private static final Logger log = LoggerFactory.getLogger(PrivacyPolicyController.class);
	
	@Autowired
	private PrivacyPolicyService service;
	
	/**
	 * 更新或插入隐私政策规则
	 * @param privacyPolicyModel
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	@ApiOperation(value = "保存或修改隐私政策规则配置" , notes="新增或修改隐私政策规则配置接口(id为空则新增,否则修改)",httpMethod = "POST")
	public ResponseData<PrivacyPolicyVo> saveOrUpdate(@RequestBody PrivacyPolicyModel privacyPolicyModel) {
		PrivacyPolicyVo privacyPolicy = new PrivacyPolicyVo();
		try {
			privacyPolicy = service.update(privacyPolicyModel);
		} catch (Exception e) {
			log.error("保存或修改隐私政策规则发生异常",e);
            return new ResponseData<>(0,"保存失败","save failed");
		}
		return new ResponseData<>(privacyPolicy);
	}
	
	/**
	 * 获取最新的隐私政策规则
	 * @return
	 */
	@RequestMapping(value = "/getPrivacyPolicy")
	@ApiOperation(value = "查询最新的隐私政策规则配置" , notes="每次查询只返回最新一条数据",httpMethod = "POST")
	public ResponseData<LinkConfigureVo> getPrivacyPolicy(HttpServletRequest request)
	{
		LinkConfigureVo privacyPolicyModel = null;
		try {
			privacyPolicyModel = service.getPrivacyPolicy();
		} catch (Exception e) {
			log.error("查询发生异常",e);
            return new ResponseData<>(0,"查询失败","query failed");
		}
		return new ResponseData<>(privacyPolicyModel);
	}
}
