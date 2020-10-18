package com.tcl.uf.advert.shiro;

import com.tcl.commondb.advert.model.AdvertRoleModel;
import com.tcl.commondb.advert.model.AdvertUserModel;
import com.tcl.commondb.management.model.ManageRole;
import com.tcl.commondb.management.model.ManageUser;
import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.service.AdvertUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertRealm
 * @Description:人员权限校验
 * @date 2020/8/17 18:41
 */
public class AdvertRealm extends AuthorizingRealm {

    @Autowired
    private AdvertUserService advertUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //查询广告平台用户
        AdvertUserModel user = advertUserService.findUserByName(username);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        if (user == null) {
            //查询用户名称
            ManageUser manageUser = advertUserService.findManageUserByName(username);
            //查询用户管理后台角色
            ManageRole userRole = advertUserService.findManageRoleByMid(manageUser.getId());
            if(userRole.getCharacte().equals(AdvertConstants.MANAGEMENT_ROLE_ADMIN_NAME)){
                simpleAuthorizationInfo.addRole(AdvertConstants.ADVERT_ROLE_ADMIN);
            }
        } else {
            //查询用户角色列表
            List<AdvertRoleModel> advertRoles = advertUserService.findAdvertRolesByUserId(user.getId());

            for (AdvertRoleModel role : advertRoles) {
                //添加角色
                simpleAuthorizationInfo.addRole(role.getName());
                /*//添加权限
                for (Permissions permissions : role.getPermissions()) {
                    simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
                }*/
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String username = authenticationToken.getPrincipal().toString();
        String password;
        //查询用户名称
        AdvertUserModel user = advertUserService.findUserByName(username);
        if (user == null) {
            //查询平台用户权限
            ManageUser manageUser = advertUserService.findManageUserByName(username);
            //查询用户管理后台角色
            ManageRole userRole = advertUserService.findManageRoleByMid(manageUser.getId());
            //判断是否平台管理员权限
            if(userRole.getCharacte().equals(AdvertConstants.MANAGEMENT_ROLE_ADMIN_NAME)){
                password = manageUser.getId().toString();
            }else{
                //这里返回后会报出对应异常
                throw new AuthenticationException("此用户不存在");
            }
        }else{
            if (user.getAuditStatus() != AdvertConstants.AUDIT_STATUS_PASS) {
                //这里返回后会报出对应异常
                throw new AuthenticationException("此用户权限未通过审核");
            }
            if (user.getStatus() != AdvertConstants.ROLE_STATUS_ONLINE) {
                //这里返回后会报出对应异常
                throw new AuthenticationException("此用户权限未启用");
            }
            password = user.getMid().toString();
        }

        //这里验证authenticationToken和simpleAuthenticationInfo的信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return simpleAuthenticationInfo;
    }
}
