package com.hq.storeManagerRestClient;

import com.hq.storeManagerRestClient.common.restClientResp.RestClientCfg;

public class StoreMngClientMgr {
	/**
	 * 
	 * @param service 服务地址
	 * @param appName 使用方的MS名称
	 */
	public static void init(String service, String appName) {
		RestClientCfg.init(service, appName);
	}
	
	public static void init(String service) {
		init(service, "");
	}
	
}
