package com.hq.chainMS.service.chainClerk.bs;

import com.hq.chainMS.service.chain.bs.ChainDataHolder;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chainClerk.data.ChainClerk;
import com.hq.chainMS.service.chainClerk.data.ClerkInfo;
import com.hq.chainMS.service.chainUser.bs.ChainUserMgr;
import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainClerkMgr {

	public static ChainClerkMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainClerkMgr.class);
	}

	public void addWithId(ChainClerk target) {
		ChainClerkDataHolder.getInstance().addWithId(target);
	}

	public void delete(ChainClerk target) {
		ChainClerkDataHolder.getInstance().delete(target);
	}

	public ChainClerk get(long chainId) {
		ChainClerk chainClerk = ChainClerkDataHolder.getInstance().get(chainId);
		if (chainClerk == null) {
			initChainClerk(chainId);
			chainClerk = ChainClerkDataHolder.getInstance().get(chainId);
		}
		return chainClerk;
	}

	public void initChainClerk(long chainId) {
		Chain chain = ChainDataHolder.getInstance().get(chainId);
		if (chain != null) {
			ChainUser user = ChainUserMgr.getInstance().get(chain.getBossId());
			ChainClerk chainClerk = ChainClerk.newInstance(chainId, chain.getBossId());
			ClerkInfo clerk = chainClerk.getClerkInfoMap().get(user.getId());
			clerk.setName(user.getName());
			clerk.setPhone(user.getPhone());
			ChainClerkDataHolder.getInstance().addWithId(chainClerk);
		}
	}

	public void update(ChainClerk chainClerk) {
		ChainClerkDataHolder.getInstance().update(chainClerk);
	}
}
