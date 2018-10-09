package com.hq.chainStore.service.storeConfig.data.leaguer;

public class LeaguerOriginConfig {
	private int id;

	private String originName;

	public static LeaguerOriginConfig newInstance() {
		LeaguerOriginConfig data = new LeaguerOriginConfig();
		return data;
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
