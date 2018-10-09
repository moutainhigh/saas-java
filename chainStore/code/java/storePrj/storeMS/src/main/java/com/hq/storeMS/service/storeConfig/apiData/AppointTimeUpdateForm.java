package com.hq.storeMS.service.storeConfig.apiData;

import com.hq.storeMS.service.storeConfig.data.appoint.AppointTimeConfig;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class AppointTimeUpdateForm {
	// 如： 09:00 - 23:00
	private String startTime;
	private String endTime;

	public static AppointTimeUpdateForm newInstance() {
		return new AppointTimeUpdateForm();
	}
	
	public void updateAppointTime(AppointTimeConfig data) {
		FastBeanCopyer.getInstance().copy(this, data);
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
