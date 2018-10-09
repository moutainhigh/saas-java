package com.hq.customerMS.service.order.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.order.apiData.MallOrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.storeClient.service.order.apiData.PayOrderForCuserForm;
import com.hq.storeClient.service.order.apiData.PreOrderForCuserAddForm;
import com.hq.storeClient.service.order.data.MallOrder;
import com.hq.storeClient.service.order.data.Order;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderMgr {

	public static OrderMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderMgr.class);
	}

	public PageResp<MallOrder> findMallOrderPage(MallOrderQueryForm params) {
		return OrderDataHolder.getInstance().findMallOrderPage(params);
	}
	
	public PageResp<Order> findOrderPageInfo(OrderQueryForm params) {
		return OrderDataHolder.getInstance().findOrderPageInfo(params);
	}
	
	public Order get(long storeId,long orderId) {
		return OrderDataHolder.getInstance().get(storeId,orderId);
	}
	
	public Order addOrder(PreOrderForCuserAddForm addForm) {
		return OrderDataHolder.getInstance().addOrder(addForm);
	}
	
	public Order payOrder(PayOrderForCuserForm payForm) {
		return OrderDataHolder.getInstance().payOrder(payForm);
	}
	
	public Order update(long orderId, OrderUpdateApiForm updateApiForm) {
		return OrderDataHolder.getInstance().update(orderId,updateApiForm);
	}
	

}
