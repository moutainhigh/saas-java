package com.hq.storeClient.service.storeLeaguerInfo.apiData;

public class PurchaseProductCardForm {
	
	private String leaguerId;//会员id
	
	private String cardId;//次卡类型id 对应ProductCard id
	
	public static PurchaseProductCardForm newInstance() {
		PurchaseProductCardForm purchaseProductCardForm = new PurchaseProductCardForm();
		return purchaseProductCardForm;
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

}
