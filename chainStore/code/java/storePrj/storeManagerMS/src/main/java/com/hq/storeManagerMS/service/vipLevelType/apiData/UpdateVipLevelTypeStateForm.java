package com.hq.storeManagerMS.service.vipLevelType.apiData;

public class UpdateVipLevelTypeStateForm {

	private long id;
	
	// 等级状态 VipLevelTypeStateEnum
	private int state;

	public static UpdateVipLevelTypeStateForm newInstance() {
		return new UpdateVipLevelTypeStateForm();

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	
}
