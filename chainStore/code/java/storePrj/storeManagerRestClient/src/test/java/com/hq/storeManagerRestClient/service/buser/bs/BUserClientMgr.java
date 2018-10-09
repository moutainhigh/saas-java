package com.hq.storeManagerRestClient.service.buser.bs;

import com.hq.storeManagerRestClient.common.exception.AppNameAuthUtils;
import com.hq.storeManagerRestClient.common.exception.AppNameEnum;
import com.hq.storeManagerRestClient.service.buser.data.BUser;
import com.hq.storeManagerRestClient.service.buser.data.BUserDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserClientMgr {

	public static BUserClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(BUserClientMgr.class);
	}
	
	public BUser getBUser(long buserId) {
		AppNameAuthUtils.getInstance().checkMS(AppNameEnum.StoreMS);
		return BUserDAO.getInstance().get(buserId);
	}
}
