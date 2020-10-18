package com.tcl.commondb.management.repository;

import com.tcl.commondb.management.model.OaOnlineUserDepartmentManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author chiwm@kuyumall.com
 * @Interface: OnlineUserDepartmentManagerRepository
 * @Description:
 * @date 2018/6/12 下午4:24
 */

public interface OaOnlineUserDepartmentManagerRepository extends JpaRepository<OaOnlineUserDepartmentManager, Long> {

    /**
     * 获取部门ID匹配的信息
     *
     * @param departmentId
     * @return
     */
    List<OaOnlineUserDepartmentManager> findByDepartmentId(String departmentId);


    OaOnlineUserDepartmentManager findFirstByDepartmentId(String departmentId);

    OaOnlineUserDepartmentManager findFirstByDepartmentName(String departmentName);

    OaOnlineUserDepartmentManager findFirstByDepartmentIdIn(String[] departmentId);


    OaOnlineUserDepartmentManager findFirstByDepartmentIdIn(Collection<String> departmentId);

}
