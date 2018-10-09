package com.hq.thirdPartyServer.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	private static final String encoding = "utf-8";

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (encoding != null) {

			servletRequest.setCharacterEncoding(encoding);
			servletResponse.setCharacterEncoding(encoding);
			filterChain.doFilter(servletRequest, servletResponse);

		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}