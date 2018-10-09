package com.hq.chainStore.service.storeBeauticianInfo.apiData;

public class RemoveBeauticianInfoData {

	private long buserId;
	
	public static RemoveBeauticianInfoData newInstance(){
		return new RemoveBeauticianInfoData();
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}
	
}
