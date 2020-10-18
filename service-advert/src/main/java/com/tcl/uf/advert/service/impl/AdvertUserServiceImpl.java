package com.tcl.uf.advert.service.impl;

import com.tcl.commondb.advert.model.*;
import com.tcl.commondb.advert.repository.*;
import com.tcl.commondb.management.model.ManageRole;
import com.tcl.commondb.management.model.ManageUser;
import com.tcl.commondb.management.model.ManageUserRole;
import com.tcl.commondb.management.model.OaDepartment;
import com.tcl.commondb.management.repository.ManageRoleRepository;
import com.tcl.commondb.management.repository.ManageUserRepository;
import com.tcl.commondb.management.repository.ManageUserRoleRepository;
import com.tcl.commondb.management.repository.OaDepartmentRepository;
import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.dto.UserAuditParams;
import com.tcl.uf.advert.dto.UserListParams;
import com.tcl.uf.advert.dto.UserStatusParams;
import com.tcl.uf.advert.exception.JMailException;
import com.tcl.uf.advert.service.AdvertDepartmentService;
import com.tcl.uf.advert.service.AdvertMailService;
import com.tcl.uf.advert.service.AdvertUserService;
import com.tcl.uf.advert.vo.UserApplyVo;
import com.tcl.uf.advert.vo.UserDetailVo;
import com.tcl.uf.advert.vo.UserListVo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tcl.uf.common.base.constant.CommonConstant;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertUserServiceImpl
 * @Description:广告管理平台业务方用户
 * @date 2020/8/17 19:08
 */
@Service("AdvertUserService")
public class AdvertUserServiceImpl implements AdvertUserService {

    private static final Logger log = LoggerFactory.getLogger(AdvertUserServiceImpl.class);

    @Autowired
    AdvertUserModelRepository advertUserModelRepository;

    @Autowired
    AdvertUserRoleModelRepository advertUserRoleModelRepository;

    @Autowired
    AdvertRoleModelRepository advertRoleModelRepository;

    @Autowired
    ManageUserRepository manageUserRepository;

    @Autowired
    ManageRoleRepository manageRoleRepository;

    @Autowired
    ManageUserRoleRepository manageUserRoleRepository;

    @Autowired
    AdvertDepartmentModelRepository advertDepartmentModelRepository;

    @Autowired
    OaDepartmentRepository oaDepartmentRepository;

    @Autowired
    AdvertApproveLogModelRepository advertApproveLogModelRepository;

    @Autowired
    AdvertMailService advertMailService;

    @Autowired
    AdvertDepartmentService advertDepartmentService;

    @Override
    public AdvertUserModel findUserByName(String userName) {
        return advertUserModelRepository.findFirstByIsDeletedAndUsername(AdvertConstants.DELETE_FLAG_FALSE, userName);
    }

    @Override
    public AdvertUserModel findUserByMid(Long mid) {
        return advertUserModelRepository.findFirstByMid(mid);
    }

    @Override
    public ManageUserRole findUserRoleByMid(Long mid) {
        return manageUserRoleRepository.findFirstBymId(mid);
    }

    @Override
    public ManageRole findManageRoleByMid(Long mid) {
        ManageUserRole userRole = manageUserRoleRepository.findFirstBymId(mid);
        return manageRoleRepository.findFirstByIdAndIsDelete(userRole.getcId(), CommonConstant.NO);
    }

    @Override
    public ManageUser findManageUserByName(String username) {
        return manageUserRepository.findFirstByUsername(username);
    }

