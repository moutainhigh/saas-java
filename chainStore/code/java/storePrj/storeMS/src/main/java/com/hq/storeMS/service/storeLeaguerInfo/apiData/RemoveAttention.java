package com.hq.storeMS.service.storeLeaguerInfo.apiData;


public class RemoveAttention {
	private String leaguerId;

	public static RemoveAttention newInstance() {
		return new RemoveAttention();
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

}
