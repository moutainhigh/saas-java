package com.hq.storeClient.service.buserRole.bs;

import com.hq.storeClient.service.buserRole.apiData.BatchPermForm;
import com.hq.storeClient.service.buserRole.apiData.BuserRoleUpdateApiForm;
import com.hq.storeClient.service.buserRole.apiData.BuserRoleUpdateInfoForm;
import com.hq.storeClient.service.buserRole.apiData.BuserRoleUpdateType;
import com.hq.storeClient.service.buserRole.data.BuserRole;
import com.hq.storeClient.service.buserRole.data.BuserRoleDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class BuserRoleClientMgr {

	public static BuserRoleClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleClientMgr.class);
	}

	public BuserRole get(long buserId) {
		return BuserRoleDAO.getInstance().get(buserId);
	}

	public void update(long buserId, BuserRoleUpdateApiForm updateForm) {
		BuserRoleDAO.getInstance().update(buserId, updateForm);
	}
	
	public void batchPerm(BatchPermForm inputForm) {
		final String actionName = "batchPerm";
		BuserRoleDAO.getInstance().rawReq(actionName, inputForm);
	}
	
	public void updateBuserRoleInfo(long buserId, BuserRoleUpdateInfoForm inputForm) {
		BuserRoleUpdateApiForm updateForm = BuserRoleUpdateApiForm.newInstance();
		updateForm.setUpdateType(BuserRoleUpdateType.UpdateInfo.ordinal());
		updateForm.setUpdateInfoData(inputForm);
		update(buserId, updateForm);
	}

}
