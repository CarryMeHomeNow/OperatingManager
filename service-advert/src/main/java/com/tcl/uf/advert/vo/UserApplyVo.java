package com.tcl.uf.advert.vo;

import java.util.Map;

public class UserApplyVo {

    private String userName;

    private String oaEmail;

    private Map<Long,String> departmentList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOaEmail() {
        return oaEmail;
    }

    public void setOaEmail(String oaEmail) {
        this.oaEmail = oaEmail;
    }

    public Map<Long, String> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(Map<Long, String> departmentList) {
        this.departmentList = departmentList;
    }
}
