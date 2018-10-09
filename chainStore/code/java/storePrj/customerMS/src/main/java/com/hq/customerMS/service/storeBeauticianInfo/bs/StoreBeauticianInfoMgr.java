package com.hq.customerMS.service.storeBeauticianInfo.bs;

import com.hq.storeClient.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreBeauticianInfoMgr {

	public static StoreBeauticianInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoMgr.class);
	}

	public StoreBeauticianInfo get(long id) {
		return StoreBeauticianInfoDataHolder.getInstance().get(id);
	}

}
