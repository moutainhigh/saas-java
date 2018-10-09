package com.hq.chainMS.service.chainProduct.apiData;

public class RemoveProductTypeData {
	private String id;
	
	public static RemoveProductTypeData newInstance(){
		return new RemoveProductTypeData();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