    @Override
    public List<AdvertRoleModel> findAdvertRolesByUserId(Long userId) {
        List<AdvertUserRoleModel> userRoles = advertUserRoleModelRepository.findAllByUserId(userId);
        //查询
        List<Long> roleIds = new ArrayList<>();
        for (AdvertUserRoleModel userRoleModel : userRoles) {
            roleIds.add(userRoleModel.getRoleId());
        }
        return advertRoleModelRepository.findAllByIsDeletedAndIdIn(AdvertConstants.DELETE_FLAG_FALSE, roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long apply(String username, Long departmentId) {

        //验证用户信息
        AdvertDepartmentModel advertDepartmentModel = advertDepartmentModelRepository.findFirstByIdEquals(departmentId);
        if (advertDepartmentModel == null) {
            throw new JMException("选择的业务部门不存在");
        }

        ManageUser manageUser = manageUserRepository.findFirstByUsername(username);
        if (manageUser == null) {
            throw new JMException("平台用户不存在");
        }

        //查询用户管理后台角色
        ManageRole userRole = findManageRoleByMid(manageUser.getId());

        OaDepartment oaDepartment = oaDepartmentRepository.findFirstByDepartmentIdEquals(manageUser.getDepartmentId());
        if (oaDepartment == null) {
            throw new JMException("用户所属组织架构不存在");
        }

        AdvertUserModel advertUserModel = advertUserModelRepository.findFirstByUsername(username);
        if (advertUserModel != null) {
            throw new JMException("该用户已有申请记录,请联系管理员");
        }

        //新增用户
        advertUserModel = new AdvertUserModel();
        advertUserModel.setDepartmentId(advertDepartmentModel.getId());
        advertUserModel.setUsername(manageUser.getUsername());
        advertUserModel.setMid(manageUser.getId());
        advertUserModel.setOaEmail(manageUser.getEmail());
        advertUserModel.setOaDepartmentName(oaDepartment.getName());
        advertUserModel.setIsDeleted(AdvertConstants.DELETE_FLAG_FALSE);
        advertUserModel.setCreateTime(TimeUtils.getTimestamp());

        //设置权限
        AdvertRoleModel advertRoleModel;
        AdvertUserRoleModel avertUserRoleModel = new AdvertUserRoleModel();

        if (userRole.getCharacte().equals(AdvertConstants.MANAGEMENT_ROLE_ADMIN_NAME)) {
            advertRoleModel = advertRoleModelRepository.findFirstByIsDeletedAndName(AdvertConstants.DELETE_FLAG_FALSE, AdvertConstants.ADVERT_ROLE_ADMIN);
            advertUserModel.setAuditStatus(AdvertConstants.AUDIT_STATUS_PASS);
            advertUserModel.setStatus(AdvertConstants.ROLE_STATUS_ONLINE);
        }else{
            if(advertDepartmentModel.getType() == AdvertConstants.DEPARTMENT_TYPE_MANAGEMENT){
                throw new JMException("非平台管理员不能申请管理部门权限");
            }
            advertRoleModel = advertRoleModelRepository.findFirstByIsDeletedAndName(AdvertConstants.DELETE_FLAG_FALSE, AdvertConstants.ADVERT_ROLE_USER);
            advertUserModel.setAuditStatus(AdvertConstants.AUDIT_STATUS_DRAFT);
            advertUserModel.setStatus(AdvertConstants.ROLE_STATUS_AUDITING);
        }
        advertUserModelRepository.saveAndFlush(advertUserModel);
        avertUserRoleModel.setRoleId(advertRoleModel.getId());
        avertUserRoleModel.setUserId(advertUserModel.getId());
        avertUserRoleModel.setCreateTime(TimeUtils.getTimestamp());
        advertUserRoleModelRepository.saveAndFlush(avertUserRoleModel);

        return advertUserModel.getId();
    }

    @Override
    public ListWithPage<UserListVo> findList(Pageable pageable, UserListParams userListParams) {
        Long sumTotal = null;
        List<UserListVo> records = null;
        //构造初始状态排序条件
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "createTime"));
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
        Page<AdvertUserModel> result = advertUserModelRepository.findAllByIsDeletedAndAuditStatus(AdvertConstants.DELETE_FLAG_FALSE, userListParams.getAuditStatus(), page);

        List<UserListVo> resultList = new ArrayList<>();
        Set<Long> departmentIds = new HashSet<>();
        result.forEach(item -> {
            String confirmTime = "";
            String createTime = "";
            if (item.getAuditTime() != null) {
                confirmTime = TimeUtils.getLongDateStr(item.getAuditTime());
            }
            if (item.getCreateTime() != null) {
                createTime = TimeUtils.getLongDateStr(item.getCreateTime());
            }
            UserListVo vo = new UserListVo(item.getId(), item.getUsername(), null, item.getOaEmail(), item.getOaDepartmentName(), createTime, confirmTime, item.getAuditStatus(), item.getStatus(), item.getDepartmentId());
            resultList.add(vo);
            departmentIds.add(item.getDepartmentId());
        });

        Map<Long, AdvertDepartmentModel> departmentMap = advertDepartmentService.getDepartmentMap(departmentIds);
        resultList.forEach(e -> {
            if (departmentMap.containsKey(e.getDepartmentId())) {
                e.setDepartmentName(departmentMap.get(e.getDepartmentId()).getDepartmentName());
            }
        });

        //数据库实体对象转VO对象
        if (!result.isEmpty()) {
            sumTotal = result.getTotalElements();
            records = resultList;
        } else {
            sumTotal = Long.getLong("0");
            records = new ArrayList<>();
        }
        return PageUtils.formatData(records, pageable, sumTotal);
    }

