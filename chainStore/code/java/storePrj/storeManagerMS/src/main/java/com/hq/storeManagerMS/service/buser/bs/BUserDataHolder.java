package com.hq.storeManagerMS.service.buser.bs;

import com.hq.storeClient.service.buser.apiData.BUserUpdateApiForm;
import com.hq.storeClient.service.buser.bs.BUserClientMgr;
import com.hq.storeClient.service.buser.data.BUser;
import com.hq.storeManagerMS.common.constants.ServerConstants;
import com.hq.storeManagerMS.common.validate.AppIdThreadLocal;
import com.hq.storeManagerMS.service.buser.data.BUserCacheDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserDataHolder{
	
	public static BUserDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BUserDataHolder.class);
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
	
	public BUser get(long buserId) {
		BUser data = BUserCacheDAO.getInstance().get(buserId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = BUserClientMgr.getInstance().get(buserId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}	
	
	public BUser update(long id, BUserUpdateApiForm updateForm){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		BUser data = BUserClientMgr.getInstance().update(id, updateForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
}