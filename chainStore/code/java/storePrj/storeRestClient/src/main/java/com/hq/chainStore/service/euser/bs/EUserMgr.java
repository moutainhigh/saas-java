package com.hq.chainStore.service.euser.bs;

import java.util.List;

import com.hq.chainStore.service.euser.apiData.EUserAddForm;
import com.hq.chainStore.service.euser.apiData.EUserUpdateCountData;
import com.hq.chainStore.service.euser.apiData.EUserUpdateForm;
import com.hq.chainStore.service.euser.apiData.EUserUpdateType;
import com.hq.chainStore.service.euser.data.EUser;
import com.hq.chainStore.service.euser.data.EUserDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class EUserMgr {

	public static EUserMgr getInstance(){
		return HotSwap.getInstance().getSingleton(EUserMgr.class);
	}
	

	public EUser createEUser(EUserAddForm euserAddForm) {
		EUser euser = EUserDAO.getInstance().add(euserAddForm);
		return euser;
	}
	
	public void updateCount(long euserId,EUserUpdateCountData updateCountData) {
		EUserUpdateForm updateForm = EUserUpdateForm.newInstance();
		updateForm.setUpdateType(EUserUpdateType.updateCount.ordinal());
		updateForm.setUpdateCountData(updateCountData);
		update(euserId,updateForm);
	}
	
	public void update(long buserId,EUserUpdateForm updateForm) {
		EUserDAO.getInstance().update(buserId, updateForm);
	}

	public EUser findByPhone(long phone) {
		final String uriPath = "findByPhone";
		ReqMap reqMap = ReqMap.newInstance().add("phone", phone);
		EUser euser = EUserDAO.getInstance().findOneWithReqParam(uriPath, reqMap);
		return euser;
	}
	
	public List<EUser> getList(Integer pageItemCount,Integer pageNo) {
		final String uriPath = "findList";
		List<EUser> eusers = EUserDAO.getInstance().findList(uriPath, pageItemCount, pageNo);
		return eusers;
	}
	
	public EUser get(long id) {
		EUser euser = EUserDAO.getInstance().get(id);
		return euser;
	}
	
}
