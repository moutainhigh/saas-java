package com.hq.storeManagerMS.service.buserRole.bs;

import com.hq.storeClient.service.buserRole.apiData.BuserRoleUpdateInfoForm;
import com.hq.storeClient.service.buserRole.bs.BuserRoleClientMgr;
import com.hq.storeClient.service.buserRole.data.BuserRole;
import com.hq.storeManagerMS.common.constants.ServerConstants;
import com.hq.storeManagerMS.common.validate.AppIdThreadLocal;
import com.hq.storeManagerMS.service.buserRole.data.BuserRoleCacheDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class BuserRoleDataHolder {
	
	public static BuserRoleDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BuserRoleDataHolder.class);
	}
	
	public void updateBuserRoleInfo(long buserId, BuserRoleUpdateInfoForm updateForm){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		BuserRoleClientMgr.getInstance().updateBuserRoleInfo(buserId, updateForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
	public BuserRole get(long id) {
		BuserRole data = BuserRoleCacheDAO.getInstance().get(id);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = BuserRoleClientMgr.getInstance().get(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	
}
