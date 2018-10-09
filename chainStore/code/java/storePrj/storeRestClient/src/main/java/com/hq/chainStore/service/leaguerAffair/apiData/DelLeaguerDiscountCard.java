package com.hq.chainStore.service.leaguerAffair.apiData;

public class DelLeaguerDiscountCard {
	private String discountCardId;

	public static DelLeaguerDiscountCard newInstance() {
		return new DelLeaguerDiscountCard();
	}

	public String getDiscountCardId() {
		return discountCardId;
	}

	public void setDiscountCardId(String discountCardId) {
		this.discountCardId = discountCardId;
	}

}
