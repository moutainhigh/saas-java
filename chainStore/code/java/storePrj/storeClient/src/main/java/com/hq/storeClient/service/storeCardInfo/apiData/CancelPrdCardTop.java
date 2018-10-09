package com.hq.storeClient.service.storeCardInfo.apiData;

public class CancelPrdCardTop {
	private String id;

	public static CancelPrdCardTop newInstance() {
		CancelPrdCardTop data = new CancelPrdCardTop();
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
