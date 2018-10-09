package com.hq.storeManagerRestClient.service.muser.data;

import com.hq.storeManagerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestProxy;

public class MUserDAO extends RestDao<MUser> {

	public static MUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(MUserDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public MUser getMUserFromMS(String uriPath, ReqMap reqMap){
		final String uriPattern = "{}/{}/{}?{}";
		String uri = StringFormatUtil.format(uriPattern, getService(), "muserUnAuth", uriPath, reqMap.toReqParam());
		return RestProxy.getInstance().get(uri, MUser.class);
	}
	
}
