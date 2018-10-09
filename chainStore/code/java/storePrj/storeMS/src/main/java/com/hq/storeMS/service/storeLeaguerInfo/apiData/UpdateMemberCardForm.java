package com.hq.storeMS.service.storeLeaguerInfo.apiData;

public class UpdateMemberCardForm {
	
	private String leaguerId;//会员id
	
	private String cardId;//会员卡类型id 对应MembershipCard id
	
	private String number;//会员卡编号
	
	private int limitTime;//有效期  eg：365天/1年
	
	private int limitUnit;//有效期时间的单位 对应LimitUnitEnum
	
	public static UpdateMemberCardForm newInstance() {
		UpdateMemberCardForm addMemberCard = new UpdateMemberCardForm();
		return addMemberCard;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}

	public int getLimitUnit() {
		return limitUnit;
	}

	public void setLimitUnit(int limitUnit) {
		this.limitUnit = limitUnit;
	}

}
