package com.tcl.uf.management.param.role;

import com.tcl.commondb.management.model.ManageRole;

import java.util.List;

public class ManageRoleMenuResp extends ManageRole {
    private List<ManageMenuResp> children;

    public List<ManageMenuResp> getChildren() {
        return children;
    }

    public void setChildren(List<ManageMenuResp> children) {
        this.children = children;
    }
}
