package com.tcl.uf.advert.controller;

import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.dto.UserApplyParams;
import com.tcl.uf.advert.dto.UserAuditParams;
import com.tcl.uf.advert.dto.UserListParams;
import com.tcl.uf.advert.dto.UserStatusParams;
import com.tcl.uf.advert.service.AdvertUserService;
import com.tcl.uf.advert.vo.UserApplyVo;
import com.tcl.uf.advert.vo.UserDetailVo;
import com.tcl.uf.advert.vo.UserListVo;
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
import javax.servlet.http.HttpServletResponse;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertUserController
 * @Description:广告用户管理
 * @date 2020/8/17 20:48
 */
@Api(value = "广告用户管理")
@RestController
@RequestMapping("/user")
public class AdvertUserController extends AbstractBaseController {

    private static final Logger _log = LoggerFactory.getLogger(AdvertUserController.class);

    @Autowired
    private AdvertUserService advertUserService;

    @GetMapping(value = "/back/unauthorized")
    @ApiOperation(value = "未授权用户统一跳转", notes = "未授权用户统一跳转", httpMethod = "GET")
    public ResponseData<Object> unauthorized() {
        return fail(AdvertConstants.AUTH_STATUS_CODE_UNAUTH,"获取授权失败");
    }

    @GetMapping(value = "/back/onlineUserInfo")
    @ApiOperation(value = "查询当前登录用户信息详情", notes = "查询当前登录用户信息详情", httpMethod = "GET")
    public ResponseData<Object> onlineUserInfo(HttpServletRequest request) {

        UserDetailVo userDetailVo;
        try {
            TokenData userInfo = getManageUserInfo(request);
            userDetailVo = advertUserService.getUserDetailByName(userInfo.getUsername());
            if(userDetailVo != null && userDetailVo.getStatus() != AdvertConstants.ROLE_STATUS_ONLINE ){
                return success(null);
            }
        } catch (JMException e) {
            _log.error("查询当前登录用户信息详情异常:", e);
            return fail(e.getMessage());
        }
        return success(userDetailVo);
    }

    @RequiresRoles(AdvertConstants.ADVERT_ROLE_ADMIN)
    @GetMapping(value = "/back/detail/{id}")
    @ApiOperation(value = "查询用户信息详情", notes = "查询用户信息详情", httpMethod = "GET")
    public ResponseData<UserDetailVo> findUserDetail(HttpServletRequest request, @PathVariable(name = "id") Long id) {

        UserDetailVo userDetailVo;
        try {
            userDetailVo = advertUserService.getUserDetailById(id);
        } catch (JMException e) {
            _log.error("查询用户信息详情异常:", e);
            return fail(e.getMessage());
        }
        return success(userDetailVo);
    }

    @GetMapping(value = "/back/oaUserInfo")
    @ApiOperation(value = "获取当前登录用户OA信息", notes = "获取当前登录用户OA信息", httpMethod = "GET")
    public ResponseData<UserApplyVo> oaUserInfo(HttpServletRequest request) {
        UserApplyVo userApplyVo;
        try {
            TokenData userInfo = getManageUserInfo(request);
            userApplyVo = advertUserService.getOaBaseInfo(userInfo.getUsername());
        } catch (JMException e) {
            _log.error("用户申请发生异常:", e);
            return fail(e.getMessage());
        }
        return success(userApplyVo);
    }

    @PostMapping(value = "/back/apply")
    @ApiOperation(value = "业务方用户权限申请", notes = "业务方用户权限申请", httpMethod = "POST")
    public ResponseData<Object> apply(@RequestBody UserApplyParams userApplyParams, HttpServletRequest request) {
        Long userId;
        try {
            TokenData userInfo = getManageUserInfo(request);
            userId = advertUserService.apply(userInfo.getUsername(), userApplyParams.getDepartmentId());
        } catch (JMException e) {
            _log.error("用户申请发生异常:", e);
            return fail(e.getMessage());
        }
        return success(userId);
    }

    @RequiresRoles(AdvertConstants.ADVERT_ROLE_ADMIN)
    @PostMapping(value = "/back/list")
    @ApiOperation(value = "查看人员列表", notes = "查看人员列表", httpMethod = "POST")
    public ResponseData<ListWithPage<UserListVo>> findList(HttpServletRequest request,  @RequestBody UserListParams userListParams) {
        ListWithPage<UserListVo> list;
        try {
            Pageable pageable = PageUtils.getPageFromRequestParam(request);
            list = advertUserService.findList(pageable, userListParams);
        } catch (JMException e) {
            _log.error("查看人员列表发生异常:", e);
            return fail(e.getMessage());
        }
        return new ResponseData<>(list);
    }

    @RequiresRoles(AdvertConstants.ADVERT_ROLE_ADMIN)
    @PostMapping(value = "/back/audit")
    @ApiOperation(value = "用户权限审核", notes = "用户权限审核", httpMethod = "POST")
    public ResponseData<Object> audit(HttpServletRequest request, @RequestBody UserAuditParams userAuditParams) {
        try {
            TokenData userInfo = getManageUserInfo(request);
            advertUserService.userAudit(userAuditParams,userInfo.getUsername());
        } catch (JMException e) {
            _log.error("用户权限审核发生异常:", e);
            return fail(e.getMessage());
        }
        return success("操作成功");
    }

    @RequiresRoles(AdvertConstants.ADVERT_ROLE_ADMIN)
    @PostMapping(value = "/back/updateStatus")
    @ApiOperation(value = "用户状态更新", notes = "用户状态更新", httpMethod = "POST")
    public ResponseData<Object> updateStatus(HttpServletRequest request, HttpServletResponse response, @RequestBody UserStatusParams userStatusParams) {
        try {
            advertUserService.updateStatus(userStatusParams);
        } catch (JMException e) {
            _log.error("用户状态更新发生异常:", e);
            return fail(e.getMessage());
        }
        return success("操作成功");
    }

}
