package com.tcl.uf.zuul.filter.pre;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class InhanceHttpServletRequestWrapper extends HttpServletRequestWrapper{  
	
	private HttpServletRequest req;
	private Map<String, String[]> parameters;
	
	public InhanceHttpServletRequestWrapper(HttpServletRequest request,Map<String, String[]> parameters) {
        super(request);
        req = request;
        this.parameters = parameters;
    }
	
	@Override
    public String[] getParameterValues(String name) {
        if (parameters == null) return null;
        String[] arr = parameters.get(name);
        if (arr == null)
            return null;
        return arr.clone();
    }
	
//    @Override
//    public String getQueryString() {
//        return this._getHttpServletRequest().getQueryString();
//    }
	
	@Override
    public Enumeration getParameterNames() {
        return new Enumeration<String>() {
            private String[] arr = getParameters().keySet().toArray(new String[0]);
            private int idx = 0;

            @Override
            public boolean hasMoreElements() {
                return idx < arr.length;
            }

            @Override
            public String nextElement() {
                return arr[idx++];
            }
        };
    }
	
    @Override
    public Map getParameterMap() {
        return getParameters();
    }
	
    @Override
    public String getParameter(String name) {
        if (parameters == null) return null;
        String[] values = parameters.get(name);
        if (values == null || values.length == 0)
            return null;
        return values[0];
    }
	
	public HashMap<String, String[]> getParameters() {
        if (parameters == null) return null;
        HashMap<String, String[]> map = new HashMap<String, String[]>(parameters.size() * 2);
        for (String key : parameters.keySet()) {
            map.put(key, parameters.get(key).clone());
        }
        return map;
    } 
}  
