package com.hq.chainMS.service.chainCard.apiData;

public class PrdCardTypeRemoveForm {
	private String id;

	public static PrdCardTypeRemoveForm newInstance() {
		return new PrdCardTypeRemoveForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
