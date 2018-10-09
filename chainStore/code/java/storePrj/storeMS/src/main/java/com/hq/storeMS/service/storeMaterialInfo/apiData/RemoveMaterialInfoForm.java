package com.hq.storeMS.service.storeMaterialInfo.apiData;

public class RemoveMaterialInfoForm {

	private String id;
	
	public static RemoveMaterialInfoForm newInstance(){
		return new RemoveMaterialInfoForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
