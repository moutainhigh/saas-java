package com.hq.storeClient.service.dynamic.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.dynamic.apiData.DynamicAddForm;
import com.hq.storeClient.service.dynamic.apiData.DynamicQueryForm;
import com.hq.storeClient.service.dynamic.apiData.DynamicQueryFormForCuser;
import com.hq.storeClient.service.dynamic.apiData.DynamicUpdateApiForm;
import com.hq.storeClient.service.dynamic.apiData.DynamicUpdateInfoForm;
import com.hq.storeClient.service.dynamic.apiData.DynamicUpdateStatusForm;
import com.hq.storeClient.service.dynamic.apiData.DynamicUpdateType;
import com.hq.storeClient.service.dynamic.data.Dynamic;
import com.hq.storeClient.service.dynamic.data.DynamicDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class DynamicClientMgr {

	public static DynamicClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DynamicClientMgr.class);
	}
	
	public PageResp<Dynamic> findPage(DynamicQueryForm queryForm) {
		final String findPath = "findPage";
		return DynamicDAO.getInstance().findPage(findPath, queryForm);
	}
	
	public PageResp<Dynamic> findPageForCuser(DynamicQueryFormForCuser queryForm) {
		final String findPath = "findPageForCuser";
		return DynamicDAO.getInstance().findPageForCuser(findPath, queryForm);
	}
	
	public Dynamic get(long dynamicId) {
		return DynamicDAO.getInstance().get(dynamicId);
	}
	
	public Dynamic update(long dynamicId, DynamicUpdateApiForm updateForm) {
		return DynamicDAO.getInstance().updateWithResp(dynamicId, updateForm);
	}
	
	public Dynamic add(DynamicAddForm addForm) {
		return DynamicDAO.getInstance().add(addForm);
	}
	
	public Dynamic updateDynamicInfo(long dynamicId, DynamicUpdateInfoForm inputForm) {
		DynamicUpdateApiForm updateForm = DynamicUpdateApiForm.newInstance();
		updateForm.setDynamicUpdateType(DynamicUpdateType.UpdateDynamicInfo);
		updateForm.setUpdateInfoForm(inputForm);
		return update(dynamicId, updateForm);
	}
	
	public Dynamic updateDynamicStatus(long dynamicId, DynamicUpdateStatusForm inputForm) {
		DynamicUpdateApiForm updateForm = DynamicUpdateApiForm.newInstance();
		updateForm.setDynamicUpdateType(DynamicUpdateType.UpdateDynamicStatus);
		updateForm.setUpdateStatusForm(inputForm);
		return update(dynamicId, updateForm);
	}
}
