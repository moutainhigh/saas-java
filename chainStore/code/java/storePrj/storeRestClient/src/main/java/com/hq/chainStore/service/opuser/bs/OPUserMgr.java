package com.hq.chainStore.service.opuser.bs;

import java.util.List;
import java.util.Set;

import com.hq.chainStore.service.opuser.apiData.OPUserAddApiForm;
import com.hq.chainStore.service.opuser.apiData.OPUserChangePasswordApiData;
import com.hq.chainStore.service.opuser.apiData.OPUserLoginApiForm;
import com.hq.chainStore.service.opuser.apiData.OPUserUpdateApiForm;
import com.hq.chainStore.service.opuser.apiData.OPUserUpdateInfoApiData;
import com.hq.chainStore.service.opuser.apiData.OPUserUpdateRoleApiData;
import com.hq.chainStore.service.opuser.apiData.OPUserUpdateType;
import com.hq.chainStore.service.opuser.apiData.OPuserQueryApiForm;
import com.hq.chainStore.service.opuser.data.OPUser;
import com.hq.chainStore.service.opuser.data.OPUserDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestResp;

public class OPUserMgr {

	public static OPUserMgr getInstance(){
		return HotSwap.getInstance().getSingleton(OPUserMgr.class);
	}

	public OPUser get(long id) {
		return OPUserDAO.getInstance().get(id);
	}
	
	public List<OPUser> findOPuserList(OPuserQueryApiForm findForm) {
		final String findPath = "findOPuserList";
		ReqMap reqMap = ReqMap.newInstance().add("name", findForm.getName())
											.add("phone", findForm.getPhone());
		List<OPUser> opuserList = OPUserDAO.getInstance().findWithReqParam(findPath, reqMap, findForm.getPageItemCount(), findForm.getPageNo());
		return opuserList;
	}
	
	public List<OPUser> findByName(String name,int pageItemCount,int pageNo) {
		final String findPath = "findByName";
		ReqMap reqMap = ReqMap.newInstance().add("name", name);
		List<OPUser> opuserList = OPUserDAO.getInstance().findWithReqParam(findPath, reqMap, pageItemCount, pageNo);
		return opuserList;
	}
	
	public OPUser findByPhone(long phone) {
		final String findPath = "findByPhone";
		ReqMap reqMap = ReqMap.newInstance().add("phone", phone);
		OPUser opuser = OPUserDAO.getInstance().findOneWithReqParam(findPath, reqMap);
		return opuser;
	}
	
	public RestResp reg(OPUserAddApiForm formInfo) {

		final String actionName = "reg";
		RestResp resp = OPUserDAO.getInstance().rawReq(actionName, formInfo);
		return resp;
	}
	
	
	public RestResp login(OPUserLoginApiForm loginForm) {
		
		final String actionName = "login";
		RestResp resp = OPUserDAO.getInstance().rawReq(actionName , loginForm);
		return resp;
	}

	public void updateOPUserInfo(long opId, OPUserUpdateInfoApiData inputData) {
		OPUserUpdateApiForm updateForm = OPUserUpdateApiForm.newInstance();
		updateForm.setUpdateInfoData(inputData);
		updateForm.setUpdateTypeEnum(OPUserUpdateType.updateInfo);
		
		inputData.setOpuserId(opId);
		update(opId, updateForm);
	}
	public void updateOPUserRole(long opId, Set<Integer> roleIdSet) {
		OPUserUpdateApiForm updateForm = OPUserUpdateApiForm.newInstance();
		OPUserUpdateRoleApiData inputData = OPUserUpdateRoleApiData.newInstance();
		updateForm.setUpdateRoleData(inputData);
		updateForm.setUpdateTypeEnum(OPUserUpdateType.updateRole);

		inputData.setOpuserId(opId);
		inputData.setRoleIdSet(roleIdSet);
		update(opId, updateForm);
	}
	
	public void changePassword(long opId, OPUserChangePasswordApiData inputData) {
		OPUserUpdateApiForm updateForm = OPUserUpdateApiForm.newInstance();
		updateForm.setChangePasswordData(inputData);
		updateForm.setUpdateTypeEnum(OPUserUpdateType.changePassword);
		
		inputData.setOpuserId(opId);
		update(opId, updateForm);
	}
	
	private void update(long opId, OPUserUpdateApiForm updateForm) {
		OPUserDAO.getInstance().update(opId, updateForm);
	}
	
}
