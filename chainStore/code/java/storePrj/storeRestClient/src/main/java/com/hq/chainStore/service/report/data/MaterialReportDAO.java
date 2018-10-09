package com.hq.chainStore.service.report.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class MaterialReportDAO extends RestDao<MaterialReport> {

	public static MaterialReportDAO getInstance() {
		return HotSwap.getInstance().getSingleton(MaterialReportDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getReportService();
	}
	
	@Override
	public List<MaterialReport> list(int pageItemCount, int pageNo){
		return super.list(pageItemCount, pageNo);
		
	}

}
