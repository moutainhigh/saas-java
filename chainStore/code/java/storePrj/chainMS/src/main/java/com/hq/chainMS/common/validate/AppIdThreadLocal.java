package com.hq.chainMS.common.validate;

import com.hq.chainMS.common.validate.info.ValidateInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class AppIdThreadLocal {
	public static AppIdThreadLocal getInstance() {
		return HotSwap.getInstance().getSingleton(AppIdThreadLocal.class);
	}

	private final static ThreadLocal<ValidateInfo> appIdTL = new ThreadLocal<ValidateInfo>();
	
	public void setValidateInfo(int appId) {
		ValidateInfo info = ValidateInfo.newInstance();
		info.setAppId(appId);
		appIdTL.set(info);
	}

	public void remove() {
		appIdTL.remove();
	}

	public void set(ValidateInfo authInfo) {
		appIdTL.set(authInfo);
	}

	public ValidateInfo get() {
		return appIdTL.get();
	}
}
