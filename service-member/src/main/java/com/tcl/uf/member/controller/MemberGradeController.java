package com.tcl.uf.member.controller;

import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.member.dto.MemberGradeDto;
import com.tcl.uf.member.service.MemberGradeService;
import com.tcl.uf.member.vo.MemberGradeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author gaofan
 */
@Api("会员等级规则管理")
@RequestMapping("/grade")
@RestController
public class MemberGradeController extends AbstractBaseController{


    @Autowired
    private MemberGradeService memberGradeService;

    @ApiOperation(value = "会员等级规则列表信息",notes = "会员等级规则列表信息",httpMethod = "GET")
    @GetMapping("/admin/list")
    public ResponseData<List<MemberGradeVo>> findAllDetails() {
        List<MemberGradeVo> list = memberGradeService.findAllDetails();
        return new ResponseData<>(list);
    }

    @ApiOperation(value = "更新用户等级规则",notes = "更新用户等级规则",httpMethod = "POST")
    @PostMapping("/admin/update")
    public ResponseData update(@RequestBody MemberGradeDto memberGradeDto,HttpServletRequest request) {
        memberGradeService.saveMemberGrade(memberGradeDto, getManageUserInfo(request).getUsername());
        return new ResponseData();
    }

    @ApiOperation(value = "app端会员等级信息",notes = "app端会员等级信息",httpMethod = "GET")
    @GetMapping("/app/information")
    public ResponseData<Map<String,Object>> findMemberGradeMsg(HttpServletRequest request){
        Map<String, Object> map = memberGradeService.findMemberGradeMsg(getAppUserInfo(request).getAccountId());
        return new ResponseData<>(map);
    }


    @ApiOperation(value = "查询会员等级列表",notes = "查询会员等级列表",httpMethod = "GET")
    @GetMapping("/admin/grades")
    public ResponseData<List<String>> findGrades(){
        List<String> list = memberGradeService.findGrades();
        return new ResponseData<>(list);
    }
}
