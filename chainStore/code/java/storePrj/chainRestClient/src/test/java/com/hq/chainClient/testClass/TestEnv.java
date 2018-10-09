package com.hq.chainClient.testClass;

import com.hq.chainClient.ChainRestClientMgr;
import com.hq.chainClient.common.cache.RestCacheMgr;
import com.zenmind.dao.RestDaoMgr;

public class TestEnv {

	public static void initTestEnv() {
		String service = "http://127.0.0.1:9122/chainms/ws/v1";
//		String service = "http://192.168.40.220/chainms/ws/v1";
//		String service = "http://192.168.40.221/chainms/ws/v1";
//		String service = "https://www.zhimeitimes.com:9110/chainms/ws/v1";
//		String service = "https://www.zhimeitimes.com/chainms/ws/v1";
		
		RestTemplateMgr.getInstance().init();
		RestDaoMgr.init(new RestProxySTImpl());
		RestCacheMgr.getInstance().init(new CacheMgr4Test());
		ChainRestClientMgr.init(service);
	}

}
