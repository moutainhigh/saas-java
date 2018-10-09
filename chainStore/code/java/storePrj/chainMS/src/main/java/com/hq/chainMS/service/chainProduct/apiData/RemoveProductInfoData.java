package com.hq.chainMS.service.chainProduct.apiData;

public class RemoveProductInfoData {
	private String id;
	
	public static RemoveProductInfoData newInstance(){
		return new RemoveProductInfoData();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
