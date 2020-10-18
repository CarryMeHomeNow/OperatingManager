package com.tcl.uf.management.controller;

import com.tcl.commondb.management.model.OaSystemUser;
import com.tcl.commondb.management.repository.OaUserRepository;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.util.http.HttpHeaderUtils;
import com.tcl.uf.management.param.user.UserAuthReq;
import com.tcl.uf.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractBaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private OaUserRepository oaUserRepository;
    /**
     * 搜索账号列表
     *
     */
    @PostMapping("/searchUser")
    public ResponseData searchUser(@RequestBody Map<String,String> map,HttpServletRequest request) throws Exception{

        TokenData manageUserInfo = getManageUserInfo(request);

        OaSystemUser oaUSer = oaUserRepository.findFirstByManageId(HttpHeaderUtils.getAuthUid(request));
        PageRequest pageRequest = PageRequest.of(Integer.parseInt(map.get("page")),
                Integer.parseInt(map.get("size")));
        return success(userService.listBySearchKey(map.get("searchKey"),oaUSer.getUmAppid(),pageRequest));
    }

    /**
     * 搜索已授权账号
     *
     */
    @PostMapping("/searchAuthUser")
    public ResponseData searchAuthUser(@RequestBody Map<String,String> map, HttpServletRequest request) throws Exception{
        PageRequest pageRequest = PageRequest.of(Integer.parseInt(map.get("page")),
                Integer.parseInt(map.get("size")));
        OaSystemUser oaUSer = oaUserRepository.findFirstByManageId(HttpHeaderUtils.getAuthUid(request));
        return success(userService.listAuthBySearchKey(map.get("searchKey"),oaUSer.getUmAppid(),oaUSer.getDepartmentId(),pageRequest));
    }

    @GetMapping("/delUser/{id}")
    public ResponseData delUser(@PathVariable("id") Long id ) throws Exception{
        userService.delUser(id);
        return success();
    }
    @PostMapping("/create/auth")
    public ResponseData createAuth(@RequestBody UserAuthReq manageUser) throws Exception{
        return success(userService.authUser(manageUser));
    }
}
