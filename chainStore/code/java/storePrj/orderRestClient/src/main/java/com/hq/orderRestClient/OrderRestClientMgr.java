package com.hq.orderRestClient;

import com.hq.orderRestClient.common.restClientResp.RestClientCfg;

public class OrderRestClientMgr {
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
