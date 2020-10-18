package com.tcl.uf.member.controller;

import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.member.dto.LevelRuleConfigureDto;
import com.tcl.uf.member.service.MemberLevelInfoService;
import com.tcl.uf.member.vo.LevelRuleConfigureVo;
import com.tcl.uf.member.vo.MemberVo;
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

@Api(value = "会员等级与成长值")
@RestController
@RequestMapping(value = "/memberLevelInfo")
public class MemberLevelInfoController extends AbstractBaseController {
	
	private static final Logger log = LoggerFactory.getLogger(MemberLevelInfoController.class);

	@Autowired
	private MemberLevelInfoService levelInfoService;
	@Autowired
	private HttpServletRequest servletRequest;
	
	@RequestMapping(value = "/app/queryMemberLevelInfo",method = RequestMethod.POST)
	@ApiOperation(value = "首页--查询会员等级与成长值" , notes="首页--查询会员等级与成长值接口(ssoid不能为空)",httpMethod = "POST")
	public ResponseData<MemberVo> queryMemberLevelInfo(HttpServletRequest request){
		TokenAppUserInfo TokenData = getAppUserInfo(request);
		MemberVo memberVo = new MemberVo();
		try {
			memberVo = levelInfoService.queryMemberLevelInfo(TokenData.getAccountId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询会员等级与成长值失败");
			return new ResponseData<>(0,"查询失败","query failed");
		}
		return new ResponseData<>(memberVo);
	}

	@RequestMapping(value = "/back/rule/configure/saveOrUpdate",method = RequestMethod.POST)
	@ApiOperation(value = "保存或跟新会员等级规则配置" , notes="会员等级规则配置",httpMethod = "POST")
	public ResponseData<LevelRuleConfigureVo> saveOrUpdate(@RequestBody LevelRuleConfigureDto params,HttpServletRequest request){
		TokenData tokenData = getManageUserInfo(request);
		LevelRuleConfigureVo levelRuleConfigureVo = null;
		try {
			levelRuleConfigureVo = levelInfoService.saveOrUpdate(params,tokenData.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存或跟新会员等级规则配置失败");
			return new ResponseData<>(0,"保存或跟新会员等级规则配置失败","");
		}
		return new ResponseData<LevelRuleConfigureVo>(levelRuleConfigureVo);
	}

	@RequestMapping(value = "/getLevelRule", method = RequestMethod.POST)
	@ApiOperation(value = "查询最新的会员规则配置" , notes="每查询最新的会员规则配置",httpMethod = "POST")
	public ResponseData<LevelRuleConfigureVo> getIntegralRule()
	{
        LevelRuleConfigureVo levelRuleConfigureVo = null;
		try {
			levelRuleConfigureVo = levelInfoService.getRule();
		} catch (Exception e) {
			log.error("查询发生异常",e);
			return new ResponseData<>(0,"查询失败","");
		}
		return new ResponseData<>(levelRuleConfigureVo);
	}

	/**
	 * 查询会员等级与成长值 内部接口
	 * @return
	 */
	@RequestMapping(value = "/tangram/queryMemberLevel")
	public MemberVo queryMemberLevel(){
//		TokenAppUserInfo TokenData = getAppUserInfo(servletRequest);
		MemberVo memberVo = new MemberVo();
		try {
			memberVo = levelInfoService.queryMemberLevelInfo(/*TokenData.getAccountId()*/(long) 159602111);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询会员等级与成长值失败");
			return memberVo;
		}
		return memberVo;
	}
}
