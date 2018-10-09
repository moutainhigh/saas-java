package com.hq.chainStore.service.cardOrder.data;

import com.zenmind.common.hotSwap.HotSwap;

public class CardOrderSynDataHolder {

	public static CardOrderSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(CardOrderSynDataHolder.class);
	}

	public CardOrder getData(String ownerId, String targetId) {
		return CardOrderDAO.getInstance().get(targetId);
	}
}
