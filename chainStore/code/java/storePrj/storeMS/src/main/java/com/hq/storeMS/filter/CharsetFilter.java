package com.hq.storeMS.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;

public class CharsetFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		MainLog.info(LogModule.Tmp, "CharsetFilter", "test info");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}