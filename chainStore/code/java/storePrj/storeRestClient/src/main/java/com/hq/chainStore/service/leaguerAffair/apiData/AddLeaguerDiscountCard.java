package com.hq.chainStore.service.leaguerAffair.apiData;

public class AddLeaguerDiscountCard {
	private String discountCardId;

	public static AddLeaguerDiscountCard newInstance() {
		return new AddLeaguerDiscountCard();
	}

	public String getDiscountCardId() {
		return discountCardId;
	}

	public void setDiscountCardId(String discountCardId) {
		this.discountCardId = discountCardId;
	}

}
