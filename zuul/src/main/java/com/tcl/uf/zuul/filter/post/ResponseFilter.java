package com.tcl.uf.zuul.filter.post;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.tcl.uf.common.base.dto.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tcl.uf.zuul.common.KeyAttribute.CTX_RESPONSE_HTML;
import static com.tcl.uf.zuul.common.KeyAttribute.CTX_RESPONSE_JSON;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: ResponseFilter
 * @Description:
 * @date 2018/2/5 下午2:52
 */


@Component
public class ResponseFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(ResponseFilter.class);

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
        return 99;
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

        // FIXME 目前支持 JSON / HTML数据返回,后期支持更多格式的返回.

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();

        int statusCode = ctx.getResponseStatusCode();

        if (statusCode <= 99 || statusCode >= 1000) {
            statusCode = HttpServletResponse.SC_OK;
        }

        try {

            // 1. json格式返回
            ResponseData json = (ResponseData) ctx.get(CTX_RESPONSE_JSON);
            if (json != null) {
                response.setHeader("Content-type", "application/json;charset=utf-8");
                response.setStatus(statusCode);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(JSON.toJSONBytes(json));
                outputStream.flush();
                return null;
            }

            // 2. html格式返回
            String html = (String) ctx.get(CTX_RESPONSE_HTML);
            if (html != null) {
                response.setHeader("Content-type", "text/html;charset=utf-8");
                response.setStatus(statusCode);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(html.getBytes("utf-8"));
                outputStream.flush();
                return null;
            }

        } catch (IOException e) {
            log.error("response filter", e);
        }
        return null;
    }
}
