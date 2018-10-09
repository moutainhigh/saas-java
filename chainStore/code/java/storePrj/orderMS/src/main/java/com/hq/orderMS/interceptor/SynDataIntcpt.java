package com.hq.orderMS.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hq.orderMS.common.datasyn.DataSynThreadLocal;
import com.hq.orderMS.common.datasyn.MsDataSynMgr;
import com.hq.orderMS.common.datasyn.info.DataSynVerInfo;
import com.hq.orderMS.common.log.LogModule;
import com.hq.orderMS.common.log.MainLog;
import com.zenmind.dataSyn.DataSynMgr;

public class SynDataIntcpt extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MainLog.info(LogModule.Tmp, "SynDataInterceptor[preHandle]", "test info");
		DataSynThreadLocal.getInstance().set(null);

		String verJsonData = request.getHeader(MsDataSynMgr.getInstance().DATA_SYN_REQ);
		if (StringUtils.isNotBlank(verJsonData)) {
			DataSynVerInfo synVerData = DataSynMgr.getInstance().fromClientData(DataSynVerInfo.class, verJsonData);
			DataSynThreadLocal.getInstance().set(synVerData);
		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		MainLog.info(LogModule.Tmp, "SynDataInterceptor[postHandle]", "test info");
		DataSynThreadLocal.getInstance().set(null);
		super.postHandle(request, response, handler, modelAndView);
	}

}
