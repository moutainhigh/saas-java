package com.hq.chainStore.service.storeCardInfo.apiData;

public class PrdCardTypeUpdateForm {
	private String id;
	private String name;

	public static PrdCardTypeUpdateForm newInstance() {
		return new PrdCardTypeUpdateForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
