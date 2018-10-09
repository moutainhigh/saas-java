package com.hq.payRestClient;

import com.hq.payRestClient.service.common.PayClientCfg;
import com.zenmind.dao.RestDaoMgr;
import com.zenmind.dao.rest.IntfRestProxy;

public class PayClientMgr {
	public static void init(String serverUrl){
		PayClientCfg.init(serverUrl);
	}
	
	public static void init(IntfRestProxy proxyImpl, String serverUrl){
		RestDaoMgr.init(proxyImpl);
		PayClientCfg.init(serverUrl);
	}
}
