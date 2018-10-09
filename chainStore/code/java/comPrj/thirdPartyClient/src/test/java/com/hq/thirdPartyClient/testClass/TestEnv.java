package com.hq.thirdPartyClient.testClass;

import com.hq.thirdPartyClient.service.ThirdPartyClientMgr;

public class TestEnv {
	
	public static void initTestEnv() {
		RestTemplateMgr.getInstance().init();
		
		String thirdPartyServer = "http://127.0.0.1:9119/thirdpartyserver/ws/v1";
		
//		String thirdPartyServer = "http://39.106.13.113:9219/thirdpartyserver/ws/v1";
		
		ThirdPartyClientMgr.init(new RestProxySTImpl(), thirdPartyServer);
	}
	

}
