package com.hq.orderMS.common.validate;

import com.hq.orderMS.common.validate.info.ValidateInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class ValidateInfoThreadLocal {
	public static ValidateInfoThreadLocal getInstance() {
		return HotSwap.getInstance().getSingleton(ValidateInfoThreadLocal.class);
	}

	private final static ThreadLocal<ValidateInfo> authInfoTL = new ThreadLocal<ValidateInfo>();

	public void remove() {
		authInfoTL.remove();
	}
	
	public void set(ValidateInfo authInfo) {
		authInfoTL.set(authInfo);
	}

	public ValidateInfo get() {
		return authInfoTL.get();
	}

	public long getBossId() {
		ValidateInfo info = get();
		if(info == null) {
			return 0;
		}
		return info.getBossId();
	}
	
	public long getStoreId() {
		ValidateInfo info = get();
		if(info == null) {
			return 0;
		}
		return info.getStoreId();
	}
}
