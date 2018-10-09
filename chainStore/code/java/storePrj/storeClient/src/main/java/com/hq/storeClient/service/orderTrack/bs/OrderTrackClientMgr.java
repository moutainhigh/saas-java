package com.hq.storeClient.service.orderTrack.bs;

import com.hq.storeClient.common.utils.StringUtils4Client;
import com.hq.storeClient.service.orderTrack.apiData.OrderTrackUpdateApiForm;
import com.hq.storeClient.service.orderTrack.data.OrderTrack;
import com.hq.storeClient.service.orderTrack.data.OrderTrackDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTrackClientMgr {
	public static OrderTrackClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTrackClientMgr.class);
	}

	public OrderTrack getOrderTrack(long storeId, long orderId) {
		String id = StringUtils4Client.format("{}/{}", storeId, orderId);
		return OrderTrackDAO.getInstance().get(id);
	}

	public OrderTrack update(long storeId, long orderId, OrderTrackUpdateApiForm inputForm) {
		String id = StringUtils4Client.format("{}/{}", storeId, orderId);
		return OrderTrackDAO.getInstance().updateWithResp(id, inputForm);
	}
}
