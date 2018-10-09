package com.hq.storeMS.service.storeProductInfo.apiData;

public class ProductPullForm {
	private long chainId;
	private String id;

	public static ProductPullForm newInstance() {
		return new ProductPullForm();
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
