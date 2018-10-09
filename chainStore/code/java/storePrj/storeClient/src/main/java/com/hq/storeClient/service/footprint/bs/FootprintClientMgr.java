package com.hq.storeClient.service.footprint.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.footprint.apiData.FootprintAddForm;
import com.hq.storeClient.service.footprint.apiData.FootprintQueryForm;
import com.hq.storeClient.service.footprint.data.Footprint;
import com.hq.storeClient.service.footprint.data.FootprintDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class FootprintClientMgr {

	public static FootprintClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(FootprintClientMgr.class);
	}
	
	public PageResp<Footprint> findPage(FootprintQueryForm queryForm) {
		final String findPath = "findPage";
		return FootprintDAO.getInstance().findPage(findPath, queryForm);
	}
	
	public Footprint get(long footprintId) {
		return FootprintDAO.getInstance().get(footprintId);
	}
	
	public Footprint add(FootprintAddForm addForm) {
		return FootprintDAO.getInstance().add(addForm);
	}
}
