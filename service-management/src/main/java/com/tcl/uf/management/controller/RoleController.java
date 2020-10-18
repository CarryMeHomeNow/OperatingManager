package com.tcl.uf.management.controller;

import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.http.HttpHeaderUtils;
import com.tcl.uf.management.param.role.ManageRoleAddReq;
import com.tcl.uf.management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/role")
public class RoleController extends AbstractBaseController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/list")
    public ResponseData list(HttpServletRequest request) {
        Long id = HttpHeaderUtils.getAuthUid(request);
        return success(roleService.listByMid(id));
    }
    @PostMapping("/roleMenuList")
    public ResponseData roleMenuListByMid(HttpServletRequest request) {
        Long id = HttpHeaderUtils.getAuthUid(request);
        return success(roleService.roleMenuListByMid(id));
    }

    @PostMapping("/addRoleMenu")
    public ResponseData addRoleMenu(@RequestBody ManageRoleAddReq manageRoleAddReq,HttpServletRequest request)throws JMException {
        Long id = HttpHeaderUtils.getAuthUid(request);
        manageRoleAddReq.setMid(id);
        if(StringUtils.isEmpty(manageRoleAddReq.getName())|| manageRoleAddReq.getMenuIds() == null || manageRoleAddReq.getMenuIds().size()<=0){
            throw new JMException("参数不全");
        }
        return success(roleService.addRoleMenu(manageRoleAddReq));
    }
    @PostMapping("/editRoleMenu")
    public ResponseData editRoleMenu(@RequestBody ManageRoleAddReq manageRoleAddReq,HttpServletRequest request) {
        Long id = HttpHeaderUtils.getAuthUid(request);
        manageRoleAddReq.setMid(id);
        return success(roleService.editRoleMenu(manageRoleAddReq));
    }
    @GetMapping("/del/{id}")
    public ResponseData del(@PathVariable("id") Long id) {
        roleService.delRole(id);
        return success();
    }
}
