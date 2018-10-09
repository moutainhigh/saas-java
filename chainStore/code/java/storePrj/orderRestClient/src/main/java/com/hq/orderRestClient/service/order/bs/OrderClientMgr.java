package com.hq.orderRestClient.service.order.bs;

import java.util.List;

import com.hq.orderRestClient.common.utils.StringUtils4Client;
import com.hq.orderRestClient.service.order.apiData.OrderAddApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderCount;
import com.hq.orderRestClient.service.order.data.OrderDateGroup;
import com.hq.orderRestClient.service.order.data.OrderRestDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.page.PageBean;

public class OrderClientMgr {
	
	public static OrderClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(OrderClientMgr.class);
	}
	
	public Order addOrder(OrderAddApiForm addApiForm) {
		return OrderRestDAO.getInstance().add(addApiForm);
	}
	
	public void deleteOrder(long orderId, long storeId) {
		String id = StringUtils4Client.format("{}/{}", storeId, orderId);
		OrderRestDAO.getInstance().delete(id);
	}
	
	public void updateOrder(long orderId, OrderUpdateApiForm updateApiForm) {
		OrderRestDAO.getInstance().update(orderId, updateApiForm);
	}
	
	@Deprecated
	public Order get(long id) {
		return get(0L, id);
	}
	
	public Order get(long storeId, long id) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, id);
		return OrderRestDAO.getInstance().findOne(uriPath);
	}
	
	public List<Order> findOrderList(OrderQueryForm params) {
		return OrderRestDAO.getInstance().findOrderList(params);
	}
	
	public List<OrderDateGroup> findOrderDateGroupList(OrderQueryForm params) {
		return OrderRestDAO.getInstance().findOrderDateGroupList(params);
	}
	
	public PageBean<Order> findOrderPageInfo(OrderQueryForm params) {
		return OrderRestDAO.getInstance().findOrderPageInfo(params);
	}
	
	public OrderCount getOrderCount(OrderQueryForm params){
		return OrderRestDAO.getInstance().getOrderCount(params);
	}
}
