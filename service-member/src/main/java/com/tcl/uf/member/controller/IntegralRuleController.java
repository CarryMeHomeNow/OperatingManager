package com.tcl.uf.member.controller;

import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.member.dto.IntegralRuleConfigureDto;
import com.tcl.uf.member.service.IntegralRuleService;
import com.tcl.uf.member.vo.IntegralRuleConfigureVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(description = "会员玩法-积分规则配置")
@RequestMapping(value = "/integral/rule")
public class IntegralRuleController  extends AbstractBaseController {

	private static final Logger log = LoggerFactory.getLogger(IntegralRuleController.class);

	@Autowired
	private IntegralRuleService integralRuleService;

	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	@ApiOperation(value = "保存或修改会员规则配置" , notes="新增或修改会员规则配置接口(id为空则新增,否则修改)",httpMethod = "POST")
	public ResponseData<IntegralRuleConfigureVo> saveOrUpdate(@RequestBody IntegralRuleConfigureDto rule, HttpServletRequest request) {
		TokenData tokenData = getManageUserInfo(request);
		IntegralRuleConfigureVo integralRule = null;
		try {
            integralRule = integralRuleService.saveOrUpdate(rule,tokenData.getUsername());
		} catch (Exception e) {
			log.error("保存或修改会员规则配置发生异常",e);
            return new ResponseData<>(0,"保存失败",e.getMessage());
		}
		return new ResponseData<IntegralRuleConfigureVo>(integralRule);
	}

	@RequestMapping(value = "/getIntegralRule", method = RequestMethod.POST)
	@ApiOperation(value = "查询最新的会员规则配置" , notes="每查询最新的会员规则配置",httpMethod = "POST")
	public ResponseData<IntegralRuleConfigureVo> getIntegralRule()
	{
        IntegralRuleConfigureVo integralRuleModel = null;
		try {
            integralRuleModel = integralRuleService.getRule();
		} catch (Exception e) {
			log.error("查询发生异常",e);
            return new ResponseData<>(0,"查询失败","");
		}
		return new ResponseData<>(integralRuleModel);
	}
}
