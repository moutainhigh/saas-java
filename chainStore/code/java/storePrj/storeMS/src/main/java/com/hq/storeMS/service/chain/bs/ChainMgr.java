package com.hq.storeMS.service.chain.bs;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chain.apiData.ApplyChainForm;
import com.hq.chainClient.service.chain.apiData.ChainQueryForm;
import com.hq.chainClient.service.chain.apiData.RelieveStoreForm;
import com.hq.chainClient.service.chain.data.Chain;
import com.hq.chainClient.service.chain.data.OperateFromEnum;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainMgr {

	public static ChainMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainMgr.class);
	}

	public Chain getChain(long chainId) {
		return ChainDataHolder.getInstance().get(chainId);
	}
	
	public Chain getChainByStoreId(long storeId) {
		Store store = StoreMgr.getInstance().getSimple(storeId);
		if(store == null || store.takeChainId() == null) {
			return null;
		}
		return ChainDataHolder.getInstance().get(store.takeChainId());
	}
	
	public Chain findByNumber(String number) {
		return ChainDataHolder.getInstance().findByNumber(number);
	}

	public PageResp<Chain> findChainByCond(ChainQueryForm queryForm) {
		return ChainDataHolder.getInstance().findChainByCond(queryForm);
	}
	
	public void applyChain(ApplyChainForm inputForm) {
		ChainDataHolder.getInstance().applyChain(inputForm.getChainId(), inputForm);
	}
	
	public void relieveStore(RelieveStoreForm inputForm) {
		inputForm.setOperateFrom(OperateFromEnum.storeMS.ordinal());
		ChainDataHolder.getInstance().relieveStore(inputForm.getChainId(), inputForm);
	}
}
