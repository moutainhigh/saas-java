package com.hq.chainStore.service.dataReport.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.order.data.Order;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;

public class DataReportDAO {

	public static DataReportDAO getInstance(){
		return HotSwap.getInstance().getSingleton(DataReportDAO.class);
	}

	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	private final String module = "dataReport";
	
	public MemberDataCount getMemberDataCount(ReqMap reqMap) {
		final String actionName = "getMemberDataCount";
		try {
			final String uriPattern = "{}/{}/{}?{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), this.module, actionName, reqMap.toReqParam());
			return RestProxy.getInstance().get(uri, MemberDataCount.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("DataReportDAO.getMemberDataCount()", this.module, actionName, e));
		}
	}
	
	public List<DataReport> findDataReprotList(ReqMap reqMap) {
		final String actionName = "findDataReprotList";
		try {
			final String uriPattern = "{}/{}/{}?{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), this.module, actionName, reqMap.toReqParam());
			return RestProxy.getInstance().list(uri, DataReport.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("DataReportDAO.findDataReprotList()", this.module, actionName, e));
		}
	}
	
	public List<Order> findOrderList(ReqMap reqMap) {
		final String actionName = "findOrderList";
		try {
			final String uriPattern = "{}/{}/{}?{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), this.module, actionName, reqMap.toReqParam());
			return RestProxy.getInstance().list(uri, Order.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("DataReportDAO.findOrderList()", this.module, actionName, e));
		}
	}
}
