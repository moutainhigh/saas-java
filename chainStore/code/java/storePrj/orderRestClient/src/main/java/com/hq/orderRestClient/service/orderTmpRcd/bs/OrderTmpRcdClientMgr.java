package com.hq.orderRestClient.service.orderTmpRcd.bs;

import com.hq.orderRestClient.service.orderTmpRcd.data.OrderTmpRcdRestDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTmpRcdClientMgr {
	
	public static OrderTmpRcdClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(OrderTmpRcdClientMgr.class);
	}
	
	public void checkOrderTmpRcd() {
		final String actionName="checkOrderTmpRcd";
		OrderTmpRcdRestDAO.getInstance().rawReq(actionName, null);
	}
}
