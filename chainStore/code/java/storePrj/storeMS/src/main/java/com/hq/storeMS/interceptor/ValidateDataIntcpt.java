package com.hq.storeMS.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.common.validate.info.ValidateInfo;
import com.hq.storeMS.service.auth.AuthConstants;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.StoreRO;
import com.zenmind.common.json.JsonUtil;

public class ValidateDataIntcpt extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MainLog.info(LogModule.Tmp, "ValidateDataIntcpt[preHandle]", "test info");
		ValidateInfoThreadLocal.getInstance().set(null);

		String validateInfo = request.getHeader(AuthConstants.HEADER_ACCESS_VALIDATE_INFO);
		if (StringUtils.isNotBlank(validateInfo)) {
			ValidateInfo info = JsonUtil.getInstance().fromJson(validateInfo, ValidateInfo.class);
			ValidateInfoThreadLocal.getInstance().set(info);
		} else {// 兼容以前的版本 header不带validateInfo信息的情况
			putDefaultValidateInfo();
		}
		return super.preHandle(request, response, handler);
	}

	private void putDefaultValidateInfo() {
		try {
			Long sessionBUserId = BUserAuthUtils.getInstance().getSessionBUserId();
			if (sessionBUserId != null) {
				BUser buser = BUserQueryMgr.getInstance().getSimple(sessionBUserId);
				Set<Long> storeIdSet = buser.getStoreIdSet();
				if (CollectionUtils.isNotEmpty(storeIdSet)) {
					for (Long storeId : storeIdSet) {
						StoreRO store = StoreMgr.getInstance().getReadOnly(storeId);
						ValidateInfo info = ValidateInfo.newInstance(store.getBossId(), storeId);
						ValidateInfoThreadLocal.getInstance().set(info);
						break;
					}
				}
			}
		} catch (Exception e) {
			MainLog.info(LogModule.Tmp, "ValidateDataIntcpt[putDefaultValidateInfo]", "error");
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		MainLog.info(LogModule.Tmp, "ValidateDataIntcpt[postHandle]", "test info");
		ValidateInfoThreadLocal.getInstance().set(null);
		super.postHandle(request, response, handler, modelAndView);
	}

}
