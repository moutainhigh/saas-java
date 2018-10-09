package com.hq.payRestClient.common.validate;

import com.hq.payRestClient.common.validate.info.ValidateInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class ValidateThreadLocal {

	public static ValidateThreadLocal getInstance() {
		return HotSwap.getInstance().getSingleton(ValidateThreadLocal.class);
	}

	private ThreadLocal<ValidateInfo> validateTL = new ThreadLocal<ValidateInfo>();

	public ValidateInfo getValidateInfo() {
		return validateTL.get();
	}

	public void setValidateInfo(ValidateInfo info) {
		validateTL.set(info);
	}
	
	public void setValidateInfo(int appId) {
		ValidateInfo info = ValidateInfo.newInstance();
		info.setAppId(appId);
		validateTL.set(info);
	}

	public void remove() {
		validateTL.remove();
	}

}
