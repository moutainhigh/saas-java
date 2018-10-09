package com.hq.storeClient.service.areaCode.bs;

import java.util.List;

import com.hq.storeClient.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.storeClient.service.areaCode.data.AreaCode;
import com.hq.storeClient.service.areaCode.data.AreaCodeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeClientMgr {

	public static AreaCodeClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(AreaCodeClientMgr.class);
	}

	public List<AreaCode> findByCond(AreaCodeQueryForm queryForm) {
		final String findPath = "findByCond";
		return AreaCodeDAO.getInstance().findWithReqParam(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
	}
	
	public AreaCode getAreaCode(long areaCodeId) {
		return AreaCodeDAO.getInstance().get(areaCodeId);
	}
}
