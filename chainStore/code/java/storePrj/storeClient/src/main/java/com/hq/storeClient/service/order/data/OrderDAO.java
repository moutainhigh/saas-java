package com.hq.storeClient.service.order.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.order.apiData.MallOrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.storeClient.service.order.apiData.PayOrderForCuserForm;
import com.hq.storeClient.service.order.apiData.PreOrderForCuserAddForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class OrderDAO extends RestDao<Order> {

	public static OrderDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

	public PageResp<MallOrder> findMallOrderPage(String findPath, MallOrderQueryForm params) {
		RestResp restResp = super.rawGetReq(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), MallOrder.class);
	}
	
	public PageResp<Order> findOrderPageInfo(String findPath, OrderQueryForm params) {
		RestResp restResp = super.rawGetReq(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), Order.class);
	}

	public Order addPreOrderForCuser(String path, PreOrderForCuserAddForm addForm) {
		RestResp restResp = super.rawReq(path, addForm);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), Order.class);
	}
	
	public Order payOrderByCuser(String path, PayOrderForCuserForm payForm) {
		RestResp restResp = super.rawReq(path, payForm);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), Order.class);
	}
	
	public Order updateOrder(long orderId, OrderUpdateApiForm inputForm) {
		return super.updateWithResp(orderId, inputForm);
	}

}
