package com.hq.orderMS.service.order.callback;

import com.hq.orderMS.service.order.data.Order;
import com.hq.orderMS.service.order.data.OrderStatusEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderCallBack {
	public static OrderCallBack getInstance() {
		return HotSwap.getInstance().getSingleton(OrderCallBack.class);
	}

	public void sendOrder(Order target) {
		if (target.getStatus() == OrderStatusEnum.HAS_PAY.ordinal()) {
			// 发送给storeMS
			sendOrderEvent(target);
		}
	}

	private void sendOrderEvent(Order order) {
//		StoreEvent event = new StoreEvent();
//		event.setEventType(StoreEventType.OrderItem.ordinal());
//		event.setStoreId(order.getStoreId());
//		event.setTid(UUID.randomUUID().toString());
//
//		OrderItem item = new OrderItem();
//		FastBeanCopyer.getInstance().copy(order, item);
//
//		event.setJsonData(JsonUtil.getInstance().toJson(item));
//		EventSenderMgr.getInstance().sendEvent(event);
	}

}
