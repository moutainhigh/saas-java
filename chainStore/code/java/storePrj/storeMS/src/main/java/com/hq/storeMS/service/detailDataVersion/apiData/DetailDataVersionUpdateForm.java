package com.hq.storeMS.service.detailDataVersion.apiData;

public class DetailDataVersionUpdateForm {

	private int updateType;
	
	private long storeId;

	private AddBeauticianInfoData addBeauticianInfoData;
	
	private UpdateBeauticianInfoData updateBeauticianInfoData;
	
	private RemoveBeauticianInfoData removeBeauticianInfoData;
	
	public static DetailDataVersionUpdateForm newInstance(){
		return new DetailDataVersionUpdateForm();
	}

	public int getUpdateType() {
		return updateType;
	}
	
	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}
	
	public void setUpdateTypeEnum(DetailDataVersionUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}
	
	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public DetailDataVersionUpdateType getUpdateTypeEnum() {
		return DetailDataVersionUpdateType.valueOf(this.updateType);
	}

	public AddBeauticianInfoData getAddBeauticianInfoData() {
		return addBeauticianInfoData;
	}

	public void setAddBeauticianInfoData(AddBeauticianInfoData addBeauticianInfoData) {
		this.addBeauticianInfoData = addBeauticianInfoData;
	}

	public UpdateBeauticianInfoData getUpdateBeauticianInfoData() {
		return updateBeauticianInfoData;
	}

	public void setUpdateBeauticianInfoData(UpdateBeauticianInfoData updateBeauticianInfoData) {
		this.updateBeauticianInfoData = updateBeauticianInfoData;
	}

	public RemoveBeauticianInfoData getRemoveBeauticianInfoData() {
		return removeBeauticianInfoData;
	}

	public void setRemoveBeauticianInfoData(RemoveBeauticianInfoData removeBeauticianInfoData) {
		this.removeBeauticianInfoData = removeBeauticianInfoData;
	}

	
}
