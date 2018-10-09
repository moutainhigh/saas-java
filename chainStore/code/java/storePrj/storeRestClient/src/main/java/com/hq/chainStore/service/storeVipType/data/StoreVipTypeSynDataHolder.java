package com.hq.chainStore.service.storeVipType.data;

import com.zenmind.common.hotSwap.HotSwap;

public class StoreVipTypeSynDataHolder {

	public static StoreVipTypeSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreVipTypeSynDataHolder.class);
	}

	public StoreVipType getData(String ownerId, String targetId) {
		return StoreVipTypeDAO.getInstance().get(targetId);
	}

}
