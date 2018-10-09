package com.hq.chainClient.service.chainCard.apiData;

public class DelProductCardForm {
	private String id;

	public static DelProductCardForm newInstance() {
		return new DelProductCardForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
