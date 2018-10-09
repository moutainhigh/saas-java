package com.hq.storeClient.service.storeConfig.apiData;

public class CancelAppointRemoveForm {
	private int id;

	public static CancelAppointRemoveForm newInstance() {
		return new CancelAppointRemoveForm();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
