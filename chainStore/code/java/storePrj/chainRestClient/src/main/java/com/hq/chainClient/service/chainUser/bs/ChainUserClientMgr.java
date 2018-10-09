package com.hq.chainClient.service.chainUser.bs;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chainUser.apiData.ChainUserLoginForm;
import com.hq.chainClient.service.chainUser.apiData.ChainUserQueryForm;
import com.hq.chainClient.service.chainUser.apiData.ChainUserUpdateForm;
import com.hq.chainClient.service.chainUser.apiData.ChainUserUpdateInfoForm;
import com.hq.chainClient.service.chainUser.apiData.ChainUserUpdateType;
import com.hq.chainClient.service.chainUser.apiData.ChangePasswordForm;
import com.hq.chainClient.service.chainUser.apiData.RegistForm;
import com.hq.chainClient.service.chainUser.data.ChainUser;
import com.hq.chainClient.service.chainUser.data.ChainUserDAO;
import com.hq.chainClient.service.chainUser.data.ChainUserDto;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestResp;

public class ChainUserClientMgr {

	public static ChainUserClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainUserClientMgr.class);
	}
	
	public ChainUser get(long id) {
		return ChainUserDAO.getInstance().get(id);
	}
	
	public ChainUser findByPhone(long phone) {
		final String uriPath = "findByPhone";
		ReqMap reqMap = ReqMap.newInstance().add("phone", phone);
		return ChainUserDAO.getInstance().findOneWithReqParam(uriPath, reqMap);
	}
	
	public PageResp<ChainUserDto> findByCond(ChainUserQueryForm queryForm) {
		return ChainUserDAO.getInstance().findByCond(queryForm);
	}
	
	public RestResp reg(RegistForm formInfo) {
		final String actionName = "reg";
		return ChainUserDAO.getInstance().rawReq(actionName, formInfo);
	}
	
	public RestResp login(ChainUserLoginForm loginForm) {
		final String actionName = "login";
		return ChainUserDAO.getInstance().rawReq(actionName , loginForm);
	}
	
	public void updateInfo(long chainUserId, ChainUserUpdateInfoForm inputForm) {
		ChainUserUpdateForm updateForm = ChainUserUpdateForm.newInstance();
		updateForm.setUpdateType(ChainUserUpdateType.UpdateInfo.ordinal());
		updateForm.setChainUserUpdateInfoForm(inputForm);
		update(chainUserId,updateForm);
	}
	
	public void changePassword(long chainUserId, ChangePasswordForm inputForm) {
		ChainUserUpdateForm updateForm = ChainUserUpdateForm.newInstance();
		updateForm.setUpdateType(ChainUserUpdateType.ChangePassword.ordinal());
		updateForm.setChangePasswordForm(inputForm);
		update(chainUserId,updateForm);
	}
	
	private void update(long chainUserId, ChainUserUpdateForm updateForm) {
		ChainUserDAO.getInstance().update(chainUserId, updateForm);
	}
	
}
