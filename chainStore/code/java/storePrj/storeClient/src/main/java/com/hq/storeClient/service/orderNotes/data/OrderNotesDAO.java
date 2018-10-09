package com.hq.storeClient.service.orderNotes.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class OrderNotesDAO extends RestDao<OrderNotes> {

	public static OrderNotesDAO getInstance(){
		return HotSwap.getInstance().getSingleton(OrderNotesDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
