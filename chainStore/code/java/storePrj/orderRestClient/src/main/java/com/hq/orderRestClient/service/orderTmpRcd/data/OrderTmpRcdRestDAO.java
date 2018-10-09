
package com.hq.orderRestClient.service.orderTmpRcd.data;

import com.hq.orderRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class OrderTmpRcdRestDAO extends RestDao<OrderTmpRcd> {

	public static OrderTmpRcdRestDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTmpRcdRestDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
