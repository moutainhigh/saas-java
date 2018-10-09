package com.hq.chainStore.service.storeIncomePay.apiData;


public class IncomePayTypeUpdateForm {
	private String id;
	private String name;

	public static IncomePayTypeUpdateForm newInstance() {
		return new IncomePayTypeUpdateForm();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
