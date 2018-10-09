package com.hq.storeMS.service.storeMaterialInfo.apiData;

public class RemoveMaterialInventoryForm {

	private String id;
	
	private int count;
	
	public static RemoveMaterialInventoryForm newInstance(){
		return new RemoveMaterialInventoryForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
