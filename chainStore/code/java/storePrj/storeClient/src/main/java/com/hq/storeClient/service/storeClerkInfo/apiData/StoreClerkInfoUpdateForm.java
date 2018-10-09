package com.hq.storeClient.service.storeClerkInfo.apiData;

public class StoreClerkInfoUpdateForm {
	private int updateType;

	private ApplyClerkInfoData applyClerkInfoData;
	
	public static StoreClerkInfoUpdateForm newInstance(){
		return new StoreClerkInfoUpdateForm();
	}
	
	public StoreClerkInfoUpdateType getStoreClerkUpdateType() {
		return StoreClerkInfoUpdateType.valueOf(updateType);
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public ApplyClerkInfoData getApplyClerkInfoData() {
		return applyClerkInfoData;
	}

	public void setApplyClerkInfoData(ApplyClerkInfoData applyClerkInfoData) {
		this.applyClerkInfoData = applyClerkInfoData;
	}
	
}
