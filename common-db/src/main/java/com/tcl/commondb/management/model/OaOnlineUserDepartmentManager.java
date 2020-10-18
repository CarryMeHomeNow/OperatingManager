package com.tcl.commondb.management.model;

import javax.persistence.*;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: OnlineUserDepartmentManager
 * @Description:
 * @date 2018/6/12 下午4:20
 */

@Entity
@Table(name = "oa_online_user_department_manager")
public class OaOnlineUserDepartmentManager {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "department_id", columnDefinition = "varchar(50) COMMENT '分公司的ID'")
    private String departmentId;


    @Column(name = "department_name", columnDefinition = "varchar(50) COMMENT '分公司的名称'")
    private String departmentName;

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
