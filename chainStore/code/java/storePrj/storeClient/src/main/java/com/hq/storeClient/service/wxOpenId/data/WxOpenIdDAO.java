package com.hq.storeClient.service.wxOpenId.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class WxOpenIdDAO extends RestDao<WxOpenId> {

	public static WxOpenIdDAO getInstance(){
		return HotSwap.getInstance().getSingleton(WxOpenIdDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
}
