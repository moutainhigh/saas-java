package com.hq.storeClient.service.wxJsApiTicket.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class WxJsApiTicketDAO extends RestDao<WxJsApiTicket> {

	public static WxJsApiTicketDAO getInstance() {
		return HotSwap.getInstance().getSingleton(WxJsApiTicketDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

}
