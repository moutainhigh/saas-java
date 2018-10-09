package com.hq.chainStore.service.storeBeauticianInfo.apiData;

import com.hq.chainStore.service.storeBeauticianInfo.data.BeauticianState;

public class UpdateBeauticianInfoData {

	private long buserId;
	private int state;
	private String descript;
	
	public static UpdateBeauticianInfoData newInstance(){
		return new UpdateBeauticianInfoData();
	}
	
	public long getBuserId() {
		return buserId;
	}



	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}



	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public BeauticianState getStateEnum() {
		return BeauticianState.valueOf(this.state);
	}

	public void setStateEnum(BeauticianState state) {
		this.state = state.ordinal();
	}
	
	
	
}
