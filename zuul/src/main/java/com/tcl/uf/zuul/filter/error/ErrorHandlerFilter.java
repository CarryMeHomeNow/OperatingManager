package com.tcl.uf.zuul.filter.error;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.tcl.uf.common.base.dto.ResponseData;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import static com.tcl.uf.zuul.common.KeyAttribute.CTX_RESPONSE_JSON;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: ErrorHandlerFilter
 * @Description:
 * @date 2018/2/5 下午3:44
 */

@Component
public class ErrorHandlerFilter extends ZuulFilter {

    private static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";

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
        return "post";
    }

    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();

//        return ctx.containsKey("error.status_code")
//                && !ctx.getBoolean(SEND_ERROR_FILTER_RAN, false);
        return false;
    }

    /**
     * if shouldFilter() is true, this method will be invoked. this method is the core method of a ZuulFilter
     *
     * @return Some arbitrary artifact may be returned. Current implementation ignores it.
     */
    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();

            // 屏蔽zuul默认的错误处理
            ctx.set(SEND_ERROR_FILTER_RAN, true);

            int statusCode = (Integer) ctx.get("error.status_code");

            ctx.setResponseStatusCode(200);

            ResponseData responseData = new ResponseData();
            responseData.setCode(statusCode);

            if (ctx.containsKey("error.exception")) {
                Exception e = (Exception) ctx.get("error.exception");
                responseData.setMsgE(e.getMessage());

            }

            if (ctx.containsKey("error.message")) {
                String message = (String) ctx.get("error.message");
                responseData.setMsgZ(message);
            }

            ctx.set(CTX_RESPONSE_JSON, responseData);
        } catch (Exception ex) {
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }
}
