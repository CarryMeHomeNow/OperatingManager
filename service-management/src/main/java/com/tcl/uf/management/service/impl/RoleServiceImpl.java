package com.tcl.uf.management.service.impl;

import com.tcl.commondb.management.model.ManageMenu;
import com.tcl.commondb.management.model.ManageMenuRole;
import com.tcl.commondb.management.model.ManageRole;
import com.tcl.commondb.management.model.ManageUserRole;
import com.tcl.commondb.management.repository.ManageMenuRepository;
import com.tcl.commondb.management.repository.ManageMenuRoleRepository;
import com.tcl.commondb.management.repository.ManageRoleRepository;
import com.tcl.commondb.management.repository.ManageUserRoleRepository;
import com.tcl.uf.common.base.constant.CommonConstant;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.BeanCopyUtils;
import com.tcl.uf.management.param.role.ManageMenuResp;
import com.tcl.uf.management.param.role.ManageRoleAddReq;
import com.tcl.uf.management.param.role.ManageRoleMenuResp;
import com.tcl.uf.management.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private ManageRoleRepository manageRoleRepository;
    @Autowired
    private ManageUserRoleRepository manageUserRoleRepository;
    @Autowired
    private ManageMenuRepository menuRepository;
    @Autowired
    private ManageMenuRoleRepository manageMenuRoleRepository;

    @Override
    public List<ManageRole> listByMid(Long mid) {
        ManageUserRole manageUserRole = manageUserRoleRepository.findFirstBymId(mid);
        if (null == manageUserRole) return new ArrayList<>();
        ManageRole one = manageRoleRepository.findFirstByIdAndIsDelete(manageUserRole.getcId(), CommonConstant.NO);
        List<ManageRole> result = manageRoleRepository.findAllByParentIdAndIsDelete(manageUserRole.getcId(),CommonConstant.NO);
        result.add(one);
        return result;
    }

    @Override
    public List<ManageRoleMenuResp> roleMenuListByMid(Long mid) {
        Map<Long, List<Integer>> role_menuids = new HashMap<>();
        List<ManageRoleMenuResp> resultList = new ArrayList<>();
        List<ManageRole> roles = listByMid(mid);
        List<ManageMenu> firstMenu = menuRepository.findAllByParentId(0);
        roles.forEach(e -> {
            ManageRoleMenuResp m = BeanCopyUtils.map(e, ManageRoleMenuResp.class);
            List<ManageMenuResp> firstmenuDto = BeanCopyUtils.mapList(firstMenu, ManageMenuResp.class);
            List<ManageMenuRole> hasMenuList = manageMenuRoleRepository.findAllBycId(e.getId());
            List<Integer> menuIds = hasMenuList.stream().map(ManageMenuRole::getMenuId).collect(Collectors.toList());
            role_menuids.put(e.getId(), menuIds);
            firstmenuDto.forEach(f -> {
                if (menuIds.contains(f.getMenuId())) {
                    f.setIsAuth(1);
                }
                if (e.getCharacte().equals("root")) {
                    f.setIsAuth(1);
                }
            });
            m.setChildren(firstmenuDto);
            resultList.add(m);
        });
        resultList.forEach(e -> {
            e.getChildren().forEach(f -> {
                List<ManageMenu> secendMenu = menuRepository.findAllByParentId(f.getMenuId());
                List<ManageMenuResp> secendMenuDto = BeanCopyUtils.mapList(secendMenu, ManageMenuResp.class);
                secendMenuDto.forEach(c -> {
                    if (role_menuids.get(e.getId()).contains(c.getMenuId())) {
                        c.setIsAuth(1);
                    }
                    if (e.getCharacte().equals("root")) {
                        c.setIsAuth(1);
                    }
                    List<ManageMenu> thirdMenu = menuRepository.findAllByParentId(c.getMenuId());
                    List<ManageMenuResp> thirdMenuDto = BeanCopyUtils.mapList(thirdMenu, ManageMenuResp.class);
                    thirdMenuDto.forEach(t->{
                        if (role_menuids.get(e.getId()).contains(t.getMenuId())) {
                            t.setIsAuth(1);
                        }
                        if (e.getCharacte().equals("root")) {
                            t.setIsAuth(1);
                        }
                    });
                    c.setChildren(thirdMenuDto);
                });
                f.setChildren(secendMenuDto);
            });
        });
        return resultList;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addRoleMenu(ManageRoleAddReq manageRoleAddReq) {
        long cuTime = System.currentTimeMillis();
        if (manageRoleAddReq.getMenuIds() == null) {
            throw new JMException("缺少数据");
        }
        ManageRole firstByName = manageRoleRepository.findFirstByName(manageRoleAddReq.getName());
        if (null != firstByName) {
            throw new JMException("角色名重复");
        }
        ManageUserRole ro = manageUserRoleRepository.findFirstBymId(manageRoleAddReq.getMid());
        ManageRole manageRole = new ManageRole();
        manageRole.setCharacte(manageRoleAddReq.getName());
        manageRole.setName(manageRoleAddReq.getName());
        manageRole.setCreateTime(cuTime);
        manageRole.setParentId(ro.getcId());
        manageRole.setIsDelete((byte) 1);
        manageRoleRepository.saveAndFlush(manageRole);
        List<ManageMenuRole> manageMenuRoleList = new ArrayList<>();
        manageRoleAddReq.getMenuIds().forEach(e -> {
            ManageMenuRole r = new ManageMenuRole();
            r.setcId(manageRole.getId());
            r.setMenuId(e);
            r.setCreateTime(cuTime);
            manageMenuRoleList.add(r);
        });
        manageMenuRoleRepository.saveAll(manageMenuRoleList);
//        manageMenuRoleRepository.save(manageMenuRoleList);
        return true;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delRole(Long roleId) {
        ManageRole one = manageRoleRepository.findFirstByIdAndIsDelete(roleId,CommonConstant.NO);
        if(null == one){throw new JMException("角色不存在");}
        one.setIsDelete((byte)2);
        manageRoleRepository.save(one);
    }

    @Override
    public ManageRoleMenuResp roleMenus(Long mid) {
        Map<Long, List<Integer>> role_menuids = new HashMap<>();
        ManageUserRole mu = manageUserRoleRepository.findFirstBymId(mid);
        ManageRole role = manageRoleRepository.findFirstByIdAndIsDelete(mu.getcId(),CommonConstant.NO);
        List<ManageMenu> firstMenu = menuRepository.findAllByParentId(0);
        ManageRoleMenuResp m = BeanCopyUtils.map(role, ManageRoleMenuResp.class);
        List<ManageMenuResp> firstmenuDto = BeanCopyUtils.mapList(firstMenu, ManageMenuResp.class);
        List<ManageMenuRole> hasMenuList = manageMenuRoleRepository.findAllBycId(role.getId());
        List<Integer> menuIds = hasMenuList.stream().map(ManageMenuRole::getMenuId).collect(Collectors.toList());
        role_menuids.put(role.getId(), menuIds);
        firstmenuDto.forEach(f -> {
            if (menuIds.contains(f.getMenuId())) {
                f.setIsAuth(1);
            }
            if (role.getCharacte().equals("root")) {
                f.setIsAuth(1);
            }
        });
        m.setChildren(firstmenuDto);
        m.getChildren().forEach(f -> {
            List<ManageMenu> secendMenu = menuRepository.findAllByParentId(f.getMenuId());
            List<ManageMenuResp> secendMenuDto = BeanCopyUtils.mapList(secendMenu, ManageMenuResp.class);
            secendMenuDto.forEach(c -> {
                if (role_menuids.get(m.getId()).contains(c.getMenuId())) {
                    c.setIsAuth(1);
                }
                if (m.getCharacte().equals("root")) {
                    c.setIsAuth(1);
                }
                List<ManageMenu> thirdMenu = menuRepository.findAllByParentId(c.getMenuId());
                List<ManageMenuResp> thirdMenuDto = BeanCopyUtils.mapList(thirdMenu, ManageMenuResp.class);
                thirdMenuDto.forEach(t->{
                    if (role_menuids.get(m.getId()).contains(t.getMenuId())) {
                        t.setIsAuth(1);
                    }
                    if (m.getCharacte().equals("root")) {
                        t.setIsAuth(1);
                    }
                });
                c.setChildren(thirdMenuDto);
            });
            f.setChildren(secendMenuDto);
        });

        return m;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean editRoleMenu(ManageRoleAddReq manageRoleAddReq) {
        long local = System.currentTimeMillis();
        ManageRole role = manageRoleRepository.findFirstByIdAndIsDelete(manageRoleAddReq.getId(),CommonConstant.NO);
        if (role == null) {
            throw new JMException("角色不存在");
        }
        if (StringUtils.isNotEmpty(manageRoleAddReq.getName())) {
            role.setName(manageRoleAddReq.getName());
            role.setCharacte(manageRoleAddReq.getName());
            manageRoleRepository.save(role);
        }
        manageMenuRoleRepository.deleteAllBycId(role.getId());
        List<ManageMenuRole> upList = new ArrayList<>();
        manageRoleAddReq.getMenuIds().forEach(e -> {
            ManageMenuRole v = new ManageMenuRole();
            v.setcId(role.getId());
            v.setMenuId(e);
            v.setCreateTime(local);
            upList.add(v);
        });
        manageMenuRoleRepository.saveAll(upList);
//        manageMenuRoleRepository.save(upList);
        return true;
    }
}
