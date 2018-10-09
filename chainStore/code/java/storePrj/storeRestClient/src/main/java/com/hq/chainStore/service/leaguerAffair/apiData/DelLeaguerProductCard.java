package com.hq.chainStore.service.leaguerAffair.apiData;

public class DelLeaguerProductCard {
	private String productCardId;

	public static DelLeaguerProductCard newInstance() {
		return new DelLeaguerProductCard();
	}

	public String getProductCardId() {
		return productCardId;
	}

	public void setProductCardId(String productCardId) {
		this.productCardId = productCardId;
	}

}
