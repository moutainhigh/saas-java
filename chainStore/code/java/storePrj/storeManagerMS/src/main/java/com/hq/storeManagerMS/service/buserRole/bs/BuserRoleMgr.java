package com.hq.storeManagerMS.service.buserRole.bs;

import com.hq.storeClient.service.buserRole.apiData.BuserRoleUpdateInfoForm;
import com.hq.storeClient.service.buserRole.data.BuserRole;
import com.zenmind.common.hotSwap.HotSwap;

public class BuserRoleMgr {

	public static BuserRoleMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleMgr.class);
	}

	public void update(long buserId, BuserRoleUpdateInfoForm updateForm) {
		BuserRoleDataHolder.getInstance().updateBuserRoleInfo(buserId, updateForm);
	}

	public BuserRole get(long buserId) {
		return BuserRoleDataHolder.getInstance().get(buserId);
	}

}
