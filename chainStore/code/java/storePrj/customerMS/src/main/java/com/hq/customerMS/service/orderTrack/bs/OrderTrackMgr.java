package com.hq.customerMS.service.orderTrack.bs;

import com.hq.storeClient.service.orderTrack.apiData.OrderTrackUpdateApiForm;
import com.hq.storeClient.service.orderTrack.data.OrderTrack;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTrackMgr {

	public static OrderTrackMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTrackMgr.class);
	}

	public OrderTrack get(long storeId, long orderId) {
		return OrderTrackDataHolder.getInstance().get(storeId,orderId);
	}
	
	public OrderTrack update(long storeId, long orderId, OrderTrackUpdateApiForm updateApiForm) {
		return OrderTrackDataHolder.getInstance().update(storeId, orderId,updateApiForm);
	}
}
