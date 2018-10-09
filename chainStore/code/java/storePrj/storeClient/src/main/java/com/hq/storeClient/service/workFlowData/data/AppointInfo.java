package com.hq.storeClient.service.workFlowData.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class AppointInfo {
	private long appointId;

	public static AppointInfo newInstance() {
		AppointInfo data = new AppointInfo();
		return data;
	}

	public long getAppointId() {
		return appointId;
	}

	public void setAppointId(long appointId) {
		this.appointId = appointId;
	}
}
