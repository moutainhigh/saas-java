package com.hq.storeFileClient.testClass;

import com.hq.storeFileClient.service.StoreFileClientMgr;

public class TestEnv {
	
	public static void initTestEnv() {
		RestTemplateMgr.getInstance().init();
		
		String storeFileServer = "http://127.0.0.1:9115/storefilems/ws/v1";
		
//		String storeFileServer = "http://192.168.40.220:9115/storefilems/ws/v1";
		
//		String storeFileServer = "http://39.106.13.113:9115/storefilems/ws/v1";
		
		StoreFileClientMgr.init(new RestProxySTImpl(), storeFileServer);
	}
	

}
