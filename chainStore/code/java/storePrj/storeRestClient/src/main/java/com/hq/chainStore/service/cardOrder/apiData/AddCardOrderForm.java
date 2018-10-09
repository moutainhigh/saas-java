package com.hq.chainStore.service.cardOrder.apiData;


public class AddCardOrderForm {
	private long storeId;
	//顾客信息
	private String leaguerId;
	//卡片信息
	private String cardId;//卡片ID
	private float price;//价格
	private int cardType;//卡片类型

	public static AddCardOrderForm newInstance() {
		return new AddCardOrderForm();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}
}
