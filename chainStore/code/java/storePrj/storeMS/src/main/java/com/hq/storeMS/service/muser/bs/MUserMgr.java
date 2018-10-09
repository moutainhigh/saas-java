package com.hq.storeMS.service.muser.bs;

import com.hq.storeManagerRestClient.service.muser.data.MUser;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserMgr {

	public static MUserMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MUserMgr.class);
	}
	
	public MUser get(long id) {
		return MUserDataHolder.getInstance().get(id);
	}
	
	public MUser findByAccount(String account) {
		return MUserDataHolder.getInstance().findByAccount(account);
	}
}
