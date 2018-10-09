package com.hq.storeMS.service.beauticianProduct.apiData;

public class RemoveForemost {
	private Long productId;

	public static RemoveForemost newInstance() {
		return new RemoveForemost();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
