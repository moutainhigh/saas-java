package com.hq.customerMS.service.orderTrack.data;

import com.hq.customerMS.common.util.AppUtils;
import com.hq.storeClient.service.orderTrack.data.OrderTrack;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTrackCacheDAO {

	public static OrderTrackCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTrackCacheDAO.class);
	}

	final private String suffix = "orderTrack";

	public OrderTrack get(long storeId, long id) {
		return OrderTrackRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}

}
