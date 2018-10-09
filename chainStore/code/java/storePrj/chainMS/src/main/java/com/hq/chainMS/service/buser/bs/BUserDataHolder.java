package com.hq.chainMS.service.buser.bs;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.chainMS.service.buser.data.BUserCacheDAO;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.buser.apiData.BUserAddByChainForm;
import com.hq.storeClient.service.buser.apiData.BUserChainQueryForm;
import com.hq.storeClient.service.buser.bs.BUserClientMgr;
import com.hq.storeClient.service.buser.data.BUser;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserDataHolder {

	public static BUserDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(BUserDataHolder.class);
	}
	
	public BUser get(long id) {
		BUser data = BUserCacheDAO.getInstance().get(id);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = BUserClientMgr.getInstance().get(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public BUser findByPhone(long phone) {
		BUser data = BUserCacheDAO.getInstance().getOne(String.valueOf(phone));
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = BUserClientMgr.getInstance().findByPhone(phone);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public PageResp<BUser> findByChain(BUserChainQueryForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<BUser> data = BUserClientMgr.getInstance().findByChain(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public BUser regByChainForm(BUserAddByChainForm formInfo) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		BUser data = BUserClientMgr.getInstance().regByChainForm(formInfo);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
}
