package com.hq.storeClient.service.storeCardInfo.apiData;

public class PrdCardTypeAddForm {
	private long index;
	private String name;

	public static PrdCardTypeAddForm newInstance() {
		return new PrdCardTypeAddForm();
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
