package com.hq.storeClient.service.storeChain.apiData;

import java.util.List;

public class StoreChainUpdateApiForm {
	//对应 StoreChainUpdateType
	private int updateType;
	
	private List<StoreChainUpdateStatusForm> storeChainUpdateStatusForms; 

	public static StoreChainUpdateApiForm newInstance() {
		return new StoreChainUpdateApiForm();
	}

	public StoreChainUpdateType getStoreChainUpdateType() {
		return StoreChainUpdateType.valueOf(updateType);
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public List<StoreChainUpdateStatusForm> getStoreChainUpdateStatusForms() {
		return storeChainUpdateStatusForms;
	}

	public void setStoreChainUpdateStatusForms(List<StoreChainUpdateStatusForm> storeChainUpdateStatusForms) {
		this.storeChainUpdateStatusForms = storeChainUpdateStatusForms;
	}

}
