package com.hq.storeClient;

import com.hq.storeClient.common.restClientResp.RestClientCfg;

public class StoreClientMgr {
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
