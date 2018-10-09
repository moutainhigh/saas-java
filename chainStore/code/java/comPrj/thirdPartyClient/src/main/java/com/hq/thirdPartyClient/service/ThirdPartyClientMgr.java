package com.hq.thirdPartyClient.service;

import com.hq.thirdPartyClient.service.common.ThirdPartyClientCfg;
import com.zenmind.dao.RestDaoMgr;
import com.zenmind.dao.rest.IntfRestProxy;

public class ThirdPartyClientMgr {
	/**
	 * ThirdPartyClient的使用要先初始化这个方法;
	 * @param thirdPartyServerURL 第三方服务地址
	 */
	public static void init(String thirdPartyServerURL){
		ThirdPartyClientCfg.init(thirdPartyServerURL);
	}
	
	/**
	 * 
	 * @param proxyImpl zmRestDao的初始化
	 * @param thirdPartyServerURL 第三方服务地址
	 */
	public static void init(IntfRestProxy proxyImpl, String thirdPartyServerURL){
		RestDaoMgr.init(proxyImpl);
		ThirdPartyClientCfg.init(thirdPartyServerURL);
	}
}
