package com.hq.storeMS.service.saas.apiData;

public class OPStoreUpdateStateApiData {
	
	private long id;
	
	private boolean approved;
	
	public static OPStoreUpdateStateApiData newInstance(){
		return new OPStoreUpdateStateApiData();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	
	
}
