package com.hq.storeMS.service.buser.bs;

import com.hq.storeMS.service.buser.data.StoreBUser;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreBUserMgr {

	public static StoreBUserMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreBUserMgr.class);
	}
	
	public StoreBUser get(long storeId) {
		return StoreBUserDataHolder.getInstance().get(storeId);
	}
}
