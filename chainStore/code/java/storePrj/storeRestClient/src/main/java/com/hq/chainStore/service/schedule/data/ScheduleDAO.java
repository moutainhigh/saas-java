package com.hq.chainStore.service.schedule.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ScheduleDAO extends RestDao<Schedule> {
	
	public static ScheduleDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ScheduleDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public List<Schedule> list(int pageItemCount, int pageNo){
		return super.list(pageItemCount, pageNo);
	}

}
