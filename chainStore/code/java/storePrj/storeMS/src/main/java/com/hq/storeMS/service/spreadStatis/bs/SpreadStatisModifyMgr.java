package com.hq.storeMS.service.spreadStatis.bs;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.storeMS.service.dynamic.bs.DynamicQueryMgr;
import com.hq.storeMS.service.dynamic.data.Dynamic;
import com.hq.storeMS.service.spreadStatis.data.SpreadStatis;
import com.zenmind.common.hotSwap.HotSwap;

public class SpreadStatisModifyMgr {

	public static SpreadStatisModifyMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SpreadStatisModifyMgr.class);
	}

	public void addByOrder(Order order) {
		long dynamicId = order.getDynamicId();
		if(dynamicId == 0L || order.getStatus() == OrderStatusEnum.NOT_PAY.ordinal()) {
			return;
		}
		Dynamic dynamic = DynamicQueryMgr.getInstance().get(dynamicId);
		if(dynamic == null) {
			return;
		}
		SpreadStatis target = SpreadStatis.newInstance();
		target.setBuserId(dynamic.getBuserId());
		target.setStoreId(order.getStoreId());
		target.setCuserId(order.getCuserId());
		target.setDynamicId(dynamicId);
		target.setOrderId(order.getId());
		addAndReturnId(target);
	}
	
	public void addAndReturnId(SpreadStatis target) {
		SpreadStatisDataHolder.getInstance().addAndReturnId(target);
	}

}
