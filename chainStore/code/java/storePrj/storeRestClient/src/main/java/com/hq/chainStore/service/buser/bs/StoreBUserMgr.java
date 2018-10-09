package com.hq.chainStore.service.buser.bs;

import com.hq.chainStore.service.buser.data.StoreBUser;
import com.hq.chainStore.service.buser.data.StoreBUserDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreBUserMgr {

	public static StoreBUserMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreBUserMgr.class);
	}

	public StoreBUser get(long storeId) {
		return StoreBUserDAO.getInstance().get(storeId);
	}
}