    @Override
    public UserApplyVo getOaBaseInfo(String username) {
        ManageUser manageUser = manageUserRepository.findFirstByUsername(username);
        if (manageUser == null) {
            throw new JMException("用户名管理后台不存在");
        }

        //查询用户管理后台角色
        ManageRole userRole = findManageRoleByMid(manageUser.getId());
        List<AdvertDepartmentModel> departmentList;
        if (userRole.getCharacte().equals(AdvertConstants.MANAGEMENT_ROLE_ADMIN_NAME)) {
            departmentList = advertDepartmentModelRepository.findAllByIsDeleted(AdvertConstants.DELETE_FLAG_FALSE);
        } else {
            departmentList = advertDepartmentModelRepository.findAllByIsDeletedAndType(AdvertConstants.DELETE_FLAG_FALSE, AdvertConstants.DEPARTMENT_TYPE_BUSINESS);
        }
        Map<Long, String> departmentMap = departmentList.stream().collect(Collectors.toMap(AdvertDepartmentModel::getId, AdvertDepartmentModel::getDepartmentName));

        UserApplyVo userApplyVo = new UserApplyVo();
        userApplyVo.setOaEmail(manageUser.getEmail());
        userApplyVo.setUserName(manageUser.getUsername());
        userApplyVo.setDepartmentList(departmentMap);

        return userApplyVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = JMailException.class)
    public void userAudit(UserAuditParams userAuditParams, String username) {

        //校验用户状态
        AdvertUserModel advertUserModel = advertUserModelRepository.findByIdEquals(userAuditParams.getUserId());
        if (advertUserModel == null) {
            throw new JMException("用户不存在");
        }
        if (advertUserModel.getAuditStatus() != AdvertConstants.AUDIT_STATUS_DRAFT) {
            throw new JMException("用户已审核");
        }

        String mailTitle = AdvertConstants.MAIL_TEMPLATE_USER_AUDIT_TITLE;
        String mailContent;
        //更新审核状态
        if (userAuditParams.getAuditStatus() == AdvertConstants.AUDIT_STATUS_PASS) {
            advertUserModel.setAuditStatus(AdvertConstants.AUDIT_STATUS_PASS);
            advertUserModel.setAuditTime(TimeUtils.getTimestamp());
            advertUserModel.setStatus(AdvertConstants.ROLE_STATUS_ONLINE);
            advertUserModelRepository.saveAndFlush(advertUserModel);
            mailContent = AdvertConstants.MAIL_TEMPLATE_USER_AUDIT_PASS.replace("{url}", AdvertConstants.ADVERT_SYSTEM_URL);
        } else if (userAuditParams.getAuditStatus() == AdvertConstants.AUDIT_STATUS_REJECT) {
            advertUserModel.setAuditStatus(AdvertConstants.AUDIT_STATUS_REJECT);
            advertUserModel.setAuditTime(TimeUtils.getTimestamp());
            advertUserModel.setStatus(AdvertConstants.ROLE_STATUS_DRAFT);
            advertUserModelRepository.saveAndFlush(advertUserModel);
            mailContent = AdvertConstants.MAIL_TEMPLATE_USER_AUDIT_REJECT.replace("{url}", AdvertConstants.ADVERT_SYSTEM_URL);
            mailContent = mailContent.replace("{remark}", userAuditParams.getRemarks());
        } else {
            throw new JMException("非法状态参数");
        }

        //写入审核日志
        AdvertApproveLogModel advertApproveLogModel = new AdvertApproveLogModel();
        advertApproveLogModel.setApplyId(advertUserModel.getId());
        advertApproveLogModel.setApplyType(AdvertConstants.AUDIT_TYPE_AD_USER);
        advertApproveLogModel.setApprover(username);
        advertApproveLogModel.setApproveResult(userAuditParams.getAuditStatus());
        advertApproveLogModel.setRemark(userAuditParams.getRemarks());
        advertApproveLogModel.setCreateTime(TimeUtils.getTimestamp());
        advertApproveLogModelRepository.saveAndFlush(advertApproveLogModel);

        //发送通知邮件
        try {
            advertMailService.sendSimpleMail(advertUserModel.getOaEmail(), mailTitle, mailContent);
        } catch (JMailException e) {
            log.error("发送审批通知邮件失败email=={} message=={} ", advertUserModel.getOaEmail(), e.getMessage());
        }
    }

