package com.tcl.uf.zuul.filter.pre;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.tcl.uf.zuul.common.dto.RequestLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: RequestLogFilter
 * @Description:
 * @date 2018/5/17 下午3:36
 */

@Component
public class RequestLogFilter extends ZuulFilter {

    private final static Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);

    /**
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     *
     * @return A String representing that type
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * if shouldFilter() is true, this method will be invoked. this method is the core method of a ZuulFilter
     *
     * @return Some arbitrary artifact may be returned. Current implementation ignores it.
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        RequestLog reqInfo = new RequestLog();

        // Request Method
        reqInfo.setMethod(request.getMethod());

        // Request URI
        reqInfo.setUri(request.getRequestURI());

        // Request Host
        reqInfo.setHost(request.getRemoteHost());

        // Request Param
        switch (request.getMethod()) {
            case "GET":
                reqInfo.setParam(request.getQueryString());
                break;
            case "POST":
                reqInfo.setParam("not parse now");
                break;
            default:
                break;
        }


        reqInfo.setClient(null);

        logger.info(JSON.toJSONString(reqInfo));
        return null;
    }
}
