package com.hq.storeManagerRestClient.service.vipLevel.bs;

import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.service.vipLevel.apiData.AddVipLevelForm;
import com.hq.storeManagerRestClient.service.vipLevel.apiData.QueryVipLevelForm;
import com.hq.storeManagerRestClient.service.vipLevel.apiData.UpdateVipLevelForm;
import com.hq.storeManagerRestClient.service.vipLevel.apiData.UpdateVipLevelStateForm;
import com.hq.storeManagerRestClient.service.vipLevel.apiData.VipLevelUpdateForm;
import com.hq.storeManagerRestClient.service.vipLevel.apiData.VipLevelUpdateType;
import com.hq.storeManagerRestClient.service.vipLevel.data.VipLevel;
import com.hq.storeManagerRestClient.service.vipLevel.data.VipLevelDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelClientMgr {

	public static VipLevelClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelClientMgr.class);
	}

	public VipLevel get(long id) {
		return VipLevelDAO.getInstance().get(id);
	}

	public VipLevel add(AddVipLevelForm addFrom) {
		return VipLevelDAO.getInstance().add(addFrom);
	}

	public void updateVipLevel(long id, UpdateVipLevelForm updateVipLevelForm) {
		VipLevelUpdateForm updateForm = new VipLevelUpdateForm();
		updateForm.setUpdateType(VipLevelUpdateType.UpdateVipLevel.ordinal());
		updateForm.setUpdateVipLevelForm(updateVipLevelForm);
		update(id, updateForm);
	}

	public void updateState(long id, UpdateVipLevelStateForm updateStateForm) {
		VipLevelUpdateForm updateForm = new VipLevelUpdateForm();
		updateForm.setUpdateType(VipLevelUpdateType.UpdateVipLevelState.ordinal());
		updateForm.setUpdateVipLevelStateForm(updateStateForm);
		update(id, updateForm);
	}

	public void update(long id, VipLevelUpdateForm updateForm) {
		VipLevelDAO.getInstance().update(id, updateForm);
	}

	public PageResp<VipLevel> findPage(QueryVipLevelForm queryForm) {
		String findPath = "findPage";
		return VipLevelDAO.getInstance().findPage(findPath, queryForm);
	}

}
