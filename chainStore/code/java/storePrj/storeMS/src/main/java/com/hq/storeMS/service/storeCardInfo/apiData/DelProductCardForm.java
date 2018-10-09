package com.hq.storeMS.service.storeCardInfo.apiData;

public class DelProductCardForm {
	private String id;// storeId_index

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
