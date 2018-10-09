package com.hq.chainClient.service.chainProduct.apiData;

public class UpdateProductStateData {
	private String id;
	private int state;

	public static UpdateProductStateData newInstance() {
		return new UpdateProductStateData();
	}

	public static UpdateProductStateData newInstance(String idP, int stateP) {
		UpdateProductStateData data = newInstance();
		data.id = idP;
		data.state = stateP;
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
