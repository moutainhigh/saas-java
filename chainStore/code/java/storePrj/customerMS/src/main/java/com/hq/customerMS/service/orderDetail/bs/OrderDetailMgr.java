package com.hq.customerMS.service.orderDetail.bs;

import com.hq.storeClient.service.orderDetail.data.OrderDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderDetailMgr {

	public static OrderDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderDetailMgr.class);
	}

	public OrderDetail get(long storeId, long orderId) {
		return OrderDetailDataHolder.getInstance().get(storeId,orderId);
	}
}
