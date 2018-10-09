package com.hq.storeMS.service.storeIncomePay.apiData;

public class IncomePayTypeRemoveForm {

	private String incomePayTypeId;

	public static IncomePayTypeRemoveForm newInstance() {
		return new IncomePayTypeRemoveForm();
	}

	public String getIncomePayTypeId() {
		return incomePayTypeId;
	}

	public void setIncomePayTypeId(String incomePayTypeId) {
		this.incomePayTypeId = incomePayTypeId;
	}
}
