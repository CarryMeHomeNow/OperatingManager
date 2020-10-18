package com.tcl.uf.version.vo;

import java.util.List;

public class InternalVersionTabVo {

    private String tabName;
    private String value;
    private List<String> list;

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
