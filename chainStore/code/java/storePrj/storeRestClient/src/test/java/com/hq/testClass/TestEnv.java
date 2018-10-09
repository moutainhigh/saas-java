package com.hq.testClass;

import com.hq.StoreClientMgr;

public class TestEnv {
	
	public static void initTestEnv() {
		RestTemplateMgr.getInstance().init();
		String storeService = "http://127.0.0.1:9114/storems/ws/v1";
//		String storeService = "http://192.168.40.221/storems/ws/v1";
//		String storeService = "http://192.168.40.220/storems/ws/v1";
//		String storeService = "http://39.105.79.200/storems/ws/v1";
//		String storeService = "http://192.168.40.52/storems/ws/v1";
//		String storeService = "https://www.zhimeitimes.com:9110/storems/ws/v1";
//		String storeService = "http://39.106.13.113:9114/storems/ws/v1";
//		String storeService = "https://www.zhimeitimes.com/dev/storems/ws/v1";
//		String storeService = "https://www.zhimeitimes.com/storems/ws/v1";
		
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService);
	}
	

}
