package com.hq.storeClient.service.storeConfig.apiData;

public class LeaguerOriginAddForm {
	private int id;

	private String originName;

	public static LeaguerOriginAddForm newInstance() {
		return new LeaguerOriginAddForm();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

}
