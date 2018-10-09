package com.hq.chainStore.service.storeMaterialInfo.bs;

import com.hq.chainStore.service.storeMaterialInfo.apiData.StoreMaterialInfoUpdateForm;
import com.hq.chainStore.service.storeMaterialInfo.data.StoreMaterialInfo;
import com.hq.chainStore.service.storeMaterialInfo.data.StoreMaterialInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMaterialInfoMgr {

	public static StoreMaterialInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreMaterialInfoMgr.class);
	}

	public StoreMaterialInfo get(long storeId) {
		return StoreMaterialInfoDAO.getInstance().get(storeId);
	}
	
	public void update(long storeId, StoreMaterialInfoUpdateForm updateForm) {
		StoreMaterialInfoDAO.getInstance().update(storeId, updateForm);
	}
	
}
