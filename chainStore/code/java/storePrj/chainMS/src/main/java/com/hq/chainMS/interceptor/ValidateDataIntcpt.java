package com.hq.chainMS.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.common.validate.ValidateInfoThreadLocal;
import com.hq.chainMS.common.validate.info.ValidateInfo;
import com.hq.chainMS.service.auth.AuthConstants;
import com.zenmind.common.json.JsonUtil;

public class ValidateDataIntcpt extends HandlerInterceptorAdapter  {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MainLog.info(LogModule.Tmp, "ValidateDataIntcpt[preHandle]", "test info");
		ValidateInfoThreadLocal.getInstance().set(null);
		
		String validateInfo = request.getHeader(AuthConstants.HEADER_ACCESS_VALIDATE_INFO);
		if(StringUtils.isNotBlank(validateInfo)){
			ValidateInfo info = JsonUtil.getInstance().fromJson(validateInfo, ValidateInfo.class);
			ValidateInfoThreadLocal.getInstance().set(info);
		}
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		MainLog.info(LogModule.Tmp, "ValidateDataIntcpt[postHandle]", "test info");
		ValidateInfoThreadLocal.getInstance().set(null);
		super.postHandle(request, response, handler, modelAndView);
	}
}
