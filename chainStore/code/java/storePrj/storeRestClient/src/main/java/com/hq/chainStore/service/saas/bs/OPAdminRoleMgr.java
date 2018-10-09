package com.hq.chainStore.service.saas.bs;

import java.util.List;

import com.hq.chainStore.service.opuser.data.adminRole.OPAdminRole;
import com.hq.chainStore.service.saas.apiData.OPAdminRoleUpdateApiForm;
import com.hq.chainStore.service.saas.apiData.OPAdminRoleUpdateInfoApiData;
import com.hq.chainStore.service.saas.apiData.OPAdminRoleUpdateType;
import com.hq.chainStore.service.saas.data.OPAdminRoleDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class OPAdminRoleMgr {

	public static OPAdminRoleMgr getInstance(){
		return HotSwap.getInstance().getSingleton(OPAdminRoleMgr.class);
	}

	public OPAdminRole get(long id) {
		return OPAdminRoleDAO.getInstance().get(id);
	}
	public List<OPAdminRole> listAll() {
		return OPAdminRoleDAO.getInstance().list();
	}
	
	public OPAdminRole add(OPAdminRole formInfo) {

		OPAdminRole returnObj = OPAdminRoleDAO.getInstance().add(formInfo);
		return returnObj;
	}


	public void updateOPAdminRoleInfo(long opId, OPAdminRoleUpdateInfoApiData inputData) {
		OPAdminRoleUpdateApiForm updateForm = OPAdminRoleUpdateApiForm.newInstance();
		updateForm.setUpdateData(inputData);
		updateForm.setUpdateTypeEnum(OPAdminRoleUpdateType.updateInfo);
		
		update(opId, updateForm);
	}
	
	private void update(long opId, OPAdminRoleUpdateApiForm updateForm) {
		OPAdminRoleDAO.getInstance().update(opId, updateForm);
	}
	
	
}
