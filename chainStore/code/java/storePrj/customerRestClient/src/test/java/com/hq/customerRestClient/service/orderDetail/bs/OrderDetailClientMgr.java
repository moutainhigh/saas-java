package com.hq.customerRestClient.service.orderDetail.bs;

import com.hq.customerRestClient.common.util.StringUtils4Client;
import com.hq.customerRestClient.service.orderDetail.data.OrderDetail;
import com.hq.customerRestClient.service.orderDetail.data.OrderDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderDetailClientMgr {

	public static OrderDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderDetailClientMgr.class);
	}
	
	public OrderDetail getOrderDetail(long storeId, long orderId) {
		String id = StringUtils4Client.format("{}/{}", storeId, orderId);
		return OrderDetailDAO.getInstance().get(id);
	}
	
}
