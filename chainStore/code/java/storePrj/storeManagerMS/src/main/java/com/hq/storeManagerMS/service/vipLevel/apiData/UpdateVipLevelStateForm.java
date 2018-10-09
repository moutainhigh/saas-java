package com.hq.storeManagerMS.service.vipLevel.apiData;

public class UpdateVipLevelStateForm {

	private long id;
	
	// 等级状态 VipLevelStateEnum
	private int state;

	public static UpdateVipLevelStateForm newInstance() {
		return new UpdateVipLevelStateForm();

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
