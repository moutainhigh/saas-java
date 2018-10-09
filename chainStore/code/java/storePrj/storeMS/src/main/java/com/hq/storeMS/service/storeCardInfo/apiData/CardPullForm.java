package com.hq.storeMS.service.storeCardInfo.apiData;

public class CardPullForm {
	private long chainId;
	private String id;

	public static CardPullForm newInstance() {
		return new CardPullForm();
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
