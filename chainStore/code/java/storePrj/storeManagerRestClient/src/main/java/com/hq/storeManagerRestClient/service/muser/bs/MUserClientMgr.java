package com.hq.storeManagerRestClient.service.muser.bs;

import com.hq.storeManagerRestClient.service.muser.apiData.MUserAddApiForm;
import com.hq.storeManagerRestClient.service.muser.apiData.MUserChangePasswordApiData;
import com.hq.storeManagerRestClient.service.muser.apiData.MUserLoginApiForm;
import com.hq.storeManagerRestClient.service.muser.apiData.MUserUpdateApiForm;
import com.hq.storeManagerRestClient.service.muser.apiData.MUserUpdateInfoApiData;
import com.hq.storeManagerRestClient.service.muser.apiData.MUserUpdateRoleSetApiData;
import com.hq.storeManagerRestClient.service.muser.apiData.MUserUpdateStatusApiData;
import com.hq.storeManagerRestClient.service.muser.apiData.MUserUpdateType;
import com.hq.storeManagerRestClient.service.muser.data.MUser;
import com.hq.storeManagerRestClient.service.muser.data.MUserDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestResp;

public class MUserClientMgr {

	public static MUserClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(MUserClientMgr.class);
	}
	
	public MUser findByAccount(String account) {
		final String findPath = "findByAccount";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("account", account);
		return MUserDAO.getInstance().findOneWithReqParam(findPath, reqMap);
	}
	
	public MUser getMUser(long muserId) {
		return MUserDAO.getInstance().get(muserId);
	}
	
	public RestResp reg(MUserAddApiForm formInfo) {
		final String actionName = "reg";
		RestResp resp = MUserDAO.getInstance().rawReq(actionName, formInfo);
		return resp;
	}
	
	public RestResp login(MUserLoginApiForm loginForm) {
		final String actionName = "login";
		RestResp resp = MUserDAO.getInstance().rawReq(actionName , loginForm);
		return resp;
	}
	
	public void updateInfo(long muserId, MUserUpdateInfoApiData updateInfoData) {
		MUserUpdateApiForm updateForm = MUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(MUserUpdateType.updateInfo.ordinal());
		updateForm.setUpdateInfoData(updateInfoData);
		update(muserId,updateForm);
	}
	
	public void updRoleSet4Clerk(long muserId, MUserUpdateRoleSetApiData updateRoleSetApiData) {
		MUserUpdateApiForm updateForm = MUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(MUserUpdateType.updRoleSet4Clerk.ordinal());
		updateForm.setUpdateRoleSetApiData(updateRoleSetApiData);
		update(muserId,updateForm);
	}
	
	public void updMUserStatus(long muserId, MUserUpdateStatusApiData updateStatusApiData) {
		MUserUpdateApiForm updateForm = MUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(MUserUpdateType.updMUserStatus.ordinal());
		updateForm.setUpdateStatusApiData(updateStatusApiData);
		update(muserId,updateForm);
	}
	
	public void changePassword(long muserId, MUserChangePasswordApiData changePasswordData) {
		MUserUpdateApiForm updateForm = MUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(MUserUpdateType.changePassword.ordinal());
		updateForm.setChangePasswordData(changePasswordData);
		update(muserId,updateForm);
	}
	
	private void update(long muserId,MUserUpdateApiForm updateForm) {
		MUserDAO.getInstance().update(muserId, updateForm);
	}
}
