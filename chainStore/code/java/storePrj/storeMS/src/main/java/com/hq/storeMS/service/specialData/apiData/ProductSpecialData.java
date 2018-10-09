package com.hq.storeMS.service.specialData.apiData;


public class ProductSpecialData {
	private Long productId;

	public static ProductSpecialData newInstance() {
		return new ProductSpecialData();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}


}
