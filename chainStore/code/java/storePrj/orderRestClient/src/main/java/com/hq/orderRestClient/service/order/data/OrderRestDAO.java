
package com.hq.orderRestClient.service.order.data;

import java.util.List;

import com.hq.orderRestClient.common.restClientResp.RestClientCfg;
import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.common.page.PageBean;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class OrderRestDAO extends RestDao<Order> {

	public static OrderRestDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderRestDAO.class);
	}
	
	private final String table = "order";
	
	public PageBean<Order> findOrderPageInfo(OrderQueryForm params) {
		final String findPath = "findOrderPageInfo";
		try {
			RestResp restResp = rawGetReq(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
			return JsonUtil.getInstance().parseTPageBean(restResp.gettJson(), Order.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("StoreOrderDAO.findOrderPageInfo()", this.table, findPath, e));
		}
	}
	
	public List<Order> findOrderList(OrderQueryForm params) {
		final String findPath = "findOrderList";
		return findWithReqParam(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
	}
	
	public List<OrderDateGroup> findOrderDateGroupList(OrderQueryForm params) {
		final String findPath = "findOrderDateGroupList";
		try {
			RestResp restResp = rawGetReq(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
			return JsonUtil.getInstance().parseList(restResp.gettListJson(), OrderDateGroup.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("StoreOrderDAO.findOrderDateGroupList()", this.table, findPath, e));
		}
	}
	
	public OrderCount getOrderCount(OrderQueryForm params){
		String findPath="getOrderCount";
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

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
}
