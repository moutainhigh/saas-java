package com.hq.storeMS.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.auth.opUser.OPUserAuthUtils;
import com.hq.storeMS.service.common.RespStatus;

public class OPAuthIntcpt extends HandlerInterceptorAdapter  {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MainLog.info(LogModule.Tmp, "OPAuthIntcpt[preHandle]", "test info");
		Subject subject = OPUserAuthUtils.getInstance().setSubjectTL(request);
		if(subject == null){
			response.setStatus(RespStatus.SESSION_EXPIRED.getCode());
            return false;
		}
		
		if((System.currentTimeMillis() - subject.getSession().getStartTimestamp().getTime()) >= ServerConstants.SESSION_EFFECTIVE_TIME){
			response.setStatus(RespStatus.SESSION_EXPIRED.getCode());
			return false;
		}
		return super.preHandle(request, response, handler);
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		MainLog.info(LogModule.Tmp, "OPAuthIntcpt[postHandle]", "test info");
		BUserAuthUtils.getInstance().removeSubjectTL();
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
