package com.hq.chainStore.service.saas.apiData;

public class OPStoreUpdateApiForm {
	
	private int id;
	
	private int updateType;
	
	private OPStoreUpdateStateApiData updateData;
	

	public static OPStoreUpdateApiForm newInstance(){
		return new OPStoreUpdateApiForm();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUpdateType() {
		return updateType;
	}
	
	public OPStoreUpdateType getUpdateTypeEnum() {
		return OPStoreUpdateType.valueOf(this.updateType);
	}


	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}
	
	public void setUpdateTypeEnum(OPStoreUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}


	public OPStoreUpdateStateApiData getUpdateData() {
		return updateData;
	}


	public void setUpdateData(OPStoreUpdateStateApiData updateData) {
		this.updateData = updateData;
	}


	
}
