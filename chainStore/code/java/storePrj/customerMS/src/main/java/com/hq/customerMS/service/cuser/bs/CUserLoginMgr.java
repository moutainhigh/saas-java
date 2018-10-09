package com.hq.customerMS.service.cuser.bs;

import com.zenmind.common.hotSwap.HotSwap;

public class CUserLoginMgr {
	public static CUserLoginMgr getInstance(){
		return HotSwap.getInstance().getSingleton(CUserLoginMgr.class);
	}
}
