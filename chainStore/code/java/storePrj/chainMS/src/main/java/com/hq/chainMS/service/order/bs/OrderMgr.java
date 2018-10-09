package com.hq.chainMS.service.order.bs;

import java.util.List;

import com.hq.chainMS.service.common.PageResp;
import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderCount;
import com.hq.orderRestClient.service.order.data.OrderDateGroup;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderMgr {

	public static OrderMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderMgr.class);
	}

	public PageResp<Order> findOrderPageInfo(OrderQueryForm params) {
		return OrderDataHolder.getInstance().findOrderPageInfo(params);
	}
	
	public List<Order> findOrderList(OrderQueryForm params) {
		return OrderDataHolder.getInstance().findOrderList(params);
	}
	
	public List<OrderDateGroup> findOrderDateGroupList(OrderQueryForm params) {
		return OrderDataHolder.getInstance().findOrderDateGroupList(params);
	}

	public Order get(long storeId, long orderId) {
		return OrderDataHolder.getInstance().get(storeId, orderId);
	}
	
	public OrderCount getOrderCount(OrderQueryForm params){
		return OrderDataHolder.getInstance().getOrderCount(params);
	}

	
}
