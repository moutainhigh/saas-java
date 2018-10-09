package com.hq.storeClient.service.storeLeaguerInfo.apiData;

public class LeaguerLabelAddForm {
	private long index;
	private String name;

	public static LeaguerLabelAddForm newInstance() {
		return new LeaguerLabelAddForm();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

}
