package com.hq.storeClient.service.buserDevice.bs;

import java.util.List;

import com.hq.storeClient.service.buserDevice.data.DeviceOperateHistoryDAO;
import com.hq.storeClient.service.buserDevice.data.OperateHistory;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class DeviceOperateHistoryClientMgr {
	public static DeviceOperateHistoryClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DeviceOperateHistoryClientMgr.class);
	}

	public List<OperateHistory> getOperateHistory(String clientId, String createdDate) {
		String uriPath = "operateHistory";
		ReqMap reqMap = new ReqMap();
		reqMap.add("clientId", clientId);
		reqMap.add("createdDate", createdDate);
		int pageItemCount = -1;
		int pageNo = -1;
		return DeviceOperateHistoryDAO.getInstance().findWithReqParam(uriPath, reqMap, pageItemCount, pageNo);
	}
}
