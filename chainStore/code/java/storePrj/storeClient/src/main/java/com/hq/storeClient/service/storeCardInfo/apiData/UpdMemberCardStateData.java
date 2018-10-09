package com.hq.storeClient.service.storeCardInfo.apiData;

public class UpdMemberCardStateData {

	private String id;
	
	private long storeId;
	
	private int state;
	
	public static UpdMemberCardStateData newInstance(){
		return new UpdMemberCardStateData();
	}
	
	public static UpdMemberCardStateData newInstance(String idP, long storeIdP, int stateP){
		UpdMemberCardStateData data = new UpdMemberCardStateData();
		data.id = idP;
		data.storeId = storeIdP;
		data.state = stateP;
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
