package com.hq.chainClient.service.chainCard.apiData;

public class DelMembershipCard {
	private String id;

	public static DelMembershipCard newInstance() {
		return new DelMembershipCard();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
