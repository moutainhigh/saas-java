package com.hq.storeClient.service.appVersion.bs;

import com.hq.storeClient.service.appVersion.data.AppVersion;
import com.hq.storeClient.service.appVersion.data.AppVersionDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class AppVersionClientMgr {

	public static AppVersionClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(AppVersionClientMgr.class);
	}

	public AppVersion findByName(String appName) {
		final String findPath = "findByName";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("appName", appName);
		return AppVersionDAO.getInstance().findOneWithReqParam(findPath, reqMap);
	}
}
