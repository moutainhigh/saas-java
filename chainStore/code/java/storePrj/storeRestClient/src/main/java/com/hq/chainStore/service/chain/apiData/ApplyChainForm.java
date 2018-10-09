package com.hq.chainStore.service.chain.apiData;

public class ApplyChainForm {
	private long chainId;
	private long storeId;

	public static ApplyChainForm newInstance() {
		return new ApplyChainForm();
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

}
