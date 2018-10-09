package com.hq.chainStore.service.beauticianProduct.apiData;

public class AddForemost {
	private Long productId;

	public static AddForemost newInstance() {
		return new AddForemost();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
