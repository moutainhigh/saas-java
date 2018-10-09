package com.hq.storeClient.testClass;

import com.hq.storeClient.StoreClientMgr;
import com.hq.storeClient.common.cache.RestCacheMgr;
import com.zenmind.dao.RestDaoMgr;

public class TestEnv {

	public static void initTestEnv() {
		String service = "http://127.0.0.1:9114/storems/ws/v1";
//		String service = "http://192.168.40.220/storems/ws/v1";
//		String service = "http://192.168.40.221/storems/ws/v1";
//		String service = "http://www.zhimeitimes.com:9221/exp/storems/ws/v1";
//		String service = "https://www.zhimeitimes.com/exp/storems/ws/v1";
//		String service = "https://www.zhimeitimes.com:9110/storems/ws/v1";
//		String service = "https://www.zhimeitimes.com/storems/ws/v1";
		
		RestTemplateMgr.getInstance().init();
		RestDaoMgr.init(new RestProxySTImpl());
		RestCacheMgr.getInstance().init(new CacheMgr4Test());
		StoreClientMgr.init(service);
	}

}
