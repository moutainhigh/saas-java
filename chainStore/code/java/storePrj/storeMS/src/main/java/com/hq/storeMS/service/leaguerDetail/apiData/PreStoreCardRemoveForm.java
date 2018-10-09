package com.hq.storeMS.service.leaguerDetail.apiData;

public class PreStoreCardRemoveForm {
	// 客户预存卡ID
	private String id;

	public static PreStoreCardRemoveForm newInstance() {
		return new PreStoreCardRemoveForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
