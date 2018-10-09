package com.hq.chainStore.service.storeCardInfo.apiData;

public class DelDiscountCard {
	private String id;// storeId_index

	public static DelDiscountCard newInstance() {
		return new DelDiscountCard();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
