package com.hq.chainStore.service.areaCode.data;

import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeSynDataHolder {

	public static AreaCodeSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(AreaCodeSynDataHolder.class);
	}

	public AreaCode getData(String ownerId, String targetId) {
		return AreaCodeDAO.getInstance().get(targetId);
	}

}
