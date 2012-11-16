package com.bjsxt.oa.web;

import com.bjsxt.oa.SystemContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PagerFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		SystemContext.setOffset(getOffset(httpRequest));
		SystemContext.setPagesize(getPagesize(httpRequest));
		
		try{
			chain.doFilter(request, response);
		}finally{
			SystemContext.removeOffset();
			SystemContext.removePagesize();
		}
	}
	
	private int getOffset(HttpServletRequest request){
		int offset = 0;
		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (Exception ignore) {
		}
		return offset;
	}
	
	private int getPagesize(HttpServletRequest request){
		return 10;
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
