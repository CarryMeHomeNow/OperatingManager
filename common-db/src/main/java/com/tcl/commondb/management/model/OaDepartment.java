package com.tcl.commondb.management.model;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhukaiwei@kuyumall.com
 * @ClassName: UmDepartment
 * @Description: 四个在线用户表
 * @date 2018/4/16 上午2:11
 */

@Entity
@Table(name = "oa_system_department")
public class OaDepartment {

    public static final int ROOT_PARENT_ID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "d_name", columnDefinition = "varchar(100) NOT NULL COMMENT '部门名称'")
    private String name;

    @Column(name = "department_id", columnDefinition = "varchar(20) NOT NULL COMMENT '部门ID'")
    private String departmentId;

    @Column(name = "parent_id", columnDefinition = "varchar(20) NOT NULL COMMENT '父节点ID'")
    private String parentId;

    @Column(name = "parent_id_list", columnDefinition = "varchar(255) COMMENT '父节点列表 , 分割'")
    private String parentIdList;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Timestamp createTime;

    @Column(name = "update_time", columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    private Timestamp updateTime;

    @Column(name = "um_appid", columnDefinition = "varchar(20) DEFAULT 'TCL' COMMENT '组织代码'")
    private String umAppid;

    public String getUmAppid() {
        return umAppid;
    }

    public void setUmAppid(String umAppid) {
        this.umAppid = umAppid;
    }

    public String getParentIdList() {
        return parentIdList;
    }

    public void setParentIdList(String parentIdList) {
        this.parentIdList = parentIdList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }


    /**
     * FIXME 需要去忽略该方法
     *
     * @return List<String></>
     */
    public List<String> getParentDepartmentIdList() {
        if (StringUtils.isEmpty(parentIdList)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(Arrays.asList(parentIdList.split(",")));
    }

}
