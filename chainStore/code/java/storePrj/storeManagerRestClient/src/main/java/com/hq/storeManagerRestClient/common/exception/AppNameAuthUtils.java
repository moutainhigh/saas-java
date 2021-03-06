package com.hq.storeManagerRestClient.common.exception;

import com.hq.storeManagerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;

public class AppNameAuthUtils {

	public static AppNameAuthUtils getInstance() {
		return HotSwap.getInstance().getSingleton(AppNameAuthUtils.class);
	}

	public void checkMS(AppNameEnum appNameEnum) {
		//[防止循环调用的检查]
		if (RestClientCfg.getAppNameEnum() == appNameEnum) {
			throw (ClientException.newInstance("AppNameAuthUtils.checkMS()"));
		}
	}

}
