package com.hq.chainStore.service.report.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class OrderReportSynDataHolder extends AbsDataSynDataHolder<OrderReport> {

	public static OrderReportSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(OrderReportSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.OrderReport;

	protected Class<OrderReport> getClazz() {
		return OrderReport.class;
	}
	
	protected RestDao<OrderReport> getDao() {
		return OrderReportDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
}
