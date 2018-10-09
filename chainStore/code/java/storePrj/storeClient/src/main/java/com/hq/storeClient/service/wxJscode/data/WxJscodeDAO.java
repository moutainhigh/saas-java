package com.hq.storeClient.service.wxJscode.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class WxJscodeDAO extends RestDao<WxJscode> {

	public static WxJscodeDAO getInstance(){
		return HotSwap.getInstance().getSingleton(WxJscodeDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
}
