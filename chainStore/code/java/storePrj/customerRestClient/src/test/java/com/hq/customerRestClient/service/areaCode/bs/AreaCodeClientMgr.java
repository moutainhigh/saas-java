package com.hq.customerRestClient.service.areaCode.bs;

import java.util.List;

import com.hq.customerRestClient.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.customerRestClient.service.areaCode.data.AreaCode;
import com.hq.customerRestClient.service.areaCode.data.AreaCodeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeClientMgr {

	public static AreaCodeClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeClientMgr.class);
	}
	
	public List<AreaCode> findByCondUnAuth(AreaCodeQueryForm queryForm) {
		final String findPath = "findByCond";
		return AreaCodeDAO.getInstance().findByCondUnAuth(findPath, queryForm);
	}
	
}
