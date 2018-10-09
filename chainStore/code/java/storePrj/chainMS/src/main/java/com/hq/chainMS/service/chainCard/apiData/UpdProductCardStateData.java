package com.hq.chainMS.service.chainCard.apiData;

public class UpdProductCardStateData {
	private String id;
	private int state;

	public static UpdProductCardStateData newInstance() {
		return new UpdProductCardStateData();
	}

	public static UpdProductCardStateData newInstance(String idP, int stateP) {
		UpdProductCardStateData data = new UpdProductCardStateData();
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
