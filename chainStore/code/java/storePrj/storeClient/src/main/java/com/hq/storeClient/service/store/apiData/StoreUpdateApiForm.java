package com.hq.storeClient.service.store.apiData;

public class StoreUpdateApiForm {
	private int updateType;
	
	private StoreUpdateChainData updateChainData;

	public static StoreUpdateApiForm newInstance() {
		return new StoreUpdateApiForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public StoreUpdateChainData getUpdateChainData() {
		return updateChainData;
	}

	public void setUpdateChainData(StoreUpdateChainData updateChainData) {
		this.updateChainData = updateChainData;
	}

}
