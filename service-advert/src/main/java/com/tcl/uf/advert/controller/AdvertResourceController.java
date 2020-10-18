package com.tcl.uf.advert.controller;

import com.tcl.uf.advert.dto.*;
import com.tcl.uf.advert.service.AdvertResourceService;
import com.tcl.uf.advert.vo.ResourceAppListVo;
import com.tcl.uf.advert.vo.ResourceDetailVo;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertResourceController
 * @Description:广告投放资源管理
 * @date 2020/8/19 20:48
 */
@Api(value = "广告投放资源管理")
@RestController
@RequestMapping("/resource")
public class AdvertResourceController extends AbstractBaseController {

    private static final Logger _log = LoggerFactory.getLogger(AdvertResourceController.class);

    @Autowired
    AdvertResourceService advertResourceService;

    @PostMapping(value = "/app/list")
    @ApiOperation(value = "APP获取广告位广告素材列表", notes = "APP获取广告位广告素材列表", httpMethod = "POST")
    public ResponseData<List<ResourceAppListVo>> appList(HttpServletRequest request, HttpServletResponse response, @RequestBody ResourceAppListParams appListParams) {
        List<ResourceAppListVo> list;
        try {
            list = advertResourceService.findAppList(appListParams);
        } catch (JMException e) {
            _log.error("APP获取广告位广告素材列表异常", e);
            return fail(e.getErrCode(), e.getMessage());
        }
        return success(list);
    }

    @PostMapping("/back/app/List")
    @ApiOperation(value = "后端服务获取广告位广告素材列表", notes = "后端服务获取广告位广告素材列表", httpMethod = "POST")
    public ResponseData<List<ResourceAppListVo>> intAppList(@RequestBody ResourceAppListParams appListParams, @RequestParam("accountId") Long accountId) {
        List<ResourceAppListVo> list;
        try {
            list = advertResourceService.findAppList(appListParams);
        } catch (JMException e) {
            _log.error("后端服务获取广告位广告素材列表异常", e);
            return fail(e.getErrCode(), e.getMessage());
        }
        return success(list);
    }

    @PostMapping(value = "/back/saveOrUpdate")
    @ApiOperation(value = "新增或修改广告资源", notes = "新增或修改广告资源", httpMethod = "POST")
    public ResponseData<Object> saveOrUpdate(HttpServletRequest request, @RequestBody ResourceDetailDto resourceDetailDto) {
        Long resourceId;
        try {
            TokenData userInfo = getManageUserInfo(request);
            resourceId = advertResourceService.saveOrUpdate(resourceDetailDto, userInfo.getUsername());
        } catch (JMException e) {
            _log.error("新增或修改广告资源异常", e);
            return fail(e.getMessage());
        }
        return success(resourceId);
    }

    @GetMapping(value = "/back/detail/{id}")
    @ApiOperation(value = "查看广告资源详情", notes = "查看广告资源详情", httpMethod = "GET")
    public ResponseData<ResourceDetailVo> findDetail(@PathVariable(name = "id") Long id) {
        ResourceDetailVo resourceDetailVo;
        try {
            resourceDetailVo = advertResourceService.findDetail(id);
        } catch (JMException e) {
            _log.error("查看广告资源详情发生异常:", e);
            return fail(e.getMessage());
        }
        return success(resourceDetailVo);
    }

    @PostMapping(value = "/back/list")
    @ApiOperation(value = "广告投放资源列表", notes = "广告投放资源列表", httpMethod = "POST")
    public ResponseData<ListWithPage<ResourceDetailVo>> findList(HttpServletRequest request, @RequestBody ResourceStatusListParams resourceStatusListParams) {
        ListWithPage<ResourceDetailVo> resultList;
        try {
            TokenData userInfo = getManageUserInfo(request);
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            resultList = advertResourceService.resourceStatusList(pageable, resourceStatusListParams, userInfo.getUsername());
        } catch (JMException e) {
            _log.error("获取广告投放资源列表发生异常:", e);
            return fail(e.getMessage());
        }
        return success(resultList);
    }

    @PostMapping(value = "/back/updateStatus")
    @ApiOperation(value = "广告资源状态更新", notes = "广告资源状态更新", httpMethod = "POST")
    public ResponseData<Object> updateStatus(HttpServletRequest request, @RequestBody ResourceStatusParams resourceStatusParams) {
        try {
            TokenData userInfo = getManageUserInfo(request);
            advertResourceService.updateStatus(resourceStatusParams, userInfo.getUsername());
        } catch (JMException e) {
            _log.error("广告资源状态更新发生异常:", e);
            return fail(e.getMessage());
        }
        return success("操作成功");
    }

}
