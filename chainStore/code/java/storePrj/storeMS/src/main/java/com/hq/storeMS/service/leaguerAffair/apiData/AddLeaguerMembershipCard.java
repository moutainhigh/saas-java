package com.hq.storeMS.service.leaguerAffair.apiData;

public class AddLeaguerMembershipCard {
	private String membershipCardId;

	public static AddLeaguerMembershipCard newInstance() {
		return new AddLeaguerMembershipCard();
	}

	public String getMembershipCardId() {
		return membershipCardId;
	}

	public void setMembershipCardId(String membershipCardId) {
		this.membershipCardId = membershipCardId;
	}

}
