package com.hq.chainMS.service.storeChain.bs;

import java.util.List;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.storeClient.service.storeChain.apiData.StoreChainUpdateStatusForm;
import com.hq.storeClient.service.storeChain.bs.StoreChainClientMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreChainDataHolder {

	public static StoreChainDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreChainDataHolder.class);
	}

	public void batchUpdateState(String id, List<StoreChainUpdateStatusForm> updateStatusForms) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreChainClientMgr.getInstance().batchUpdateState(id, updateStatusForms);
		AppIdThreadLocal.getInstance().set(null);
	}
	
}
