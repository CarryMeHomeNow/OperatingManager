package com.tcl.uf.management.controller;

import clojure.lang.Obj;
import com.alibaba.fastjson.JSON;
import com.tcl.commondb.management.model.ManageUser;
import com.tcl.commondb.management.repository.ManageUserRepository;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.common.base.manage.RedisManager;
import com.tcl.uf.common.base.util.RandomGenerator;
import com.tcl.uf.management.param.role.LoginReq;
import com.tcl.uf.management.param.role.ManageRoleMenuResp;
import com.tcl.uf.management.schedule.oasync.UserDepartmentSyncService;
import com.tcl.uf.management.service.RoleService;
import com.tcl.uf.management.service.UserService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController extends AbstractBaseController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    private static final String LOGIN_ERROR_NUMBER = "_login_error_NUMBER_";
    @Autowired
    private ManageUserRepository manageUserRepository;
    @PostMapping("/in")
    public ResponseData in(@RequestBody LoginReq loginReq,HttpServletRequest request,HttpServletResponse res) {
        String code = RedisManager.get(loginReq.getVerKey());
        if(StringUtils.isEmpty(code) || !(loginReq.getVerCode().equals(code))){
            return fail("请填写正确验证码");
        }
        String username = loginReq.getUsername();
        String password = loginReq.getPassword();

        Integer errorNumber = 0;
        String usernameKey = LOGIN_ERROR_NUMBER + username;
        String errorNumberStr = RedisManager.get(usernameKey);
        if (!StringUtils.isEmpty(errorNumberStr)) {
            errorNumber = Integer.valueOf(errorNumberStr);
        }
        if (errorNumber > 8) {
            return fail("当天账号密码错误尝试超过8次, 24小时后再试");
        }
        ManageUser member = manageUserRepository.findFirstByUsername(username);
        if (member == null) {
            // 账号不存在
            return fail("账号密码错误");
        }
        if (member.isFreeze()) {
            return fail("账号已冻结");
        }
        boolean b = userService.validMemberPassword(loginReq.getUsername(), loginReq.getPassword());
        if (!b) {
            // IDM账号登入
            boolean result = userService.IDMLogin(username, password);
            if (!result) {
                return fail("账号密码错误");
            }
        }

        TokenData td = new TokenData();
        td.setMid(member.getId());
        td.setUsername(member.getUsername());

        String token = RandomGenerator.getStr(32);

        Cookie c = new Cookie("UBToken", token);
        res.addCookie(c);

        RedisManager.set(usernameKey, "0", 24 * 60 * 60);
        RedisManager.set("_login_client_uid_" + td.getMid(), td.getClientUid());
        RedisManager.set(token, JSON.toJSONString(td));
        ManageRoleMenuResp roles = roleService.roleMenus(member.getId());
        Map<String,Object> map = new HashMap<>();
        map.put("role",roles);
        map.put("user",member);
        map.put("token", token);
        return success(map);

    }
    @Autowired
    private RoleService roleService;
    @PostMapping("/out")
    public ResponseData out() throws Exception{
        return success(roleService.roleMenus(1L));
    }
    @RequestMapping("/captcha")
    public Object captchaweb(HttpServletRequest request, HttpServletResponse response)throws Exception{
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        RedisManager.set(key, verCode, 120);
        Map<String, Object> map =new HashMap<>();
        map.put("key",key);
        map.put("image",specCaptcha.toBase64());
        return success(map);
    }
    @RequestMapping("/t1")
    public void t1(HttpServletRequest request, HttpServletResponse response)throws Exception{
        TokenAppUserInfo appUserInfo = getAppUserInfo(request);
        log.info("----------APPTOKEN:{}",JSON.toJSONString(appUserInfo));
    }
    @Autowired
    private UserDepartmentSyncService userDepartmentSyncService;
    @RequestMapping("/t2")
    public void t2(HttpServletRequest request, HttpServletResponse response)throws Exception{
        userDepartmentSyncService.userSyncAll();
//        log.info("----------MANAGETOKEN:{}",JSON.toJSONString(manageUserInfo));
    }


}
