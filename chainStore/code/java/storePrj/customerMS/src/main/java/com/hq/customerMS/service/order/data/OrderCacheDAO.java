package com.hq.customerMS.service.order.data;

import com.hq.customerMS.common.util.AppUtils;
import com.hq.storeClient.service.order.data.Order;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderCacheDAO {

	public static OrderCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderCacheDAO.class);
	}

	final private String suffix = "order";

	public Order get(long storeId, long id) {
		return OrderRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}

}
