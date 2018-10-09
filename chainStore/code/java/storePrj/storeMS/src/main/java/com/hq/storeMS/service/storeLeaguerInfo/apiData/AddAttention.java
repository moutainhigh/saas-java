package com.hq.storeMS.service.storeLeaguerInfo.apiData;


public class AddAttention {
	private String leaguerId;

	public static AddAttention newInstance() {
		return new AddAttention();
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

}
