package com.tcl.uf.advert.exception;

import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.dto.ResponseData;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AuthorizationExceptionHandler
 * @Description:
 * @date 2020/8/18 21:47
 */
@ControllerAdvice
public class ShiroExceptionHandler extends AbstractBaseController {

    private static final Logger _log = LoggerFactory.getLogger(ShiroExceptionHandler.class);

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public ResponseData AuthorizationExceptionHandler(AuthorizationException e) {
        _log.error("没有通过权限验证！", e);
        return fail(AdvertConstants.AUTH_STATUS_CODE_UNAUTH,"未通过权限验证："+e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseData AuthenticationExceptionHandler(AuthenticationException e) {
        _log.error("没有通过权限验证！", e);
        return fail(AdvertConstants.AUTH_STATUS_CODE_UNAUTH,"未通过权限验证："+e.getMessage());
    }
}
