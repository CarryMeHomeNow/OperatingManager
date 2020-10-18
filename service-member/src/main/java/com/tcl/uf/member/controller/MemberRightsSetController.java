package com.tcl.uf.member.controller;

import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.member.dto.MemberRightsSetDto;
import com.tcl.uf.member.service.MemberRightsSetService;
import com.tcl.uf.member.vo.MemberRightsSetVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author gaofan
 */
@Api("会员权益管理")
@RestController
@RequestMapping("/right")
public class MemberRightsSetController extends AbstractBaseController {

    @Autowired
    private MemberRightsSetService memberRightsSetService;

    @ApiOperation(value = "查询所有权益信息",notes = "查询所有权益信息",httpMethod = "GET")
    @GetMapping("/app/detail")
    public ResponseData<List<MemberRightsSetVo>> findAll() {
        List<MemberRightsSetVo> data = memberRightsSetService.findAll();
        return new ResponseData<>(data);
    }

    @ApiOperation(value = "新增(修改)权益信息", notes = "新增(修改)权益信息", httpMethod = "POST")
    @PostMapping("/admin")
    public ResponseData insertMemberRights(@RequestBody MemberRightsSetDto memberRightsSetDto, HttpServletRequest request) {
        memberRightsSetService.insertMemberRights(memberRightsSetDto,getManageUserInfo(request).getUsername());
        return new ResponseData();
    }

    @ApiOperation(value = "根据权益ID查询权益信息",notes = "根据权益ID查询权益信息",httpMethod = "GET")
    @GetMapping("/admin/{id}")
    public ResponseData<MemberRightsSetVo> findById(@PathVariable(value = "id") Integer id) {
        MemberRightsSetVo data = memberRightsSetService.findById(id);
        return new ResponseData<>(data);
    }

    @ApiOperation(value = "根据权益Id删除权益", notes = "根据权益Id删除权益", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable(value = "id") Integer id, HttpServletRequest request) {
        memberRightsSetService.delete(id,getManageUserInfo(request).getUsername());
        return new ResponseData();
    }

}
