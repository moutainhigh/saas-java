package com.hq.chainStore.service.leaguerAffair.apiData;

public class DelLeaguerMembershipCard {
	private String membershipCardId;

	public static DelLeaguerMembershipCard newInstance() {
		return new DelLeaguerMembershipCard();
	}

	public String getMembershipCardId() {
		return membershipCardId;
	}

	public void setMembershipCardId(String membershipCardId) {
		this.membershipCardId = membershipCardId;
	}

}
