package com.tcl.uf.management.param.role;

import com.tcl.commondb.management.model.ManageMenu;

import java.util.List;

public class ManageMenuResp extends ManageMenu {
    private int isAuth = 0;
    private List<ManageMenuResp> children;
    public int getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(int isAuth) {
        this.isAuth = isAuth;
    }

    public List<ManageMenuResp> getChildren() {
        return children;
    }

    public void setChildren(List<ManageMenuResp> children) {
        this.children = children;
    }
}
