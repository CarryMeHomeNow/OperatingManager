package com.tcl.uf.advert.controller;

import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.dto.ResourceAuditListParams;
import com.tcl.uf.advert.dto.ResourceAuditParams;
import com.tcl.uf.advert.service.AdvertResourceService;
import com.tcl.uf.advert.vo.ResourceDetailVo;
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

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertResourceAuditController
 * @Description:广告资源审核管理
 * @date 2020/8/19 20:48
 */
@Api(value = "广告资源审核管理")
@RestController
@RequestMapping("/resource/audit")
public class AdvertResourceAuditController extends AbstractBaseController {

    private static final Logger _log = LoggerFactory.getLogger(AdvertResourceAuditController.class);

    @Autowired
    AdvertResourceService advertResourceService;

    @RequiresRoles(AdvertConstants.ADVERT_ROLE_ADMIN)
    @PostMapping(value = "/back/audit")
    @ApiOperation(value = "广告投放资源审核", notes = "广告投放资源审核", httpMethod = "POST")
    public ResponseData<Object> audit(HttpServletRequest request, @RequestBody ResourceAuditParams resourceAuditParams) {
        try {
            TokenData userInfo = getManageUserInfo(request);
            advertResourceService.resourceAudit(resourceAuditParams, userInfo.getUsername());
        } catch (JMException e) {
            _log.error("广告投放资源审核发生异常:", e);
            return fail(e.getMessage());
        }
        return success("操作成功");
    }

    @RequiresRoles(AdvertConstants.ADVERT_ROLE_ADMIN)
    @PostMapping(value = "/back/list")
    @ApiOperation(value = "广告投放资源审核列表", notes = "广告投放资源审核列表", httpMethod = "POST")
    public ResponseData<ListWithPage<ResourceDetailVo>> findList(HttpServletRequest request, @RequestBody ResourceAuditListParams resourceAuditListParams) {
        ListWithPage<ResourceDetailVo> resultList ;
        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            resultList = advertResourceService.resourceAuditList(pageable, resourceAuditListParams);
        } catch (JMException e) {
            _log.error("获取广告投放资源审核列表发生异常:", e);
            return fail(e.getMessage());
        }
        return success(resultList);
    }

}