    @Override
    public void updateStatus(UserStatusParams userStatusParams) {

        //校验用户状态
        AdvertUserModel advertUserModel = advertUserModelRepository.findByIdEquals(userStatusParams.getUserId());
        if (advertUserModel == null) {
            throw new JMException("用户不存在");
        }
        if (advertUserModel.getAuditStatus() != AdvertConstants.AUDIT_STATUS_PASS) {
            throw new JMException("用户未审核");
        }

        String mailTitle = AdvertConstants.MAIL_TEMPLATE_USER_AUDIT_TITLE;
        String mailContent;

        if (userStatusParams.getStatus() == AdvertConstants.ROLE_STATUS_ONLINE) {
            mailContent = AdvertConstants.MAIL_TEMPLATE_USER_STATUS_ONLINE.replace("{url}", AdvertConstants.ADVERT_SYSTEM_URL);
        } else if (userStatusParams.getStatus() == AdvertConstants.ROLE_STATUS_OFFLINE) {
            mailContent = AdvertConstants.MAIL_TEMPLATE_USER_STATUS_OFFLINE.replace("{url}", AdvertConstants.ADVERT_SYSTEM_URL);
        } else {
            throw new JMException("非法状态参数");
        }

        //更新用户状态
        advertUserModel.setStatus(userStatusParams.getStatus());
        advertUserModelRepository.saveAndFlush(advertUserModel);

        //发送通知邮件
        try {
            advertMailService.sendSimpleMail(advertUserModel.getOaEmail(), mailTitle, mailContent);
        } catch (Exception e) {
            log.error("发送审批通知邮件失败email=={} message=={} ", advertUserModel.getOaEmail(), e.getMessage());
        }
    }

    @Override
    public UserDetailVo getUserDetailByName(String userName) {

        //查询用户基本信息
        AdvertUserModel advertUser = advertUserModelRepository.findFirstByIsDeletedAndUsername(AdvertConstants.DELETE_FLAG_FALSE, userName);
        UserDetailVo userDetailVo = transUserModelToVo(advertUser);
        if(userDetailVo != null){
            //查询用户部门信息
            AdvertDepartmentModel advertDepartment = advertDepartmentModelRepository.findFirstByIdEquals(advertUser.getDepartmentId());
            //查询用户角色列表
            List<AdvertRoleModel> advertRoles = findAdvertRolesByUserId(advertUser.getId());
            userDetailVo.setDepartmentName(advertDepartment.getDepartmentName());
            userDetailVo.setRoleList(advertRoles);
        }

        return userDetailVo;
    }

    @Override
    public UserDetailVo getUserDetailById(Long userId) {

        //查询用户基本信息
        AdvertUserModel advertUser = advertUserModelRepository.findByIdEquals(userId);
        UserDetailVo userDetailVo = transUserModelToVo(advertUser);
        if(userDetailVo != null){
            //查询用户部门信息
            AdvertDepartmentModel advertDepartment = advertDepartmentModelRepository.findFirstByIdEquals(advertUser.getDepartmentId());
            //查询用户角色列表
            List<AdvertRoleModel> advertRoles = findAdvertRolesByUserId(advertUser.getId());
            userDetailVo.setDepartmentName(advertDepartment.getDepartmentName());
            userDetailVo.setRoleList(advertRoles);
        }

        return userDetailVo;
    }

    private UserDetailVo transUserModelToVo(AdvertUserModel advertUser) {

        if (advertUser == null) {
            return null;
        }
        String createTime = null;
        String confirmTime = null;
        if (advertUser.getCreateTime() != null) {
            createTime = TimeUtils.getLongDateStr(advertUser.getCreateTime());
        }
        if (advertUser.getAuditTime() != null) {
            confirmTime = TimeUtils.getLongDateStr(advertUser.getAuditTime());
        }
        return new UserDetailVo(advertUser.getId(), advertUser.getUsername(), null, advertUser.getDepartmentId(),
                advertUser.getOaEmail(), advertUser.getOaDepartmentName(), createTime, confirmTime, advertUser.getAuditStatus(), advertUser.getStatus(), null);
    }
}
