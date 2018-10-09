package com.hq.chainStore.service.report.bs;

import java.util.List;

import com.hq.chainStore.service.report.apiData.OrderReportQueryParams;
import com.hq.chainStore.service.report.data.OrderReport;
import com.hq.chainStore.service.report.data.OrderReportDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class OrderReportMgr {

	public static OrderReportMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderReportMgr.class);
	}

	public List<OrderReport> findOrderReportList(OrderReportQueryParams params) {
		final String findPath = "findOrderReportList";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", params.getStoreId()).add("buserId", params.getBuserId()).add("minTime", params.getMinTime()).add("maxTime", params.getMaxTime());
		return OrderReportDAO.getInstance().findWithReqParam(findPath, reqMap, params.getPageItemCount(), params.getPageNo());
	}

	public OrderReport get(long orderReportId) {
		return OrderReportDAO.getInstance().get(orderReportId);
	}
}
