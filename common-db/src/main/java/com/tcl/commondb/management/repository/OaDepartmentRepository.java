package com.tcl.commondb.management.repository;/**
 * Created  on 2018/2/16.
 *
 * @author chiwanmin
 */

import com.tcl.commondb.management.model.OaDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author zhukaiwei@kuyumall.com
 * @InterfaceName: UmDepartmentRepository
 * @Description:
 * @date 2018/4/16 上午2:15
 */

public interface OaDepartmentRepository extends JpaRepository<OaDepartment, Long> {

    /**
     * @param departmentId
     * @return
     */
    OaDepartment findFirstByDepartmentIdEquals(String departmentId);

    /**
     * 通过APPID和部门ID获取部门信息
     *
     * @param departmentId
     * @param umAppid
     * @return
     */
    OaDepartment findFirstByDepartmentIdAndUmAppid(String departmentId, String umAppid);

    /**
     * 根据部门Id数组UmDepartmentRepository
     *
     * @param departmentIds
     * @return
     */
    List<OaDepartment> findAllByDepartmentIdIn(List<String> departmentIds);

    /**
     * 根据部门Id数组 排序
     *
     * @param departmentIds
     * @return
     */
    List<OaDepartment> findAllByDepartmentIdInOrderByDepartmentIdAsc(Set<String> departmentIds);

    /**
     * 搜索相关的部门ID
     *
     * @param departmentId
     * @param parentDepartmentId
     * @return
     */
    List<OaDepartment> findAllByDepartmentIdOrParentIdListContainsAndUmAppid(String departmentId, String parentDepartmentId, String umAppid);

    /**
     * 搜索权限相关的部门ID
     *
     * @param departmentId
     * @return
     */
    @Query(value = "SELECT d.departmentId FROM OaDepartment d WHERE d.departmentId=?1 OR d.parentIdList LIKE ?2")
    List<String> getAuthorDepartmentIdsByDepartmentId(String departmentId, String searchDepartmentId);


    /**
     * @param departmentIds
     * @return
     */
    List<OaDepartment> findAllByDepartmentIdIn(Set<String> departmentIds);


    /**
     * 获取全部的部门ID
     *
     * @return
     */
    @Query(value = "SELECT d.departmentId FROM OaDepartment d")
    List<String> getAllDepartmentIds();


    /**
     * 获取部门信息
     *
     * @param departmentIds
     * @param umAppid
     * @return
     */
    List<OaDepartment> findAllByDepartmentIdInAndUmAppid(Collection<String> departmentIds, String umAppid);


    /**
     * 获取子部门信息
     *
     * @param departmentId
     * @param umAppid
     * @return
     */
    List<OaDepartment> findAllByParentIdListContainingAndUmAppid(String departmentId, String umAppid);


    /**
     * 获取全量部门数据
     *
     * @param umAppid
     * @return
     */
    List<OaDepartment> findAllByUmAppid(String umAppid);


    /**
     * 获取子部门列表信息
     *
     * @param parentIds
     * @param umAppid
     * @return
     */
    List<OaDepartment> findAllByParentIdInAndUmAppid(Collection<String> parentIds, String umAppid);


    /**
     * 根据名称查询部门信息
     * @param name
     * @param umAppid
     * @return
     */
    OaDepartment findFirstByNameEqualsAndUmAppidEquals(String name, String umAppid);

    @Query(nativeQuery = true, value = "SELECT * FROM um_system_department WHERE update_time >= ?1 AND update_time <= ?2")
    List<OaDepartment> findRecordByUpdateTime(String startTime, String endTime);

    OaDepartment findFirstByNameLike(String name);

    OaDepartment findFirstByNameEquals(String name);

    List<OaDepartment> findByParentId(String parentId);
}
