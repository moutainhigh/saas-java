package com.hq.storeMS.service.dynamic.apiData;

import com.hq.storeMS.service.dynamic.data.Dynamic;

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
