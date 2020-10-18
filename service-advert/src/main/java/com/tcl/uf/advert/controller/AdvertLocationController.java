package com.tcl.uf.advert.controller;

import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.dto.*;
import com.tcl.uf.advert.service.AdvertLocationGroupService;
import com.tcl.uf.advert.service.AdvertLocationService;
import com.tcl.uf.advert.vo.LocationDetailVo;
import com.tcl.uf.advert.vo.LocationGroupListVo;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertLocationController
 * @Description:广告位管理
 * @date 2020/8/17 20:48
 */
@Api(value = "广告位管理")
@RestController
@RequestMapping("/location")
public class AdvertLocationController extends AbstractBaseController {

    private static final Logger _log = LoggerFactory.getLogger(AdvertLocationController.class);

    @Autowired
    private AdvertLocationService advertLocationService;

    @Autowired
    private AdvertLocationGroupService advertLocationGroupService;

    @RequiresRoles(AdvertConstants.ADVERT_ROLE_ADMIN)
    @PostMapping(value = "/back/saveOrUpdate")
    @ApiOperation(value = "新增或修改广告位", notes = "新增或修改广告位", httpMethod = "POST")
    public ResponseData<Object> saveOrUpdate(HttpServletRequest request, @RequestBody LocationDto locationDto) {
        Long locationId;
        try {
            TokenData userInfo = getManageUserInfo(request);
            locationId = advertLocationService.saveOrUpdate(locationDto, userInfo.getUsername());
        } catch (JMException e) {
            _log.error("新增或修改广告位发生异常:", e);
            return fail(e.getMessage());
        }
        return success(locationId);
    }

    @RequiresRoles(AdvertConstants.ADVERT_ROLE_ADMIN)
    @GetMapping(value = "/back/detail/{id}")
    @ApiOperation(value = "查看广告位详情", notes = "查看广告位详情", httpMethod = "GET")
    public ResponseData<LocationDetailVo> findDetail(@PathVariable(name = "id") Long id) {
        LocationDetailVo locationDetailVo;
        try {
            locationDetailVo = advertLocationService.findDetail(id);
        } catch (JMException e) {
            _log.error("查看广告位详情发生异常:", e);
            return fail(e.getMessage());
        }
        return success(locationDetailVo);
    }

    @RequiresRoles(AdvertConstants.ADVERT_ROLE_ADMIN)
    @PostMapping(value = "/back/updateStatus")
    @ApiOperation(value = "广告位状态更新", notes = "广告位状态更新", httpMethod = "POST")
    public ResponseData<Object> updateStatus(HttpServletRequest request, @RequestBody LocationStatusParams locationStatusParams) {
        try {
            TokenData userInfo = getManageUserInfo(request);
            advertLocationService.updateStatus(locationStatusParams, userInfo.getUsername());
        } catch (JMException e) {
            _log.error("广告位状态更新失败:", e);
            return fail(e.getMessage());
        }
        return success("操作成功");
    }

    @RequiresRoles(AdvertConstants.ADVERT_ROLE_ADMIN)
    @PostMapping(value = "/back/list")
    @ApiOperation(value = "查看广告位列表", notes = "查看广告位列表", httpMethod = "POST")
    public ResponseData<ListWithPage<LocationDetailVo>> findList(HttpServletRequest request, @RequestBody LocationListParams locationListParams) {
        ListWithPage<LocationDetailVo> list;
        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            list = advertLocationService.findList(pageable, locationListParams);
        } catch (JMException e) {
            _log.error("查看广告位列表发生异常:", e);
            return fail(e.getMessage());
        }
        return success(list);
    }

    @RequiresRoles(AdvertConstants.ADVERT_ROLE_ADMIN)
    @GetMapping(value = "/group/back/list")
    @ApiOperation(value = "查看广告组列表", notes = "查看广告组列表", httpMethod = "GET")
    public ResponseData<List<LocationGroupListVo>> findGroupList() {
        List<LocationGroupListVo> list;
        try {
            list = advertLocationGroupService.findList();
        } catch (JMException e) {
            _log.error("查看广告组列表发生异常:", e);
            return fail(e.getMessage());
        }
        return success(list);
    }

}
