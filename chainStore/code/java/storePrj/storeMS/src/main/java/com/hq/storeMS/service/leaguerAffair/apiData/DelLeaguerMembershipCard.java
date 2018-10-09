package com.hq.storeMS.service.leaguerAffair.apiData;

public class DelLeaguerMembershipCard {
	private String membershipCardId;

	public static DelLeaguerMembershipCard newInstance() {
		return new DelLeaguerMembershipCard();
	}
	
	public static DelLeaguerMembershipCard newInstance(String membershipCardId) {
		DelLeaguerMembershipCard data = new DelLeaguerMembershipCard();
		data.membershipCardId = membershipCardId;
		return data;
	}

	public String getMembershipCardId() {
		return membershipCardId;
	}

	public void setMembershipCardId(String membershipCardId) {
		this.membershipCardId = membershipCardId;
	}

}
