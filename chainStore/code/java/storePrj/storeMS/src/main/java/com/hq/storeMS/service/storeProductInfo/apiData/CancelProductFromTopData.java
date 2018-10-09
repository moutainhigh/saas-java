package com.hq.storeMS.service.storeProductInfo.apiData;

public class CancelProductFromTopData {
	private long storeId;
	private String productId;

	public static CancelProductFromTopData newInstance(){
		return new CancelProductFromTopData();
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
