package com.hq.chainStore.service.appVersion.data;

import com.zenmind.common.hotSwap.HotSwap;

public class AppVersionSynDataHolder{

	public static AppVersionSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(AppVersionSynDataHolder.class);
	}

	public AppVersion getData(String ownerId, String targetId) {
		return AppVersionDAO.getInstance().get(targetId);
	}

}
