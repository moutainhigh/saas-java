package com.hq.chainStore.service.report.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class SpecialDataDAO extends RestDao<SpecialData> {

	public static SpecialDataDAO getInstance() {
		return HotSwap.getInstance().getSingleton(SpecialDataDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public List<SpecialData> list(int pageItemCount, int pageNo){
		return super.list(pageItemCount, pageNo);
		
	}
}
