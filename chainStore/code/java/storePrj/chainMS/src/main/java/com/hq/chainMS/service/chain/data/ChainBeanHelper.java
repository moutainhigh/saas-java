package com.hq.chainMS.service.chain.data;

import java.util.Set;

import com.zenmind.common.hotSwap.HotSwap;

public class ChainBeanHelper {
	public static ChainBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ChainBeanHelper.class);
	}
	
	public boolean applyChain(Chain chain, long storeId) {
		if(chain==null) {
			return false;
		}
		Set<Long> applyStoreIds = chain.takeApplyStoreIds();
		if(applyStoreIds.contains(storeId)) {
			return false;
		}
		Set<Long> chainStoreIds = chain.takeChainStoreIds();
		if(chainStoreIds.contains(storeId)) {
			return false;
		}
		chain.addApplyStoreId(storeId);
		return true;
	}
	
	public boolean handleApplyChain(Chain chain, long storeId, int status) {
		if(chain==null) {
			return false;
		}
		Set<Long> applyStoreIds = chain.takeApplyStoreIds();
		if(!applyStoreIds.contains(storeId)) {
			return false;
		}
		
		if(status == ApplyStatusEnum.PASS.ordinal()) {
			chain.addChainStoreId(storeId);
			chain.removeApplyStoreId(storeId);
			return true;
		}
		
		if(status == ApplyStatusEnum.UNPASS.ordinal()) {
			chain.removeApplyStoreId(storeId);
			return true;
		}
		return false;
	}
}
