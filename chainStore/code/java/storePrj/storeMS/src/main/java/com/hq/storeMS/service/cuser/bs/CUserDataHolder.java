package com.hq.storeMS.service.cuser.bs;

import com.hq.customerRestClient.service.cuser.apiData.CuserAdd4Ms;
import com.hq.customerRestClient.service.cuser.apiData.CuserUpdate4Ms;
import com.hq.customerRestClient.service.cuser.bs.CUserClientMgr;
import com.hq.customerRestClient.service.cuser.data.CUser;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.validate.AppIdThreadLocal;
import com.hq.storeMS.service.cuser.data.CUserCacheDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class CUserDataHolder {

	public static CUserDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(CUserDataHolder.class);
	}

	public CUser updateFromMs(CuserUpdate4Ms inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		CUser data = CUserClientMgr.getInstance().updateFromMs(inputForm);
		AppIdThreadLocal.getInstance().remove();
		return data;
	}
	
	public CUser addFromMs(CuserAdd4Ms inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		CUser data = CUserClientMgr.getInstance().addFromMs(inputForm);
		AppIdThreadLocal.getInstance().remove();
		return data;
	}
	
	public CUser get(long id) {
		CUser data = CUserCacheDAO.getInstance().get(id);
		if(data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = CUserClientMgr.getInstance().get(id);
		AppIdThreadLocal.getInstance().remove();
		return data;
	}


	public CUser findByPhone(long phone) {
		CUser data = CUserCacheDAO.getInstance().getOne(String.valueOf(phone));
		if(data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = CUserClientMgr.getInstance().findByPhone(phone);
		AppIdThreadLocal.getInstance().remove();
		return data;
	}
}
