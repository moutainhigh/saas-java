package com.hq.storeClient.service.order.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.utils.StringUtils4Client;
import com.hq.storeClient.service.order.apiData.MallOrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.storeClient.service.order.apiData.PayOrderForCuserForm;
import com.hq.storeClient.service.order.apiData.PreOrderForCuserAddForm;
import com.hq.storeClient.service.order.data.MallOrder;
import com.hq.storeClient.service.order.data.Order;
import com.hq.storeClient.service.order.data.OrderDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderClientMgr {

	public static OrderClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderClientMgr.class);
	}

	public Order get(long storeId, long orderId) {
		String id = StringUtils4Client.format("{}/{}", storeId, orderId);
		return OrderDAO.getInstance().get(id);
	}

	public PageResp<MallOrder> findMallOrderPage(MallOrderQueryForm queryForm) {
		String findPath = "findMallOrderPage";
		return OrderDAO.getInstance().findMallOrderPage(findPath, queryForm);
	}
	
	public PageResp<Order> findOrderPageInfo(OrderQueryForm queryForm) {
		String findPath = "findOrderPageInfo";
		return OrderDAO.getInstance().findOrderPageInfo(findPath, queryForm);
	}

	public Order addPreOrderForCuser(PreOrderForCuserAddForm addForm) {
		String path = "addPreOrderForCuser";
		return OrderDAO.getInstance().addPreOrderForCuser(path, addForm);
	}
	
	public Order payOrderByCuser(PayOrderForCuserForm payForm) {
		String path = "payOrderByCuser";
		return OrderDAO.getInstance().payOrderByCuser(path, payForm);
	}
	
	public Order updateOrder(long orderId, OrderUpdateApiForm inputForm){
		return OrderDAO.getInstance().updateOrder(orderId,inputForm);
	}
}
