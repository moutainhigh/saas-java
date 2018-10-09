package com.hq.storeMS.service.muser.bs;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.validate.AppIdThreadLocal;
import com.hq.storeMS.service.muser.data.MUserCacheDAO;
import com.hq.storeManagerRestClient.service.muser.bs.MUserClientMgr;
import com.hq.storeManagerRestClient.service.muser.data.MUser;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserDataHolder {
	
	public static MUserDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(MUserDataHolder.class);
	}
	
	public MUser get(long id) {
		MUser data = MUserCacheDAO.getInstance().get(id);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = MUserClientMgr.getInstance().getMUser(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public MUser findByAccount(String account) {
		MUser data = MUserCacheDAO.getInstance().getOne(account);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = MUserClientMgr.getInstance().findByAccount(account);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
}
