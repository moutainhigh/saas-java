package com.hq.chainStore.service.storeLeaguerInfo.apiData;

public class RechargeMemberCardForm {
	
	private String leaguerId;//会员id
	
	private String cardId;//会员卡类型id 对应MembershipCard id
	
	private float amount;//充值金额
	
	public static RechargeMemberCardForm newInstance() {
		RechargeMemberCardForm rechargeMemberCardForm = new RechargeMemberCardForm();
		return rechargeMemberCardForm;
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
