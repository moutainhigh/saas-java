package com.hq.storeMS.service.storeLeaguerInfo.apiData;

public class LeaguerLabelRemoveForm {
	private String id;

	public static LeaguerLabelRemoveForm newInstance() {
		return new LeaguerLabelRemoveForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
