package com.tcl.commondb.management.repository;/**
 * Created  on 2018/2/20.
 *
 * @author chiwanmin
 */

import com.tcl.commondb.management.model.OaSystemUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author zhukaiwei@kuyumall.com
 * @InterfaceName: UmSyncUserRepository
 * @Description:
 * @date 2018/2/20 下午9:31
 */

public interface OaUserRepository extends JpaRepository<OaSystemUser, Long>, JpaSpecificationExecutor<OaSystemUser> {

    /**
     * @param umAppid
     * @return
     */
    int deleteAllByUmAppidEquals(String umAppid);

    /**
     * @param userId
     * @return
     */
    OaSystemUser findFirstByUserIdEquals(String userId);

    List<OaSystemUser> findAllByDepartmentIdInAndManageIdIsNotNull(List<String> departmentIds);
    /**
     * @param userId
     * @param sourType
     * @return
     */
    OaSystemUser findFirstByUserIdEqualsAndSourTypeEquals(String userId, String sourType);

    OaSystemUser findFirstByUsername(String username);

    /**
     * @param personCode
     * @return
     */
    OaSystemUser findFirstByPersonCodeEquals(String personCode);

    /**
     * @param mobile
     * @return
     */
    OaSystemUser findFirstByMobileEquals(String mobile);

    /**
     * 获取部门ID获取用户信息
     *
     * @param departmentId
     * @return
     */
    List<OaSystemUser> findAllByDepartmentIdAndUserStatus(String departmentId, String userStatus);

    /**
     * @param departmentId
     * @param umAppid
     * @return
     */
    List<OaSystemUser> findByDepartmentIdAndUmAppid(String departmentId, String umAppid);

    /**
     * @param departmentIds
     * @param umAppid
     * @return
     */
    List<OaSystemUser> findByDepartmentIdInAndUmAppidAndManageIdIsNotNull(Collection<String> departmentIds, String umAppid);

    /**
     * @param departmentIds
     * @param umAppid
     * @return
     */
    List<OaSystemUser> findByDepartmentIdInAndUmAppidEqualsAndUserStatus(Collection<String> departmentIds, String umAppid, String userStatus);


    /**
     * 搜索用户
     *
     * @param search
     * @return
     */
//    @Query(value = "SELECT user FROM OaSystemUser user " +
//            "WHERE (user.userId LIKE %:search% OR user.username LIKE %:search% OR user.email " +
//            "LIKE %:search% OR user.mobile LIKE %:search%)", nativeQuery = false)
//    List<OaSystemUser> findOaUserByLikeSearch(@Param("search") String search);
    Page<OaSystemUser> findAllByUserIdLikeOrUsernameLikeOrEmailLikeOrMobileLike(String search1,String search2,String search3,String search4,Pageable pageable);
//    Page<OaSystemUser> findAllByManageIdNotNullAndUserIdLikeOrUsernameLikeOrEmailLikeOrMobileLike(String search1,String search2,String search3,String search4,Pageable pageable);

    /**
     * 搜索用户
     *
     * @param umAppid
     * @return
     */
    List<OaSystemUser> findByUmAppidEqualsAndUsernameLike(String umAppid, String username);
    /**
     * 用户名搜索匹配
     *
     * @param username
     * @param pageable
     * @return
     */
    Page<OaSystemUser> findAllByUsernameContaining(String username, Pageable pageable);

    /**
     * 获取用户列表
     *
     * @param userIds
     * @param appid
     * @return
     */
    List<OaSystemUser> findAllByUserIdInAndUmAppid(Collection<String> userIds, String appid);


    /**
     * 用户名搜索匹配和部门权限
     *
     * @param username
     * @param departmentIds
     * @param pageable
     * @return
     */
    Page<OaSystemUser> findAllByUsernameContainingAndDepartmentIdIn(String username, List<String> departmentIds, Pageable pageable);


    /**
     * 部门权限,APPID, 用户名模糊匹配  查询用户
     *
     * @param username
     * @param departmentIds
     * @param umAppid
     * @return
     */
    Page<OaSystemUser> findByUsernameLikeAndDepartmentIdInAndUmAppidAndUserStatus(String username, Collection<String> departmentIds, String umAppid, String userStatus, Pageable pageable);


    /**
     * 部门权限,APPID, 用户名模糊匹配  查询用户
     *
     * @param username
     * @param departmentIds
     * @param umAppid
     * @return
     */
    List<OaSystemUser> findByUsernameLikeAndDepartmentIdInAndUmAppid(String username, Collection<String> departmentIds, String umAppid);


    /**
     * 具体名字搜搜
     *
     * @param userList
     * @param pageable
     * @return
     */
    Page<OaSystemUser> findAllByUsernameIn(List<String> userList, Pageable pageable);

    /**
     * 通过manageId获取用户信息
     *
     * @param manageId
     * @return
     */
    OaSystemUser findFirstByManageId(Long manageId);

    /**
     * TODO 通过UseriD查询用户基本信息
     *
     * @param userId
     * @return
     */
    OaSystemUser findFirstByUserId(String userId);

    /**
     * @param username
     * @param departmentId
     * @return
     */
    /*List<OaSystemUser> findBy(@Param("username") String username, @Param("departmentId") String departmentId);*/

    /**
     * 统计部门的用户
     *
     * @param departmentIds
     * @return
     */
    long countByDepartmentIdIn(List<String> departmentIds);

    /**
     * 获取用户在 um_sync_user中的信息
     *
     * @param userId
     * @param umAppid
     * @return
     */
    OaSystemUser findFirstByUserIdAndUmAppid(String userId, String umAppid);


    /**
     * 搜索权限相关的部门ID
     *
     * @param departmentId
     * @return
     */
//    @Query(value = "SELECT u.userId FROM OaSystemUser u WHERE u.departmentId=?1")
//    List<String> getAuthorUserIdsByDepartmentId(String departmentId);

    /**
     * TODO 通过部门名称查询用户基本信息
     *
     * @param departmentName
     * @return
     */
    OaSystemUser findFirstByDepartmentName(String departmentName);

    /**
     * 用户名搜索匹配和部门权限
     *
     * @param position
     * @param departmentId
     * @return
     */
    OaSystemUser findFirstByPositionContainingAndDepartmentIdIn(String position, List<String> departmentId);

//    @Query(nativeQuery = true, value = "SELECT * FROM oa_system_user WHERE update_time >= ?1 AND update_time <= ?2")
//    List<OaSystemUser> findRecordByUpdateTime(String startTime, String endTime);

	/**
     * 根据用户名称模糊匹配
     *
     * @param name
     * @return
     */
    List<OaSystemUser> findByUmAppidAndUserIdContainingOrUsernameContaining(String umAppid, String userId, String userName);

    /**
     * 根据手机号码查询用户
     * @param mobile
     * @return
     */
    OaSystemUser findFirstByMobile(String mobile);
}
