package com.hq;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.common.cache.RestCacheMgr;
import com.hq.common.cache.IntfCache;
import com.hq.common.dataSyn.DataSynInterceptor;
import com.hq.common.restClientLog.IntfLoger;
import com.hq.common.restClientLog.MainLog;
import com.zenmind.dao.RestDaoMgr;
import com.zenmind.dao.rest.IntfRestProxy;
import com.zenmind.dao.rest.interceptor.RestRespInterceptor;

public class StoreClientMgr {

	
	/**
	 * StoreClient 的使用要先初始化这个方法
	 * @param loggerImpl log实现
	 * @param proxyImpl IntfRestProxy 实现
	 * @param cacherP	缓存实现
	 * @param services  服务地址数组   顺序为：storeMsService orderReportMsService
	 */
	public static void init(IntfLoger loggerImpl, IntfRestProxy proxyImpl, IntfCache cacherP, String...services){
		MainLog.init(loggerImpl);
//		RestTemplateMgr.getInstance().init();
		RestDaoMgr.init(proxyImpl);
		RestCacheMgr.getInstance().init(cacherP);
		RestClientCfg.init(services);
		//同步数据架构初始化
		RestRespInterceptor.getInstance().add(DataSynInterceptor.newInstance());
		
	}
}
