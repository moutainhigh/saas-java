package com.hq.customerMS.service.buser.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeClient.service.buser.data.BUser;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserMgr {
	public static BUserMgr getInstance(){
		return HotSwap.getInstance().getSingleton(BUserMgr.class);
	}

	public BUser get(long id) {
		return BUserDataHolder.getInstance().get(id);
	}
	
	public PageResp<BUser> findByCond(BUserCommQueryForm queryForm) {
		return BUserDataHolder.getInstance().findByCond(queryForm);
	}
}
