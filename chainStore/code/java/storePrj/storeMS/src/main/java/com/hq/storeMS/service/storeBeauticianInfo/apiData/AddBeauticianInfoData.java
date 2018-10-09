package com.hq.storeMS.service.storeBeauticianInfo.apiData;

public class AddBeauticianInfoData {

	private long buserId;
	
	public static AddBeauticianInfoData newInstance(){
		return new AddBeauticianInfoData();
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}
	
}
