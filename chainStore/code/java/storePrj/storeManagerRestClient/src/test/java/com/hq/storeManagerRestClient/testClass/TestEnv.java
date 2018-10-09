package com.hq.storeManagerRestClient.testClass;

import com.hq.storeManagerRestClient.StoreMngClientMgr;
import com.hq.storeManagerRestClient.common.cache.RestCacheMgr;
import com.zenmind.dao.RestDaoMgr;

public class TestEnv {
	
	public static void initTestEnv() {
		String service = "http://127.0.0.1:9124/storemngms/ws/v1";
//		String service = "http://192.168.40.220/storemngms/ws/v1";
//		String service = "http://192.168.40.221/storemngms/ws/v1";
//		String service = "http://39.106.13.113:9221/storemngms/ws/v1";
//		String service = "https://www.zhimeitimes.com:9110/storemngms/ws/v1";
//		String service = "https://www.zhimeitimes.com/storemngms/ws/v1";

		RestTemplateMgr.getInstance().init();
		RestDaoMgr.init(new RestProxySTImpl());
		RestCacheMgr.getInstance().init(new CacheMgr4Test());
		StoreMngClientMgr.init(service);
	}
	

}
