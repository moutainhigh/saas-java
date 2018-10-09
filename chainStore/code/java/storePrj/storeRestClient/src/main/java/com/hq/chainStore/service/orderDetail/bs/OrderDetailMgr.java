package com.hq.chainStore.service.orderDetail.bs;

import com.hq.chainStore.service.orderDetail.data.OrderDetail;
import com.hq.chainStore.service.orderDetail.data.OrderDetailDAO;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderDetailMgr {

	public static OrderDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderDetailMgr.class);
	}
	
	@Deprecated
	public OrderDetail getOrderDetail(long orderId) {
		return getOrderDetail(0L, orderId);
	}
	
	public OrderDetail getOrderDetail(long storeId, long orderId) {
		String id = StringUtils4Client.format("{}/{}", storeId, orderId);
		return OrderDetailDAO.getInstance().get(id);
	}
	
}
