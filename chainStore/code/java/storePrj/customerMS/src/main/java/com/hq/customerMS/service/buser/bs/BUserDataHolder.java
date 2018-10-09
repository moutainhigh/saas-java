package com.hq.customerMS.service.buser.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.buser.data.BUserCacheDAO;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeClient.service.buser.bs.BUserClientMgr;
import com.hq.storeClient.service.buser.data.BUser;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserDataHolder {

	public static BUserDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(BUserDataHolder.class);
	}

	public BUser get(long id) {
		BUser data = BUserCacheDAO.getInstance().get(id);
		if (data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = BUserClientMgr.getInstance().get(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public PageResp<BUser> findByCond(BUserCommQueryForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<BUser> page = BUserClientMgr.getInstance().findByCond(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return page;
	}
}
