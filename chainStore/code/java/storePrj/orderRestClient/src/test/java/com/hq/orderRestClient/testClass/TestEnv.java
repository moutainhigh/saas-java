package com.hq.orderRestClient.testClass;

import com.hq.orderRestClient.OrderRestClientMgr;
import com.hq.orderRestClient.common.cache.RestCacheMgr;
import com.zenmind.dao.RestDaoMgr;

public class TestEnv {
	
	public static void initTestEnv() {
		String service = "http://127.0.0.1:9117/orderms/ws/v1";
//		String service = "http://192.168.40.220/orderms/ws/v1";
//		String service = "http://192.168.40.221/orderms/ws/v1";
//		String service = "https://www.zhimeitimes.com:9221/orderms/ws/v1";
		
		RestTemplateMgr.getInstance().init();
		RestDaoMgr.init(RestProxySTImpl.getInstance());
		RestCacheMgr.getInstance().init(new CacheMgr4Test());
		OrderRestClientMgr.init(service);
	}
	

}
