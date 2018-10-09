package com.hq.chainStore.service.euser.apiData;


public class EUserUpdateForm {
	
	private int updateType;
	
	private EUserUpdateCountData updateCountData;

	public static EUserUpdateForm newInstance(){
		return new EUserUpdateForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public EUserUpdateCountData getUpdateCountData() {
		return updateCountData;
	}

	public void setUpdateCountData(EUserUpdateCountData updateCountData) {
		this.updateCountData = updateCountData;
	}

	
}
