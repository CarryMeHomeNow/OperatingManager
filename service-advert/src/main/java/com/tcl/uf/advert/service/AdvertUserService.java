package com.tcl.uf.advert.service;

import com.tcl.commondb.advert.model.AdvertRoleModel;
import com.tcl.commondb.advert.model.AdvertUserModel;
import com.tcl.commondb.management.model.ManageRole;
import com.tcl.commondb.management.model.ManageUser;
import com.tcl.commondb.management.model.ManageUserRole;
import com.tcl.uf.advert.dto.UserAuditParams;
import com.tcl.uf.advert.dto.UserListParams;
import com.tcl.uf.advert.dto.UserStatusParams;
import com.tcl.uf.advert.vo.UserApplyVo;
import com.tcl.uf.advert.vo.UserDetailVo;
import com.tcl.uf.advert.vo.UserListVo;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdvertUserService {

    AdvertUserModel findUserByName(String userName);

    AdvertUserModel findUserByMid(Long mid);

    ManageUserRole findUserRoleByMid(Long mid);

    ManageRole findManageRoleByMid(Long mid);

    ManageUser findManageUserByName(String username);

    List<AdvertRoleModel> findAdvertRolesByUserId(Long userId);

    Long apply(String username,Long departmentId);

    ListWithPage<UserListVo> findList(Pageable pageable, UserListParams userListParams);

    UserApplyVo getOaBaseInfo(String username);

    UserDetailVo getUserDetailByName(String userName);

    UserDetailVo getUserDetailById(Long userId);

    void userAudit(UserAuditParams userAuditParams, String username);

    void updateStatus(UserStatusParams userStatusParams);

}
