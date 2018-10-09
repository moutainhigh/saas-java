package com.hq.chainStore.service.workFlowData.apiData;

public class DelimitCardRecordUpdateForm {
	private String delimitCardId;
	// 抵消次数
	private int count;

	public static DelimitCardRecordUpdateForm newInstance() {
		return new DelimitCardRecordUpdateForm();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDelimitCardId() {
		return delimitCardId;
	}

	public void setDelimitCardId(String delimitCardId) {
		this.delimitCardId = delimitCardId;
	}
}
