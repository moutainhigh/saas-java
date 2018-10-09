package com.hq.storeMS.service.storeBeauticianInfo.apiData;

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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	
	
	
}
