package com.hq.chainStore.service.report.data;

import com.zenmind.common.hotSwap.HotSwap;

public class SpecialDataSynDataHolder {

	public static SpecialDataSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(SpecialDataSynDataHolder.class);
	}

	public SpecialData getData(String ownerId, String targetId) {
		return SpecialDataDAO.getInstance().get(targetId);
	}
}
