package com.hq.chainClient.service.chainCard.apiData;

public class UpdMemberCardStateData {
	private String id;
	private int state;
	
	public static UpdMemberCardStateData newInstance(){
		return new UpdMemberCardStateData();
	}
	
	public static UpdMemberCardStateData newInstance(String idP, int stateP){
		UpdMemberCardStateData data = new UpdMemberCardStateData();
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
