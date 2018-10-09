package com.hq.storeClient.service.storeCardInfo.apiData;

public class AddPrdCardTop {
	private String id;

	public static AddPrdCardTop newInstance() {
		AddPrdCardTop data = new AddPrdCardTop();
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
