package com.hq.chainStore.service.buser.bs;

import java.util.List;
import java.util.Set;

import com.hq.chainStore.service.buser.apiData.BUserAddApiForm;
import com.hq.chainStore.service.buser.apiData.BUserChangePasswordApiData;
import com.hq.chainStore.service.buser.apiData.BUserLoginApiForm;
import com.hq.chainStore.service.buser.apiData.BUserUpdateApiForm;
import com.hq.chainStore.service.buser.apiData.BUserUpdateInfoApiData;
import com.hq.chainStore.service.buser.apiData.BUserUpdateType;
import com.hq.chainStore.service.buser.apiData.BUserUpdateVipTypeData;
import com.hq.chainStore.service.buser.apiData.BUserVipRechargeData;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.buser.data.BUserDAO;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestResp;

public class BUserMgr {

	public static BUserMgr getInstance(){
		return HotSwap.getInstance().getSingleton(BUserMgr.class);
	}

	public BUser get(long id) {
		return BUserDAO.getInstance().get(id);
	}
	
	public BUser findByPhone(long phone) {

		final String uriPath = "findByPhone";
		ReqMap reqMap = ReqMap.newInstance().add("phone", phone);
		BUser buser = BUserDAO.getInstance().findOneWithReqParam(uriPath, reqMap);
		return buser;
	}
	
	public List<BUser> findDevUserList(int pageItemCount, int pageNo) {
		final String uriPath = "findDevUserList";
		return BUserDAO.getInstance().findWithReqParam(uriPath, ReqMap.newInstance(), pageItemCount, pageNo);
	}
	
	public RestResp loginWithTestPhone(BUserLoginApiForm loginForm) {
		final String actionName = "loginWithTestPhone";
		return BUserDAO.getInstance().rawReq(actionName, loginForm);
	}
	
	public List<BUser> findByMultitId(Set<Long> ids) {
		return findByMultitId(StringUtils4Client.join(ids, ","));
	}
	
	public List<BUser> findByMultitId(String idList) {
		String[] split = idList.split(",");
		return findByMultitId(idList,split.length,1);
	}
	
	public List<BUser> findByMultitId(String idList,int pageItemCount, int pageNo) {
		ReqMap reqMap = ReqMap.newInstance().add("idList",idList);
		return BUserDAO.getInstance().findByMultitId(reqMap,pageItemCount,pageNo);
	}
	
	public RestResp reg(BUserAddApiForm formInfo) {
		final String actionName = "reg";
		RestResp resp = BUserDAO.getInstance().rawReq(actionName, formInfo);
		return resp;
	}
	
	
	public RestResp login(BUserLoginApiForm loginForm) {
		final String actionName = "login";
		RestResp resp = BUserDAO.getInstance().rawReq(actionName , loginForm);
		return resp;
	}
	
	public void updateInfo(long buserId,BUserUpdateInfoApiData updateInfoData) {
		BUserUpdateApiForm updateForm = BUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(BUserUpdateType.updateInfo.ordinal());
		updateForm.setUpdateInfoData(updateInfoData);
		update(buserId,updateForm);
	}
	
	public void changePassword(long buserId,BUserChangePasswordApiData changePasswordData) {
		BUserUpdateApiForm updateForm = BUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(BUserUpdateType.changePassword.ordinal());
		updateForm.setChangePasswordData(changePasswordData);
		update(buserId,updateForm);
	}
	
	public void updateVipType(long buserId, BUserUpdateVipTypeData updateVipTypeData) {
		BUserUpdateApiForm updateForm = BUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(BUserUpdateType.UpdateVipType.ordinal());
		updateForm.setUpdateVipTypeData(updateVipTypeData);
		update(buserId,updateForm);
	}
	
	public void vipRecharge(long buserId, BUserVipRechargeData vipRechargeData) {
		BUserUpdateApiForm updateForm = BUserUpdateApiForm.newInstance();
		updateForm.setUpdateType(BUserUpdateType.VipRecharge.ordinal());
		updateForm.setVipRechargeData(vipRechargeData);
		update(buserId,updateForm);
	}
	
	private void update(long buserId,BUserUpdateApiForm updateForm) {
		BUserDAO.getInstance().update(buserId, updateForm);
	}
}
