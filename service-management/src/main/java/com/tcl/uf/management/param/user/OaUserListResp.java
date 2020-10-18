package com.tcl.uf.management.param.user;

public class OaUserListResp {
    private String username;

    private String realname;

    private String departmentName;

    private String phone;

    private String email;

    private String umAppid;

    private Long id;

    private Long manageId;

    private String departmentId;

    private String createTime;

    private Integer useStatus;

    private boolean doFreeze;

    private String roleName;
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getManageId() {
        return manageId;
    }

    public void setManageId(Long manageId) {
        this.manageId = manageId;
    }

    //是否授权
    private boolean isAuth;
    public OaUserListResp(String username, String realname, String departmentName, String phone, String email, String umAppid, Long id, String departmentId,Long manageId) {
        this.username = username;
        this.realname = realname;
        this.departmentName = departmentName;
        this.phone = phone;
        this.email = email;
        this.umAppid = umAppid;
        this.id = id;
        this.departmentId = departmentId;
        this.manageId = manageId;
        if(manageId != null){
            this.isAuth = true;
        }
    }
    public OaUserListResp(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUmAppid() {
        return umAppid;
    }

    public void setUmAppid(String umAppid) {
        this.umAppid = umAppid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public boolean isDoFreeze() {
        return doFreeze;
    }

    public void setDoFreeze(boolean doFreeze) {
        this.doFreeze = doFreeze;
    }
}
