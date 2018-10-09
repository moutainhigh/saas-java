package com.hq.chainStore.service.dataReport.bs;

import java.util.List;

import com.hq.chainStore.service.dataReport.apiData.DataReportQueryForm;
import com.hq.chainStore.service.dataReport.data.DataReport;
import com.hq.chainStore.service.dataReport.data.DataReportDAO;
import com.hq.chainStore.service.dataReport.data.MemberDataCount;
import com.hq.chainStore.service.order.data.Order;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class DataReportMgr {

	public static DataReportMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DataReportMgr.class);
	}
	
	public MemberDataCount getMemberDataCount(long storeId) {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", storeId);
		return DataReportDAO.getInstance().getMemberDataCount(reqMap);
	}
	
	public List<DataReport> findDataReprotList(DataReportQueryForm queryForm) {
		return DataReportDAO.getInstance().findDataReprotList(queryForm.toReqMap());
	}
	
	public List<Order> findOrderList(DataReportQueryForm queryForm) {
		return DataReportDAO.getInstance().findOrderList(queryForm.toReqMap());
	}
}
