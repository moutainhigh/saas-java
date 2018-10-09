package com.hq.chainMS.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.zenmind.monitor.tracer.ThreadTraceMgr;

public class ThreadTraceIntcpt extends HandlerInterceptorAdapter  {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MainLog.info(LogModule.ThreadTracer, "ThreadTraceIntcpt[preHandle]", "start");
		
		String url = request.getServletPath();
		String pathInfo = request.getPathInfo();
		if (pathInfo != null && pathInfo.length() > 0) {
			url = url + pathInfo;
		}
		
		ThreadTraceMgr.getInstance().threadStart(url);
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		MainLog.info(LogModule.ThreadTracer, "ThreadTraceIntcpt[preHandle]", "end");
		ThreadTraceMgr.getInstance().threadEnd();
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
