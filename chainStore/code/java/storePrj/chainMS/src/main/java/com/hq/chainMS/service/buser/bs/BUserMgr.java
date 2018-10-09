package com.hq.chainMS.service.buser.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.buser.apiData.BUserAddByChainForm;
import com.hq.storeClient.service.buser.apiData.BUserChainQueryForm;
import com.hq.storeClient.service.buser.data.BUser;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserMgr {

	public static BUserMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BUserMgr.class);
	}

	public PageResp<BUser> findByChain(BUserChainQueryForm queryForm) {
		return BUserDataHolder.getInstance().findByChain(queryForm);
	}
	
	public BUser findByPhone(long phone) {
		return BUserDataHolder.getInstance().findByPhone(phone);
	}
	
	public BUser regByChainForm(BUserAddByChainForm formInfo) {
		return BUserDataHolder.getInstance().regByChainForm(formInfo);
	}
	
	public BUser get(long buserId) {
		return BUserDataHolder.getInstance().get(buserId);
	}

}
