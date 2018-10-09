package com.hq.storeClient.service.storeCardInfo.apiData;

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
