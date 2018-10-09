package com.hq.customerMS.service.cuser.bs;

import com.hq.customerMS.service.cuser.data.CUser;
import com.zenmind.common.hotSwap.HotSwap;

public class CUserQueryMgr {
	public static CUserQueryMgr getInstance(){
		return HotSwap.getInstance().getSingleton(CUserQueryMgr.class);
	}

	public CUser get(long id) {
		return CUserDataHolder.getInstance().get(id);
	}
	
	public CUser findByPhone(long phone) {
		return CUserDataHolder.getInstance().findByPhone(phone);
	}
	
	public CUser findByWxUnionId(String wxUnionId) {
		return CUserDataHolder.getInstance().findByWxUnionId(wxUnionId);
	}
	
	public CUser findByPriAccountNum(String priAccountNum) {
		return CUserDataHolder.getInstance().findByPriAccountNum(priAccountNum);
	}
}
