package com.hq.customerRestClient.service.order.bs;

import com.hq.customerRestClient.common.restClientResp.PageResp;
import com.hq.customerRestClient.common.util.StringUtils4Client;
import com.hq.customerRestClient.service.order.apiData.OrderQueryForm;
import com.hq.customerRestClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.customerRestClient.service.order.apiData.PayOrderForCuserForm;
import com.hq.customerRestClient.service.order.apiData.PreOrderForCuserAddForm;
import com.hq.customerRestClient.service.order.data.Order;
import com.hq.customerRestClient.service.order.data.OrderDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderClientMgr {

	public static OrderClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderClientMgr.class);
	}
	
	public Order get(long storeId, long orderId) {
		String id = StringUtils4Client.format("{}/{}", storeId, orderId);
		return OrderDAO.getInstance().get(id);
	}
	
	public PageResp<Order> findOrderPageInfo(OrderQueryForm queryForm) {
		String findPath = "findOrderPageInfo";
		return OrderDAO.getInstance().findOrderPageInfo(findPath, queryForm);
	}

	public Order addPreOrderForCuser(PreOrderForCuserAddForm addForm) {
		String path = "addOrder";
		return OrderDAO.getInstance().addPreOrderForCuser(path, addForm);
	}
	
	public Order payOrderByCuser(PayOrderForCuserForm payForm) {
		String path = "payOrder";
		return OrderDAO.getInstance().payOrderByCuser(path, payForm);
	}
	
	public Order updateOrder(long orderId, OrderUpdateApiForm inputForm){
		return OrderDAO.getInstance().updateOrder(orderId,inputForm);
	}
}
