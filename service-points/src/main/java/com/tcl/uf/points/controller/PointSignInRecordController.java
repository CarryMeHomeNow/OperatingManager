package com.tcl.uf.points.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcl.uf.common.base.constant.RequestHeader;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.points.service.PointSignInRecordService;
import com.tcl.uf.points.vo.SignInVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/signIn")
@Api(value = "app端签到模块")
public class PointSignInRecordController extends AbstractBaseController{
	
	@Autowired
	private PointSignInRecordService signInRecordService;
	
	@RequestMapping(value = "/app/isSignIn")
	@ApiOperation(value = "app端用户获取是否已签到", notes = "app端用户获取是否已签到", httpMethod = "GET")
	public ResponseData<Object> isSignIn(HttpServletRequest request){
		Long ssoid = getAppUserInfo(request).getAccountId();
		boolean flag = signInRecordService.isSignIn(ssoid);
		return success(flag);
	}
	
	@RequestMapping(value = "/app/save")
	@ApiOperation(value = "app端用户保存签到信息 ", notes = "app端用户保存签到信息", httpMethod = "GET")
	public ResponseData<Object> saveSignIn(HttpServletRequest request){
		Long ssoid = getAppUserInfo(request).getAccountId();
		String apptoken = request.getHeader(RequestHeader.APP_REQUEST_APTOKEN);
		Map<String, Object> map = signInRecordService.saveSignIn(ssoid,apptoken);
		boolean flag = Boolean.parseBoolean(String.valueOf(map.get("flag")));
		if(!flag) {
			return fail(0,String.valueOf(map.get("msg")));
		}
		return success();
	}
	
	@RequestMapping(value = "/app/querySignRecord")
	@ApiOperation(value = "app端用户获取签到信息列表", notes = "app端用户获取签到信息列表", httpMethod = "GET")
	public ResponseData<List<SignInVo>> querySignRecord(HttpServletRequest request){
		Long ssoid = getAppUserInfo(request).getAccountId();
		List<SignInVo> list = signInRecordService.querySignRecord(ssoid);
		return success(list);
	}
	 
	
}
