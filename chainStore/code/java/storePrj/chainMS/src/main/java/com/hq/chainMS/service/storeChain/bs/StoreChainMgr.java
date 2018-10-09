package com.hq.chainMS.service.storeChain.bs;

import java.util.List;

import com.hq.storeClient.service.storeChain.apiData.StoreChainUpdateStatusForm;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreChainMgr {

	public static StoreChainMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreChainMgr.class);
	}
	
	public void batchUpdateState(String id, List<StoreChainUpdateStatusForm> updateStatusForms) {
		StoreChainDataHolder.getInstance().batchUpdateState(id, updateStatusForms);
	}
}
