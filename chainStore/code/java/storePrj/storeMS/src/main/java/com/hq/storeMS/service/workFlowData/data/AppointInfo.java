package com.hq.storeMS.service.workFlowData.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
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
