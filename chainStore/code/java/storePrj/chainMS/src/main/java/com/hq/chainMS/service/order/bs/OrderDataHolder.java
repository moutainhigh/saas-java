package com.hq.chainMS.service.order.bs;

import java.util.List;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.util.PageUtil;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.chainMS.service.common.PageResp;
import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.bs.OrderClientMgr;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderCount;
import com.hq.orderRestClient.service.order.data.OrderDateGroup;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.page.PageBean;

public class OrderDataHolder {

	public static OrderDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(OrderDataHolder.class);
	}

	public PageResp<Order> findOrderPageInfo(OrderQueryForm params) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageBean<Order> pageBean = OrderClientMgr.getInstance().findOrderPageInfo(params);
		AppIdThreadLocal.getInstance().set(null);
		return PageUtil.getInstance().copyFromPageBean(pageBean);
	}
	
	public List<Order> findOrderList(OrderQueryForm params) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		List<Order> data = OrderClientMgr.getInstance().findOrderList(params);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public List<OrderDateGroup> findOrderDateGroupList(OrderQueryForm params) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		List<OrderDateGroup> data = OrderClientMgr.getInstance().findOrderDateGroupList(params);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public Order get(long storeId, long orderId) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		Order data = OrderClientMgr.getInstance().get(storeId, orderId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public OrderCount getOrderCount(OrderQueryForm params){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		OrderCount data = OrderClientMgr.getInstance().getOrderCount(params);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
//
//	public void delete(long orderId, long storeId) {
//		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
//		OrderClientMgr.getInstance().deleteOrder(orderId, storeId);
//		AppIdThreadLocal.getInstance().set(null);
//	}
//
//	public void update(long orderId, OrderUpdateApiForm updateApiForm) {
//		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
//		OrderClientMgr.getInstance().updateOrder(orderId, updateApiForm);
//		AppIdThreadLocal.getInstance().set(null);
//	}
//
//	public Order addOrder(OrderAddApiForm addApiForm) {
//		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
//		Order data = OrderClientMgr.getInstance().addOrder(addApiForm);
//		AppIdThreadLocal.getInstance().set(null);
//		return data;
//	}
}
