package com.hq.storeMS.service.storeProductInfo.bs;

import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreProductInfoMgr {

	public static StoreProductInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreProductInfoMgr.class);
	}

	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * 
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addWithId(StoreProductInfo target) {
		StoreProductInfoDataHolder.getInstance().addWithId(target);
	}

	public void update(StoreProductInfo target) {
		StoreProductInfoDataHolder.getInstance().update(target);
	}

	public StoreProductInfo getByStoreId(long storeId) {
		StoreProductInfo storeProductInfo = StoreProductInfoDataHolder.getInstance().get(storeId);
		if(storeProductInfo == null){//不存在，则新增
			storeProductInfo = StoreProductInfo.newInstance(storeId);
			StoreProductInfoDataHolder.getInstance().addWithId(storeProductInfo);
		}
		return storeProductInfo;
	}
}
