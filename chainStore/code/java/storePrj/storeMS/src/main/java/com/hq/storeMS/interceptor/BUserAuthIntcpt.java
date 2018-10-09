package com.hq.storeMS.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.validate.info.ValidateInfo;
import com.hq.storeMS.service.auth.AuthConstants;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.RespStatus;
import com.zenmind.common.json.JsonUtil;

public class BUserAuthIntcpt extends HandlerInterceptorAdapter  {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MainLog.info(LogModule.Tmp, "BUserAuthIntcpt[preHandle]", "test info");
		if(isFromOtherMS(request)) {
			return true;
		}
		
		Subject subject = BUserAuthUtils.getInstance().setSubjectTL(request);
		if(subject == null){
			response.setStatus(RespStatus.SESSION_EXPIRED.getCode());
            return false;
		}
		return super.preHandle(request, response, handler);
	}
	
	// 是否是第三方MS的调用
	private boolean isFromOtherMS(HttpServletRequest request) {
		String validateInfo = request.getHeader(AuthConstants.HEADER_ACCESS_VALIDATE_INFO);
		if (StringUtils.isNotBlank(validateInfo)) {
			ValidateInfo info = JsonUtil.getInstance().fromJson(validateInfo, ValidateInfo.class);
			int appId = info.getAppId();

			// 校验appId的合法性 这里暂时只==1
			if(appId == 1) {
				ThreadContext.unbindSubject();
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		MainLog.info(LogModule.Tmp, "BUserAuthIntcpt[postHandle]", "test info");
		BUserAuthUtils.getInstance().removeSubjectTL();
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
