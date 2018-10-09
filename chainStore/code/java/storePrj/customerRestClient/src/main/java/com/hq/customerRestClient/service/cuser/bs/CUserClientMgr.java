package com.hq.customerRestClient.service.cuser.bs;

import com.hq.customerRestClient.service.cuser.apiData.CUserAddApiForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserAddByWxForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserAddressAddForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserAddressRemoveForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserAddressUpdateForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserChangeDefaultAddressForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserChangePasswordApiData;
import com.hq.customerRestClient.service.cuser.apiData.CUserLoginApiForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserLoginByCodeApiForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserLoginWithJscodeForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserLoginWithWxInfoForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserResetPasswordData;
import com.hq.customerRestClient.service.cuser.apiData.CUserUpdateApiForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserUpdateInfoApiData;
import com.hq.customerRestClient.service.cuser.apiData.CUserUpdateType;
import com.hq.customerRestClient.service.cuser.apiData.CuserAdd4Ms;
import com.hq.customerRestClient.service.cuser.apiData.CuserUpdate4Ms;
import com.hq.customerRestClient.service.cuser.data.CUser;
import com.hq.customerRestClient.service.cuser.data.CUserDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestResp;

public class CUserClientMgr {

	public static CUserClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(CUserClientMgr.class);
	}

	public CUser addFromMs(CuserAdd4Ms inputForm) {
		final String actionName = "addFromMs";
		RestResp resp = CUserDAO.getInstance().rawReq(actionName, inputForm);
		return JsonUtil.getInstance().fromJson(resp.gettJson(), CUser.class);
	}
	
	public CUser updateFromMs(CuserUpdate4Ms inputForm) {
		final String actionName = "updateFromMs";
		RestResp resp = CUserDAO.getInstance().rawReq(actionName, inputForm);
		return JsonUtil.getInstance().fromJson(resp.gettJson(), CUser.class);
	}
	
	public CUser get(long cuserId) {
		return CUserDAO.getInstance().get(cuserId);
	}

	public CUser findByPhone(long phone) {
		final String uriPath = "findByPhone";
		ReqMap reqMap = ReqMap.newInstance().add("phone", phone);
		CUser user = CUserDAO.getInstance().findOneWithReqParam(uriPath, reqMap);
		return user;
	}

	public RestResp reg(CUserAddApiForm formInfo) {
		final String actionName = "reg";
		RestResp resp = CUserDAO.getInstance().rawReq(actionName, formInfo);
		return resp;
	}
	
	public RestResp regWithWxInfo(CUserAddByWxForm formInfo) {
		final String actionName = "regWithWxInfo";
		RestResp resp = CUserDAO.getInstance().rawReq(actionName, formInfo);
		return resp;
	}

	public RestResp login(CUserLoginApiForm loginForm) {
		final String actionName = "login";
		RestResp resp = CUserDAO.getInstance().rawReq(actionName, loginForm);
		return resp;
	}
	
	public RestResp loginByCode(CUserLoginByCodeApiForm loginForm) {
		final String actionName = "loginByCode";
		RestResp resp = CUserDAO.getInstance().rawReq(actionName, loginForm);
		return resp;
	}
	
	public RestResp loginByWxInfo(CUserLoginWithWxInfoForm loginForm) {
		final String actionName = "loginByWxInfo";
		RestResp resp = CUserDAO.getInstance().rawReq(actionName, loginForm);
		return resp;
	}
	
	public RestResp loginWithJscode(CUserLoginWithJscodeForm loginForm) {
		final String actionName = "loginWithJscode";
		RestResp resp = CUserDAO.getInstance().rawReq(actionName, loginForm);
		return resp;
	}
	
	public RestResp resetPassword(CUserResetPasswordData resetPasswordData) {
		final String actionName = "resetPassword";
		RestResp resp = CUserDAO.getInstance().rawReq(actionName, resetPasswordData);
		return resp;
	}

	public void updateInfo(long userId, CUserUpdateInfoApiData updateInfoData) {
		CUserUpdateApiForm updateForm = CUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(CUserUpdateType.UpdateInfo.ordinal());
		updateForm.setUpdateInfoData(updateInfoData);
		update(userId, updateForm);
	}

	public void changePassword(long userId,CUserChangePasswordApiData changePasswordData) {
		CUserUpdateApiForm updateForm = CUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(CUserUpdateType.ChangePassword.ordinal());
		updateForm.setChangePasswordData(changePasswordData);
		update(userId, updateForm);
	}
	
	public void addAddress(long userId, CUserAddressAddForm addressAddData) {
		CUserUpdateApiForm updateForm = CUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(CUserUpdateType.AddAddress.ordinal());
		updateForm.setAddressAddData(addressAddData);
		update(userId, updateForm);
	}
	
	public void updateAddress(long userId, CUserAddressUpdateForm addressUpdateData) {
		CUserUpdateApiForm updateForm = CUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(CUserUpdateType.UpdateAddress.ordinal());
		updateForm.setAddressUpdateData(addressUpdateData);
		update(userId, updateForm);
	}
	
	public void removeAddress(long userId, CUserAddressRemoveForm addressRemoveData) {
		CUserUpdateApiForm updateForm = CUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(CUserUpdateType.RemoveAddress.ordinal());
		updateForm.setAddressRemoveData(addressRemoveData);
		update(userId, updateForm);
	}
	
	public void changeDefaultAddress(long userId, CUserChangeDefaultAddressForm changeDefaultAddressData) {
		CUserUpdateApiForm updateForm = CUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(CUserUpdateType.ChangeDefaultAddress.ordinal());
		updateForm.setChangeDefaultAddressData(changeDefaultAddressData);
		update(userId, updateForm);
	}
	
	public void update(long userId, CUserUpdateApiForm updateForm) {
		CUserDAO.getInstance().update(userId, updateForm);
	}

}
