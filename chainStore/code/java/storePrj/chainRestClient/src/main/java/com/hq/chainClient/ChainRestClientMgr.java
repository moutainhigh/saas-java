package com.hq.chainClient;

import com.hq.chainClient.common.restClientResp.RestClientCfg;

public class ChainRestClientMgr {
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
