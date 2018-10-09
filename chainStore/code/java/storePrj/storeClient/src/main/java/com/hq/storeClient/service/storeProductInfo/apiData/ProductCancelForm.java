package com.hq.storeClient.service.storeProductInfo.apiData;

public class ProductCancelForm {
	private long chainId;
	private String id;

	public static ProductCancelForm newInstance() {
		return new ProductCancelForm();
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
