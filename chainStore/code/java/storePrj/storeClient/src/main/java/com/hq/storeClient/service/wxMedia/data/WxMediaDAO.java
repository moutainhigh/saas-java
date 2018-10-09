package com.hq.storeClient.service.wxMedia.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class WxMediaDAO extends RestDao<WxMedia> {

	public static WxMediaDAO getInstance(){
		return HotSwap.getInstance().getSingleton(WxMediaDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
}
