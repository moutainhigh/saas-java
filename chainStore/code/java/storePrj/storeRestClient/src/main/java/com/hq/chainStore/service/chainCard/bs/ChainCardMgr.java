package com.hq.chainStore.service.chainCard.bs;

import com.hq.chainStore.service.chainCard.data.ChainCard;
import com.hq.chainStore.service.chainCard.data.ChainCardDAO;
import com.hq.chainStore.service.chainCard.data.MembershipCardDetail;
import com.hq.chainStore.service.chainCard.data.ProductCardDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainCardMgr {

	public static ChainCardMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainCardMgr.class);
	}
	
	public ChainCard get(long chainId) {
		return ChainCardDAO.getInstance().get(chainId);
	}
	
	public ProductCardDetail findProductCardDetail(String productCardId, long chainId) {
		return ChainCardDAO.getInstance().findProductCardDetail(productCardId, chainId);
	}

	public MembershipCardDetail findMemberCardDetail(String memberCardId, long chainId) {
		return ChainCardDAO.getInstance().findMemberCardDetail(memberCardId, chainId);
	}
	
}
