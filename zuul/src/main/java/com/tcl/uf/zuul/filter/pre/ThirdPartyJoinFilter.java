package com.tcl.uf.zuul.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.AesUtils;
import com.tcl.uf.zuul.manager.ZuulCache;
import com.tcl.uf.zuul.model.RouterRuleV;
import com.tcl.uf.zuul.model.ThirdPartyJoinVo;
import com.tcl.uf.zuul.repository.RouterRuleRepository;
import com.tcl.uf.zuul.repository.ThirdPartyJoinVoRepository;
import com.tcl.uf.zuul.route.DbRouteLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.tcl.uf.zuul.common.KeyAttribute.CTX_RESPONSE_JSON;


/**
 * @author chiwm@kuyumall.com
 * @ClassName: LoginStatusFilter
 * @Description:
 * @date 2018/1/17 上午11:42
 */


@Component
public class ThirdPartyJoinFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(ThirdPartyJoinFilter.class);

    private static final String THIRDPARTY = "THDP";
    
    private static String POST = "POST";
    
    private static String GET = "GET";
    
//    @Autowired
//    SystemService systemService;

    @Autowired
	DbRouteLocator routeLocator;

    @Autowired
	RouterRuleRepository routerRuleRepository;
    
    @Autowired
	ThirdPartyJoinVoRepository thirdPartyJoinVoRepository;

    public ThirdPartyJoinFilter() {

    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 3;
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

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        Route matchingRoute = routeLocator.getMatchingRoute(request.getRequestURI());
        String serviceId = matchingRoute.getId();
        if (serviceId == null) {
        	return null;
        }
        
        // 获取匹配信息的匹配KEY.
        String path = matchingRoute.getFullPath();

        // 登入态校验
        // login 信息从内存/缓存中读取
        RouterRuleV service = ZuulCache.getRouterRuleV(path);
        if (service == null) {
            return null;
        }
        String resource = request.getParameter("resource");
        if(THIRDPARTY.equals(resource)){
        	
        	String appid = request.getParameter("appid");
        	String method = request.getMethod();
        	if(StringUtils.isEmpty(appid)){
        		error(ctx,"appid 不能为空","appid required");
        		return null;
        	}
        	ThirdPartyJoinVo vo = thirdPartyJoinVoRepository.getFirstByAppidEquals(appid);
        	
        	if(!vo.getInterfaceList().contains(service.getPath())){
        		errorPermission(ctx);
        		return null;
        	}

        	if(GET.equals(method.toUpperCase())){
        		try {
					String paramAES = request.getParameter("param");
					String params = AesUtils.AESDncode(vo.getAppSecret(), paramAES);
					String[] arrs = params.split("@@");
					Map<String, List<String>> qp = ctx.getRequestQueryParams();
					for(String param:arrs){
						String[] arr = param.split("=");
						List<String> list = new LinkedList<>();
						list.add(arr[1]);
						qp.put(arr[0], list);
					}
					ctx.setRequestQueryParams(qp);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}else if(POST.equals(method)){
        		try {
	        		InputStream in = (InputStream) request.getInputStream();
	        		HttpServletRequest requestWrapper = null; 
	        		if(request instanceof HttpServletRequest) {
	        				String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
	        				String params = AesUtils.AESDncode(vo.getAppSecret(), URLDecoder.decode(body, "utf-8"));
	        				ctx.setRequest(new HttpServletRequestWrapper(request) {
	                            @Override
	                            public ServletInputStream getInputStream() throws IOException {
	                                return new ServletInputStreamWrapper(params.getBytes());
	                            }

	                            @Override
	                            public int getContentLength() {
	                                return params.getBytes().length;
	                            }

	                            @Override
	                            public long getContentLengthLong() {
	                                return params.getBytes().length;
	                            }
	                        });
	        				ctx.addZuulRequestHeader("Content-type", "application/json");
	        				return null;
	        		} 
        		} catch (Exception e) {
        			e.printStackTrace();
        		}   
        	}
        }

        return null;
    }
    
    //获取request请求body中参数  
	public String getBodyString(BufferedReader br) {  
		String inputLine;  
		     String str = "";  
	   try {  
	     while ((inputLine = br.readLine()) != null) {  
	      str += inputLine;
	     }  
	     br.close();  
	   } catch (IOException e) {  
	     System.out.println("IOException: " + e);  
		   }  
		   return str;  
	}
    
    private void errorPermission(RequestContext ctx) {
        error(ctx, "没有接口访问权限", "interface permission denied");
    }

    private void error(RequestContext ctx, String msgZ, String msgE) {
        ctx.set(CTX_RESPONSE_JSON, new ResponseData<>(-1, msgZ, msgE));
        ctx.setSendZuulResponse(false);
    }
}
