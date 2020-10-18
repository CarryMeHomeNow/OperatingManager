package com.tcl.uf.advert.filter;

import com.alibaba.fastjson.JSON;
import com.tcl.uf.common.base.constant.RequestHeader;
import com.tcl.uf.common.base.dto.TokenData;
import com.tcl.uf.common.base.manage.RedisManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertAccessFilter
 * @Description:用户权限验证
 * @date 2020/8/17 18:48
 */
public class AdvertAccessFilter extends AccessControlFilter {

    private static final Logger _log = LoggerFactory.getLogger(AdvertAccessFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        try {
            if (!subject.isAuthenticated()) {
                HttpServletRequest request = (HttpServletRequest) servletRequest;
                String ubtoken = request.getHeader(RequestHeader.CTX_REQUEST_UBTOKEN);
                if (StringUtils.isEmpty(ubtoken)) {
                    return false;
                }
                String user = RedisManager.get(ubtoken);
                TokenData td = JSON.parseObject(user, TokenData.class);
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(td.getUsername(), td.getMid().toString());
                subject.login(usernamePasswordToken);
            }
        } catch (Exception e) {
            _log.error("权限校验未通过:"+e.getMessage());
            e.printStackTrace();
            redirectToLogin(servletRequest,servletResponse);
        }
        return true;
    }
}
