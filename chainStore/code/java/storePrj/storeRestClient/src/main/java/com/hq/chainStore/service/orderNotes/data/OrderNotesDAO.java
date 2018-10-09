package com.hq.chainStore.service.orderNotes.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;

public class OrderNotesDAO extends RestDao<OrderNotes> {

	public static OrderNotesDAO getInstance(){
		return HotSwap.getInstance().getSingleton(OrderNotesDAO.class);
	}
	
	private final String tableName = "orderNotes";

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	/**
	 * ================================对子域的操作================================
	 */
	public OrderNotes addSubDomains(long orderId, Object inputForm, String module) {
		try {
			final String uriPattern = "{}/{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), tableName, module, orderId);
			return RestProxy.getInstance().add(uri, inputForm, OrderNotes.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("OrderNotesDAO.addSubDomains()", tableName, "", e));
		}
	}
}
