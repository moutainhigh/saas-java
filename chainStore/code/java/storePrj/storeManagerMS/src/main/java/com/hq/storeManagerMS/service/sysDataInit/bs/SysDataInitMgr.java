package com.hq.storeManagerMS.service.sysDataInit.bs;

import com.hq.storeManagerMS.service.muser.bs.MUserMgr;
import com.hq.storeManagerMS.service.muserAdminRole.bs.MUserAdminRoleMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class SysDataInitMgr {

	public static SysDataInitMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SysDataInitMgr.class);
	}

	public void init() {
		MUserAdminRoleMgr.getInstance().init();
		MUserMgr.getInstance().init();
//		VipLevelTypeInitMgr.getInstance().init();
//		VipLevelInitMgr.getInstance().init();
	}
}
