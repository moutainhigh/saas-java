package com.hq.storeManagerMS.service.vipLevelType.apiData;

public class RemoveVipLevelTypeForm {

	private long id;

	public static RemoveVipLevelTypeForm newInstance() {
		RemoveVipLevelTypeForm data = new RemoveVipLevelTypeForm();
		return data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
