package com.hq.customerRestClient.service.storeLeaguerInfo.data;

public class LeaguerMemberCard {
	
	private String cardId;//会员卡类型id 对应MembershipCard id
	
	private String number;//会员卡编号
	
	private float balance;//账户余额
	
	private int limitTime;//有效期  eg：365天/1年
	
	private int limitUnit;//有效期时间的单位 对应LimitUnitEnum
	
	private long endTime;//到期时间
	
	private int state;//会员卡状态 对应LeaguerCardEnum
	
	private long settingTime;//设置会员卡的时间 每次设置都会刷新
	
	private long createdTime;//创建时间
	
	public static LeaguerMemberCard newInstance(){
		LeaguerMemberCard memberCard =  new LeaguerMemberCard();
		
		long curTime = System.currentTimeMillis();
		memberCard.createdTime = curTime;
		return memberCard;
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

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
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

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getSettingTime() {
		return settingTime;
	}

	public void setSettingTime(long settingTime) {
		this.settingTime = settingTime;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	
}
