package com.hq.storeClient.service.chain.bs;

import com.hq.storeClient.common.exception.AppNameAuthUtils;
import com.hq.storeClient.common.exception.AppNameEnum;
import com.hq.storeClient.service.chain.data.Chain;
import com.hq.storeClient.service.chain.data.ChainDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainClientMgr {

	public static ChainClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainClientMgr.class);
	}
	
	public Chain get(long chainId) {
		AppNameAuthUtils.getInstance().checkMS(AppNameEnum.ChainMS);
		return ChainDAO.getInstance().get(chainId);
	}
}
