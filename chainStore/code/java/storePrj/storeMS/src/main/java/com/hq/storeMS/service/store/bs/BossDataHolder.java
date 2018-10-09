package com.hq.storeMS.service.store.bs;

import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.store.data.Store;
import com.zenmind.common.hotSwap.HotSwap;

public class BossDataHolder {
	
	public static BossDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BossDataHolder.class);
	}
	
	public long getBossId(long storeId) {
		long bossId = ValidateInfoThreadLocal.getInstance().getBossId();
		if(bossId == 0 && storeId > 0) {
			Store store = StoreDataHolder.getInstance().get(storeId);
			if(store!=null) {
				bossId = store.getBossId();
			}
		}
		return bossId;
	}
	
}
