package com.hq.chainStore.service.leaguerAffair.apiData;

public class AddLeaguerProductCard {
	private String productCardId;

	public static AddLeaguerProductCard newInstance() {
		return new AddLeaguerProductCard();
	}

	public String getProductCardId() {
		return productCardId;
	}

	public void setProductCardId(String productCardId) {
		this.productCardId = productCardId;
	}

}
