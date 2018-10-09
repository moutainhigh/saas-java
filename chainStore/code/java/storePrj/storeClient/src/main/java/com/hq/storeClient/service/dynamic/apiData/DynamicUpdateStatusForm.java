package com.hq.storeClient.service.dynamic.apiData;

import com.hq.storeClient.service.dynamic.data.Dynamic;

public class DynamicUpdateStatusForm {
	private int status;

	public static DynamicUpdateStatusForm newInstance() {
		return new DynamicUpdateStatusForm();
	}
	
	public void updateDynamic(Dynamic data) {
		data.setStatus(status);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
