package com.hq.chainStore.service.storeGoods.apiData;

public class GoodsCancelForm {
	private long chainId;
	private String id;

	public static GoodsCancelForm newInstance() {
		return new GoodsCancelForm();
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
