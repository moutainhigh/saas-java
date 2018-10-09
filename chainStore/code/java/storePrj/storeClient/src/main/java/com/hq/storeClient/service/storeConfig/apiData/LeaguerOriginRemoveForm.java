package com.hq.storeClient.service.storeConfig.apiData;

public class LeaguerOriginRemoveForm {
	private int id;

	public static LeaguerOriginRemoveForm newInstance() {
		return new LeaguerOriginRemoveForm();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
