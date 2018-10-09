package com.hq.customerRestClient.testClass;

import com.hq.customerRestClient.CustomerClientMgr;
import com.hq.customerRestClient.common.cache.RestCacheMgr;
import com.zenmind.dao.RestDaoMgr;

public class TestEnv {
	public static void initTestEnv() {
		String service = "http://127.0.0.1:9118/customerms/ws/v1";
//		String service = "http://192.168.40.220/customerms/ws/v1";
//		String service = "http://192.168.40.221/customerms/ws/v1";
//		String service = "https://www.zhimeitimes.com:9110/customerms/ws/v1";
//		String service = "http://www.zhimeitimes.com:9221/green/customerms/ws/v1";
//		String service = "https://www.zhimeitimes.com/customerms/ws/v1";
//		String service = "https://www.zhimeitimes.com/green/customerms/ws/v1";

		RestTemplateMgr.getInstance().init();
		RestDaoMgr.init(new RestProxySTImpl());
		RestCacheMgr.getInstance().init(new CacheMgr4Test());
		CustomerClientMgr.init(service);
	}
}
