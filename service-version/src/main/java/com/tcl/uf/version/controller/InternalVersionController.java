package com.tcl.uf.version.controller;

import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.version.dto.InternalVersionDto;
import com.tcl.uf.version.dto.InternalVersionParams;
import com.tcl.uf.version.service.InternalVersionService;
import com.tcl.uf.version.vo.InternalVersionDetailsVo;
import com.tcl.uf.version.vo.InternalVersionOptionVo;
import com.tcl.uf.version.vo.InternalVersionTabVo;
import com.tcl.uf.version.vo.InternalVersionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Api(value = "应用内部版本管理")
@RestController
@RequestMapping("/internal/version")
public class InternalVersionController extends AbstractBaseController {

    private static final Logger _log = LoggerFactory.getLogger(InternalVersionController.class);

    @Autowired
    private InternalVersionService internalVersionService;

    @RequestMapping(value = "/back/saveOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "新增或修改内部版本", notes = "新增或修改内部版本", httpMethod = "POST")
    public ResponseData<Object> saveOrUpdate(@RequestBody InternalVersionDto articleContentDto,HttpServletRequest request) {
        Long id = null;
        TokenData info = getManageUserInfo(request);
        try {
            id = internalVersionService.saveOrUpdate(articleContentDto,info.getUsername());
        } catch (Exception e) {
            _log.info("新增或修改内部版本发生异常 请求参数 {} 异常信息 {}",articleContentDto, e);
            return new ResponseData<Object>(0,"保存失败","");
        }
        return new ResponseData<Object>(id);
    }

    @RequestMapping(value = "/back/info/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查看内部版本详情", notes = "查看内部版本详情", httpMethod = "GET")
    public ResponseData<InternalVersionDetailsVo> findArticle(@PathVariable(name = "id") Long id) {
        InternalVersionDetailsVo versionDetailsVo = null;
        try {
            versionDetailsVo = internalVersionService.findInternalVersion(id);
        } catch (Exception e) {
            _log.info("查看内部版本详情发生异常 请求参数 {} 异常信息 {}", id,e);
        }
        return new ResponseData<InternalVersionDetailsVo>(versionDetailsVo);
    }

    @RequestMapping(value = "/back/find/list", method = RequestMethod.POST)
    @ApiOperation(value = "查看内部版本列表", notes = "查看内部版本列表", httpMethod = "POST")
    public ResponseData<ListWithPage<InternalVersionVo>> findList(HttpServletRequest request, HttpServletResponse response, @RequestBody InternalVersionParams versionParams) {
        ListWithPage<InternalVersionVo> list = null;
        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            list = internalVersionService.findList(pageable, versionParams);
        } catch (Exception e) {
            _log.info("查看内部版本列表发生异常 请求参数 {} 异常信息 {}", versionParams,e);
        }
        return new ResponseData<ListWithPage<InternalVersionVo>>(list);
    }

    @RequestMapping(value = "/back/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除内部版本(单个或批量)", notes = "删除内部版本(单个或批量)", httpMethod = "POST")
    public ResponseData<Object> delete(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Long> ids) {
        try {
            internalVersionService.delete(ids);
        } catch (Exception e) {
            _log.info("删除内部版本(单个或批量)发生异常 请求参数 {} 异常信息 {}", ids,e);
        }
        return new ResponseData<Object>("删除成功");
    }

    @RequestMapping(value = "/back/find/tabs", method = RequestMethod.GET)
    @ApiOperation(value = "查询tab信息", notes = "查询tab信息", httpMethod = "GET")
    public ResponseData<List<InternalVersionTabVo>> tabs(HttpServletRequest request, HttpServletResponse response) {
        List<InternalVersionTabVo> list= new ArrayList<InternalVersionTabVo>();
        try {
            list = internalVersionService.tabs();
        } catch (Exception e) {
            _log.info("查询tab信息发生异常 异常信息 {}",e);
        }
        return new ResponseData<List<InternalVersionTabVo>>(list);
    }

    @RequestMapping(value = "/back/find/option", method = RequestMethod.GET)
    @ApiOperation(value = "内部版本号列表", notes = "内部版本号列表", httpMethod = "GET")
    public ResponseData<List<InternalVersionOptionVo>> option(HttpServletRequest request, HttpServletResponse response) {
        List<InternalVersionOptionVo> list= new ArrayList<InternalVersionOptionVo>();
        try {
            list = internalVersionService.option();
        } catch (Exception e) {
            _log.info("内部版本号列表发生异常 异常信息 {}",e);
        }
        return new ResponseData<List<InternalVersionOptionVo>>(list);
    }
}
