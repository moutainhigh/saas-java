package com.hq.storeMS.service.leaguerCard.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LeaguerCard {

	private String leaguerId;
	private String leaguerName;
	private long leaguerPhone;
	private long lastConsumeTime;
	private String cardId;
	private String cardName;
	private String cardTypeId;
	private long cardEndTime;
	
	public static LeaguerCard newInstance(){
		return new LeaguerCard();
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public String getLeaguerName() {
		return leaguerName;
	}

	public void setLeaguerName(String leaguerName) {
		this.leaguerName = leaguerName;
	}

	public long getLeaguerPhone() {
		return leaguerPhone;
	}

	public void setLeaguerPhone(long leaguerPhone) {
		this.leaguerPhone = leaguerPhone;
	}

	public long getLastConsumeTime() {
		return lastConsumeTime;
	}

	public void setLastConsumeTime(long lastConsumeTime) {
		this.lastConsumeTime = lastConsumeTime;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(String cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public long getCardEndTime() {
		return cardEndTime;
	}

	public void setCardEndTime(long cardEndTime) {
		this.cardEndTime = cardEndTime;
	}

}
