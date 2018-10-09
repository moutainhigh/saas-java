package com.hq.chainStore.service.appVersion.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;

public class AppVersionDAO extends RestDao<AppVersion> {

	public static AppVersionDAO getInstance(){
		return HotSwap.getInstance().getSingleton(AppVersionDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	public AppVersion findByNameUnAuth(String uriPath, ReqMap reqMap){
		try {
			final String uriPattern = "{}/{}/{}?{}";
			String reqParam = reqMap.toReqParam();
			String uri = StringFormatUtil.format(uriPattern, getService(), "appVersionUnAuth", uriPath, reqParam);
			return RestProxy.getInstance().get(uri, AppVersion.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("AppVersionDAO.findByNameUnAuth()", "appVersion", uriPath, e));
		}
	}
	
}
