package com.hq.chainStore.service.order.data;

import java.util.List;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.order.apiData.OrderAddByWorkflowDataIdForm;
import com.hq.chainStore.service.order.apiData.OrderQueryForm;
import com.hq.chainStore.service.order.apiData.PreOrderAddForm;
import com.hq.common.JsonUtil4Client;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class StoreOrderDAO extends RestDao<Order> {

	public static StoreOrderDAO getInstance() {
		return HotSwap.getInstance().getSingleton(StoreOrderDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	private final String table = "order";
	
	public OrderCount getOrderCount(String findPath, OrderQueryForm params){
		try {
			final String uriPattern = "{}/{}/{}?{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), this.table, findPath, params.toReqMap().toReqParam());
			return RestProxy.getInstance().get(uri, OrderCount.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("StoreOrderDAO.getOrderCount()", this.table, findPath, e));
		}
	}
	
	public PageResp<Order> findOrderPageInfo(String findPath, OrderQueryForm params){
		try {
			RestResp restResp = super.rawGetReq(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), Order.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("StoreOrderDAO.findOrderPageInfo()", this.table, findPath, e));
		}
	}
	
	public List<OrderDateGroup> findOrderDateGroupList(String findPath, OrderQueryForm params){
		try {
			RestResp restResp = super.rawGetReq(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
			return JsonUtil4Client.getInstance().parseList(restResp.gettListJson(), OrderDateGroup.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("StoreOrderDAO.findOrderDateGroupList()", this.table, findPath, e));
		}
	}
	
	public Order addOrderByWorkflowDataId(String path, OrderAddByWorkflowDataIdForm addForm){
		try {
			RestResp restResp = super.rawReq(path, addForm);
			return JsonUtil.getInstance().fromJson(restResp.gettJson(), Order.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("StoreOrderDAO.addOrderByWorkflowDataId()", this.table, path, e));
		}
	}
	
	public Order addPreOrder(String path, PreOrderAddForm addForm){
		try {
			RestResp restResp = super.rawReq(path, addForm);
			return JsonUtil.getInstance().fromJson(restResp.gettJson(), Order.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("StoreOrderDAO.addPreOrder()", this.table, path, e));
		}
	}

}
