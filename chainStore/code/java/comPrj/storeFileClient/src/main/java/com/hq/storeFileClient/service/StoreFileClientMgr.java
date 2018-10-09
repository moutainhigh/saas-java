package com.hq.storeFileClient.service;

import com.hq.storeFileClient.service.common.StoreFileClientCfg;
import com.zenmind.dao.RestDaoMgr;
import com.zenmind.dao.rest.IntfRestProxy;

public class StoreFileClientMgr {
	/**
	 * StoreFileClientMgr的使用要先初始化这个方法;
	 * @param thirdPartyServerURL 第三方服务地址
	 */
	public static void init(String storeFileServer){
		StoreFileClientCfg.init(storeFileServer);
	}
	
	/**
	 * 
	 * @param proxyImpl zmRestDao的初始化
	 * @param thirdPartyServerURL 第三方服务地址
	 */
	public static void init(IntfRestProxy proxyImpl, String storeFileServer){
		RestDaoMgr.init(proxyImpl);
		StoreFileClientCfg.init(storeFileServer);
	}
}
