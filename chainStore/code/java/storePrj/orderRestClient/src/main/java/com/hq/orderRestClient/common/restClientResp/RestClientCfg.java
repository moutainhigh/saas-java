package com.hq.orderRestClient.common.restClientResp;

import com.hq.orderRestClient.common.exception.AppNameEnum;

public class RestClientCfg {
	private static String service = "http://localhost:9122/chainms/ws/v1";
	private static AppNameEnum appNameEnum = AppNameEnum.None;

	public static void init(String service, String appName) {
		RestClientCfg.service = service;
		AppNameEnum[] values = AppNameEnum.values();
		for (AppNameEnum appNameEnum : values) {
			if(appNameEnum.getName().equals(appName)) {
				RestClientCfg.appNameEnum = appNameEnum;
				break;
			}
		}
	}
	
	public static String getService() {
		return service;
	}

	public static AppNameEnum getAppNameEnum() {
		return appNameEnum;
	}
}
