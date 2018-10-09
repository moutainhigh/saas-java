package com.hq.storeClient.service.buserDevice.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class DeviceOperateHistoryDAO extends RestDao<OperateHistory>{

	public static DeviceOperateHistoryDAO getInstance(){
		return HotSwap.getInstance().getSingleton(DeviceOperateHistoryDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

}
