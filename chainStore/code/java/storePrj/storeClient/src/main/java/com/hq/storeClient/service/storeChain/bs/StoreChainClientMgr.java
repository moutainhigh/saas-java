package com.hq.storeClient.service.storeChain.bs;

import java.util.List;

import com.hq.storeClient.service.storeChain.apiData.StoreChainUpdateApiForm;
import com.hq.storeClient.service.storeChain.apiData.StoreChainUpdateStatusForm;
import com.hq.storeClient.service.storeChain.apiData.StoreChainUpdateType;
import com.hq.storeClient.service.storeChain.data.StoreChainDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreChainClientMgr {

	public static StoreChainClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreChainClientMgr.class);
	}

	public void batchUpdateState(String id, List<StoreChainUpdateStatusForm> inputForm) {
		StoreChainUpdateApiForm updateForm = StoreChainUpdateApiForm.newInstance();
		updateForm.setStoreChainUpdateStatusForms(inputForm);
		updateForm.setUpdateType(StoreChainUpdateType.BatchUpdateState.ordinal());
		StoreChainDAO.getInstance().update(id, updateForm);
	}

}
