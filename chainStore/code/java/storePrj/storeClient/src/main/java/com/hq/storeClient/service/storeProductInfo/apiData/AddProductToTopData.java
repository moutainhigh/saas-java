package com.hq.storeClient.service.storeProductInfo.apiData;

public class AddProductToTopData {
	private long storeId;
	private String productId;

	public static AddProductToTopData newInstance(){
		return new AddProductToTopData();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
