package com.hq.chainStore.service.storeGoods.apiData;

public class GoodsPullForm {
	private long chainId;
	private String id;

	public static GoodsPullForm newInstance() {
		return new GoodsPullForm();
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
