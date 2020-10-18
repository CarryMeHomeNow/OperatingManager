package com.tcl.uf.version.controller;

import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import com.tcl.uf.version.dto.ExternalListParams;
import com.tcl.uf.version.dto.ExternalOnlineParams;
import com.tcl.uf.version.dto.ExternalUpdateParams;
import com.tcl.uf.version.service.ExternalVersionService;
import com.tcl.uf.version.vo.ExternalDetailVo;
import com.tcl.uf.version.vo.ExternalOnlineVo;
import com.tcl.uf.version.vo.NewVersionVo;
import com.tcl.uf.version.vo.PlatformDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 应用渠道版本管理
 */
@Api(description = "应用渠道版本管理")
@RestController
@RequestMapping("/external/version/")
public class ExternalVersionController extends AbstractBaseController {

    private static final Logger log = LoggerFactory.getLogger(ExternalVersionController.class);

    @Autowired
    private ExternalVersionService externalVersionService;

    @ApiOperation(value = "新增/编辑渠道版本", notes = "新增/编辑渠道版本", httpMethod = "POST")
    @PostMapping(value = "/back/saveOrUpdate")
    public ResponseData<Object> saveOrUpdate(HttpServletRequest request, @RequestBody ExternalUpdateParams params) {
        try {
            TokenData tokenData = getManageUserInfo(request);
            if (tokenData != null) {
                params.setUserId(tokenData.getUsername());
            }
            if (params.getPlatformId() == null) {
                return new ResponseData<>(0, "应用渠道信息不能为空", "");
            }
            externalVersionService.saveOrUpdate(params);
        } catch (Exception e) {
            log.error("应用渠道版本新增/修改失败", e);
            return new ResponseData<>(0, e.getMessage(), "");
        }
        return new ResponseData<>();
    }

    @ApiOperation(value = "应用渠道版本列表", notes = "应用渠道版本列表", httpMethod = "POST")
    @PostMapping(value = "/back/list")
    public ResponseData<ListWithPage<ExternalDetailVo>> externalVersionList(HttpServletRequest request, @RequestBody(required = false) ExternalListParams params) {
        ListWithPage<ExternalDetailVo> list;
        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            list = externalVersionService.externalList(pageable, params);
        } catch (Exception e) {
            log.error("应用渠道版本列表查询失败", e);
            return new ResponseData<>(0, "查询失败", "");
        }
        return new ResponseData<>(list);
    }


    @ApiOperation(value = "删除渠道版本（单个或批量）", notes = "删除渠道版本（单个或批量）", httpMethod = "POST")
    @PostMapping(value = "/back/delete")
    public ResponseData<Object> delete(@RequestBody List<Long> ids) {
        try {
            externalVersionService.delete(ids);
        } catch (Exception e) {
            log.error("应用渠道版本删除失败", e);
            return new ResponseData<>(0, "操作失败", "");
        }
        return new ResponseData<>();
    }

    @ApiOperation(value = "渠道版本详情", notes = "渠道版本详情", httpMethod = "GET")
    @GetMapping(value = "/back/{externalId}")
    public ResponseData<ExternalDetailVo> detail(@PathVariable("externalId") Long externalId) {
        ExternalDetailVo vo;
        try {
            vo = externalVersionService.externalDetail(externalId);
        } catch (Exception e) {
            log.error("渠道版本详情查询失败", e);
            return new ResponseData<>(0, "查询失败", "");
        }
        return new ResponseData<>(vo);
    }

    @ApiOperation(value = "各渠道版本上线状态列表", notes = "各渠道版本上线状态列表", httpMethod = "POST")
    @PostMapping(value = "/back/onlineList")
    public ResponseData<ListWithPage<ExternalOnlineVo>> onlineList(HttpServletRequest request) {
        ListWithPage<ExternalOnlineVo> list;
        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            list = externalVersionService.onlineList(pageable);
        } catch (Exception e) {
            log.error("上线状态列表查询失败", e);
            return new ResponseData<>(0, "查询失败", "");
        }
        return new ResponseData<>(list);
    }

    @ApiOperation(value = "根据渠道id获取该渠道最新版本",notes = "根据渠道id获取该渠道最新版本",httpMethod = "GET")
    @GetMapping("/app/new/version/{platformId}")
    public ResponseData<NewVersionVo> findNewVersionByPlatformId(@PathVariable(value = "platformId") String platformId) {
        NewVersionVo data = externalVersionService.findNewVersionByPlatformId(platformId);
        return new ResponseData<>(data);
    }

    @ApiOperation(value = "根据渠道版本查询详情", notes = "根据渠道版本查询详情", httpMethod = "POST")
    @PostMapping(value = "/back/onlineDetail")
    public ResponseData<ExternalDetailVo> onlineDetail(@RequestBody ExternalOnlineParams params) {
        ExternalDetailVo vo;
        try {
            vo = externalVersionService.onlineDetail(params);
        } catch (Exception e) {
            log.error("根据渠道版本查询详情查询失败", e);
            return new ResponseData<>(0, "查询失败", "");
        }
        return new ResponseData<>(vo);
    }

    @ApiOperation(value = "应用渠道列表", notes = "应用渠道列表", httpMethod = "GET")
    @GetMapping(value = "/back/platformList")
    public ResponseData<List<PlatformDetailVo>> platformList() {
        List<PlatformDetailVo> vo;
        try {
            vo = externalVersionService.platformList();
        } catch (Exception e) {
            log.error("应用渠道列表查询失败", e);
            return new ResponseData<>(0, "查询失败", "");
        }
        return new ResponseData<>(vo);
    }
}
