package com.tcl.uf.common.base.util.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.tcl.uf.common.base.constant.RequestHeader.X_AUTH_ID;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: HttpHeaderUtils
 * @Description:
 * @date 2018/3/18 下午2:36
 */

public class HttpHeaderUtils {

	private static final Logger log = LoggerFactory.getLogger(HttpHeaderUtils.class);
	
    /**
     * 企业微信客户端
     */
    public static final String CLIENT_WX_WORK = "client_wx_work";

    /**
     * 微信客户端
     */
    public static final String CLIENT_WX_APP = "client_wx_app";

    /**
     * 普通客户端
     */
    public static final String CLIENT_NORMAL = "client_normal";

    /**
     * chrome
     */
    public static final String CLIENT_CHROME = "client_chrome";

    /**
     * 在线APP
     */
    public static final String CLIENT_ONLINE_APP = "client_online_app";


    /**
     * 其他普通客户端
     */
    public static final String CLIENT_UNKNOWN = "client_unknown";

    /**
     * 解析客户端类型
     *
     * @param request
     * @return
     */
    public static String parseClientType(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        if (StringUtils.isEmpty(userAgent)) {
            return CLIENT_UNKNOWN;
        }

        return parseClientType(userAgent);
    }

    public static String parseClientType(String userAgent) {
        if (StringUtils.isEmpty(userAgent)) {
            return CLIENT_UNKNOWN;
        }

        if (userAgent.contains("wxwork")) {
            return CLIENT_WX_WORK;
        }

        if (userAgent.contains("MicroMessenger")) {
            return CLIENT_WX_APP;
        }

        if (userAgent.contains("MissonWebkit")) {
            return CLIENT_ONLINE_APP;
        }

        if (userAgent.contains("Chrome")) {
            return CLIENT_CHROME;
        }

        return CLIENT_UNKNOWN;
    }

    public static Long getAuthUid(HttpServletRequest request) {
        String xAuthId = request.getHeader(X_AUTH_ID);
        return Long.valueOf(xAuthId);
    }
    

    

    

    
    public final static String getIpAddress(HttpServletRequest request) {  
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址  
        String ip = request.getHeader("X-Forwarded-For");  
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {  
        	ip = request.getHeader("Proxy-Client-IP");  
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_CLIENT_IP");  
            }  
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
            }  
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getRemoteAddr();  
            }  
        } else if (ip.length() > 15) {  
            String[] ips = ip.split(","); 
            for (int index = 0; index < ips.length; index++) {  
                String strIp = (String) ips[index];  
                if (!("unknown".equalsIgnoreCase(strIp))) {  
                    ip = strIp;  
                    break;  
                }  
            }
        }  
        return ip;  
    }
}
