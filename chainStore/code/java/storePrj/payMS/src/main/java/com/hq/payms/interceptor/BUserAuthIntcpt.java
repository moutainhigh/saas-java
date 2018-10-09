package com.hq.payms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.service.auth.buser.BUserAuthUtils;
import com.hq.payms.service.common.RespStatus;

/**
 * 与storeMS做session共享 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class BUserAuthIntcpt extends HandlerInterceptorAdapter  {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MainLog.info(LogModule.Tmp, "BUserAuthIntcpt[preHandle]", "test info");
		Subject subject = BUserAuthUtils.getInstance().setSubjectTL(request);
		if(subject == null){
			response.setStatus(RespStatus.SESSION_EXPIRED.getCode());
            return false;
		}
//		if((System.currentTimeMillis() - subject.getSession().getStartTimestamp().getTime()) >= ServerConstants.SESSION_EFFECTIVE_TIME){
//			response.setStatus(RespStatus.SESSION_EXPIRED.getCode());
//			return false;
//		}

		return super.preHandle(request, response, handler);
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		MainLog.info(LogModule.Tmp, "BUserAuthIntcpt[postHandle]", "test info");
		BUserAuthUtils.getInstance().removeSubjectTL();
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
