package com.hq.storeMS.service.leaguerAffair.apiData;

public class DelLeaguerDiscountCard {
	private String discountCardId;

	public static DelLeaguerDiscountCard newInstance() {
		return new DelLeaguerDiscountCard();
	}
	
	public static DelLeaguerDiscountCard newInstance(String discountCardId) {
		DelLeaguerDiscountCard data = new DelLeaguerDiscountCard();
		data.discountCardId=discountCardId;
		return data;
	}

	public String getDiscountCardId() {
		return discountCardId;
	}

	public void setDiscountCardId(String discountCardId) {
		this.discountCardId = discountCardId;
	}

}
