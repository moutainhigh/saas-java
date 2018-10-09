package com.hq.customerMS.service.order.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.order.data.OrderCacheDAO;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.order.apiData.MallOrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.storeClient.service.order.apiData.PayOrderForCuserForm;
import com.hq.storeClient.service.order.apiData.PreOrderForCuserAddForm;
import com.hq.storeClient.service.order.bs.OrderClientMgr;
import com.hq.storeClient.service.order.data.MallOrder;
import com.hq.storeClient.service.order.data.Order;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderDataHolder {

	public static OrderDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(OrderDataHolder.class);
	}

	public PageResp<MallOrder> findMallOrderPage(MallOrderQueryForm params) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<MallOrder> page = OrderClientMgr.getInstance().findMallOrderPage(params);
		AppIdThreadLocal.getInstance().set(null);
		return page;
	}
	
	public PageResp<Order> findOrderPageInfo(OrderQueryForm params) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<Order> page = OrderClientMgr.getInstance().findOrderPageInfo(params);
		AppIdThreadLocal.getInstance().set(null);
		return page;
	}

	public Order get(long storeId, long orderId) {
		Order data = OrderCacheDAO.getInstance().get(storeId, orderId);
		if (data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = OrderClientMgr.getInstance().get(storeId, orderId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public Order update(long orderId, OrderUpdateApiForm updateApiForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		Order data = OrderClientMgr.getInstance().updateOrder(orderId, updateApiForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public Order addOrder(PreOrderForCuserAddForm addForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		Order data = OrderClientMgr.getInstance().addPreOrderForCuser(addForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public Order payOrder(PayOrderForCuserForm payForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		Order data = OrderClientMgr.getInstance().payOrderByCuser(payForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

}
