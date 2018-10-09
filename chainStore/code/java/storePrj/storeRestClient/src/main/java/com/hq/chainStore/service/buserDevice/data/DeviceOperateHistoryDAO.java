package com.hq.chainStore.service.buserDevice.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class DeviceOperateHistoryDAO extends RestDao<OperateHistory>{

	public static DeviceOperateHistoryDAO getInstance(){
		return HotSwap.getInstance().getSingleton(DeviceOperateHistoryDAO.class);
	}

	public String getService() {
		return RestClientCfg.getStoreService();
	}

}
