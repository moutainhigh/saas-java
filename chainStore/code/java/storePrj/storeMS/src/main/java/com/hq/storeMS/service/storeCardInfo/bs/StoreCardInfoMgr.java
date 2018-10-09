package com.hq.storeMS.service.storeCardInfo.bs;

import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoMgr {

	public static StoreCardInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoMgr.class);
	}

	public void updateStoreCardInfo(StoreCardInfo storeCardInfo) {
		StoreCardInfoDataHolder.getInstance().update(storeCardInfo);
	}
	
	public StoreCardInfo getByStoreId(long storeId) {
		StoreCardInfo info = StoreCardInfoDataHolder.getInstance().get(storeId);
		if(info == null){//不存在，则新增
			info = StoreCardInfo.newInstance(storeId);
			StoreCardInfoDataHolder.getInstance().addWithId(info);
		}
		return info;
	}

	public void addStoreCardInfo(StoreCardInfo target) {
		StoreCardInfoDataHolder.getInstance().addWithId(target);
	}
}
