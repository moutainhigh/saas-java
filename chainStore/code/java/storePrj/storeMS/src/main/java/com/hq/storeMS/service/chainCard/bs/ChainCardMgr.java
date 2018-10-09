package com.hq.storeMS.service.chainCard.bs;

import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.hq.chainClient.service.chainCard.data.MembershipCardDetail;
import com.hq.chainClient.service.chainCard.data.ProductCardDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainCardMgr {

	public static ChainCardMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardMgr.class);
	}

	public ChainCard getChainCard(long chainId) {
		return ChainCardDataHolder.getInstance().get(chainId);
	}

	public ProductCardDetail getProductCardDetail(String productCardId, long chainId) {
		return ChainCardDataHolder.getInstance().getProductCardDetail(productCardId, chainId);
	}

	public MembershipCardDetail getMembershipCardDetail(String memberCardId, long chainId) {
		return ChainCardDataHolder.getInstance().getMembershipCardDetail(memberCardId, chainId);
	}
}
