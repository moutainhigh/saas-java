package com.hq.storeMS.service.leaguerAffair.apiData;

public class DelLeaguerProductCard {
	private String productCardId;

	public static DelLeaguerProductCard newInstance() {
		return new DelLeaguerProductCard();
	}
	
	public static DelLeaguerProductCard newInstance(String productCardId) {
		DelLeaguerProductCard data = new DelLeaguerProductCard();
		data.productCardId=productCardId;
		return data;
	}

	public String getProductCardId() {
		return productCardId;
	}

	public void setProductCardId(String productCardId) {
		this.productCardId = productCardId;
	}

}
