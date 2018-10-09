package com.hq.chainStore.service.buserRole.bs;

import com.hq.chainStore.service.buserRole.data.BuserRole;
import com.hq.chainStore.service.buserRole.data.BuserRoleDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class BuserRoleClientMgr {

	public static BuserRoleClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleClientMgr.class);
	}

	public BuserRole get(long buserId) {
		return BuserRoleDAO.getInstance().get(buserId);
	}

}
