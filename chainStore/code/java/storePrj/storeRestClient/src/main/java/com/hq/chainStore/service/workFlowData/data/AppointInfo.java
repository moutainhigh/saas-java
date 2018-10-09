package com.hq.chainStore.service.workFlowData.data;

public class AppointInfo {
	private long appointId;

	public static AppointInfo newInstance(long appointIdP) {
		AppointInfo data = new AppointInfo();
		data.appointId = appointIdP;
		return data;
	}

	public long getAppointId() {
		return appointId;
	}

	public void setAppointId(long appointId) {
		this.appointId = appointId;
	}
}
