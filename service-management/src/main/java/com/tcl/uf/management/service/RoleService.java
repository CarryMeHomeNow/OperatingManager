package com.tcl.uf.management.service;

import com.tcl.commondb.management.model.ManageRole;
import com.tcl.uf.management.param.role.ManageRoleAddReq;
import com.tcl.uf.management.param.role.ManageRoleMenuResp;

import java.util.List;

public interface RoleService {
    /**
     * 根据用户id查询角色和子角色
     * @param mid 用户id
     * @return
     */
    public List<ManageRole> listByMid(Long mid);

    /**
     * 角色菜单列表
     * @return
     */
    public List<ManageRoleMenuResp> roleMenuListByMid(Long mid);

    /**
     * 添加角色权限
     *
     */
    public boolean addRoleMenu(ManageRoleAddReq manageRoleAddReq);
    public boolean editRoleMenu(ManageRoleAddReq manageRoleAddReq);

    /**
     * 查询用户角色菜单
     * @param mid
     * @return
     */
    public ManageRoleMenuResp roleMenus(Long mid);
    public void delRole(Long roleId);
}
