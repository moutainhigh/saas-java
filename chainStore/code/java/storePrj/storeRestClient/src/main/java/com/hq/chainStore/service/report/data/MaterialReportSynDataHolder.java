package com.hq.chainStore.service.report.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class MaterialReportSynDataHolder extends AbsDataSynDataHolder<MaterialReport> {

	public static MaterialReportSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(MaterialReportSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.MaterialReport;

	protected Class<MaterialReport> getClazz() {
		return MaterialReport.class;
	}
	
	protected RestDao<MaterialReport> getDao() {
		return MaterialReportDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
}
