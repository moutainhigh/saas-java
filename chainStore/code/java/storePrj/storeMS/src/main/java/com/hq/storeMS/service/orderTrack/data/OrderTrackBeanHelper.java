package com.hq.storeMS.service.orderTrack.data;

import com.zenmind.common.hotSwap.HotSwap;

public class OrderTrackBeanHelper {
	public static OrderTrackBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTrackBeanHelper.class);
	}
}
