package com.hq.chainStore.service.buserDevice.bs;

import java.util.List;

import com.hq.chainStore.service.buserDevice.data.DeviceOperateHistoryDAO;
import com.hq.chainStore.service.buserDevice.data.OperateHistory;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class DeviceOperateHistoryMgr {
	public static DeviceOperateHistoryMgr getInstance(){
		return HotSwap.getInstance().getSingleton(DeviceOperateHistoryMgr.class);
	}

	
	public List<OperateHistory> getOperateHistory(String clientId,String createdDate){
		String uriPath = "operateHistory";
		ReqMap reqMap = new ReqMap();
		reqMap.add("clientId", clientId);
		reqMap.add("createdDate", createdDate);
		int pageItemCount = -1;
		int pageNo = -1;
		return DeviceOperateHistoryDAO.getInstance().findWithReqParam(uriPath,reqMap,pageItemCount, pageNo);
	}
}
