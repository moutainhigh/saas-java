package com.hq.chainStore.service.report.bs;

import java.util.List;

import com.hq.chainStore.service.report.apiData.MaterialReportQueryParams;
import com.hq.chainStore.service.report.data.MaterialReport;
import com.hq.chainStore.service.report.data.MaterialReportDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class MaterialReportMgr {

	public static MaterialReportMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MaterialReportMgr.class);
	}

	public List<MaterialReport> findMaterialReportList(MaterialReportQueryParams params) {
		final String findPath = "findMaterialReportList";
		return MaterialReportDAO.getInstance().findWithReqParam(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
	}

	public MaterialReport get(long materialReportId) {
		return MaterialReportDAO.getInstance().get(materialReportId);
	}
}
