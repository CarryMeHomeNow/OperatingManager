package com.tcl.uf.management.param.user;

import com.tcl.commondb.management.model.ManageUser;

public class UserAuthReq extends ManageUser {
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
