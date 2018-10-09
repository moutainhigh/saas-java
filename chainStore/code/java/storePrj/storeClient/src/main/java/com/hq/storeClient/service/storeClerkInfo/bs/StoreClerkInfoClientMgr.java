package com.hq.storeClient.service.storeClerkInfo.bs;

import com.hq.storeClient.service.storeClerkInfo.apiData.ApplyClerkInfoData;
import com.hq.storeClient.service.storeClerkInfo.apiData.StoreClerkInfoUpdateForm;
import com.hq.storeClient.service.storeClerkInfo.apiData.StoreClerkInfoUpdateType;
import com.hq.storeClient.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeClient.service.storeClerkInfo.data.StoreClerkInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkInfoClientMgr {

	public static StoreClerkInfoClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkInfoClientMgr.class);
	}

	private void update(String storeClerkId, StoreClerkInfoUpdateForm updateForm) {
		StoreClerkInfoDAO.getInstance().update(storeClerkId, updateForm);
	}

	public StoreClerkInfo get(String id) {
		return StoreClerkInfoDAO.getInstance().get(id);
	}

	public StoreClerkInfo getByStoreId(long storeId) {
		return get(getId(storeId));
	}

	public void applyClerkInfo(long storeId, ApplyClerkInfoData applyClerkInfoData) {
		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		updateForm.setUpdateType(StoreClerkInfoUpdateType.ApplyClerk.ordinal());
		updateForm.setApplyClerkInfoData(applyClerkInfoData);
		update(getId(storeId), updateForm);
	}
	
	public void applyClerkInfo(String id, ApplyClerkInfoData applyClerkInfoData) {
		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		updateForm.setUpdateType(StoreClerkInfoUpdateType.ApplyClerk.ordinal());
		updateForm.setApplyClerkInfoData(applyClerkInfoData);
		update(id, updateForm);
	}
	
	private String getId(long storeId) {
		return StoreClerkInfo.getIdByStoreId(storeId);
	}

}
