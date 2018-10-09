package com.hq.storeClient.service.storeConfig.apiData;

public class AppointTimeUpdateForm {
	// 如： 09:00 - 23:00
	private String startTime;
	private String endTime;

	public static AppointTimeUpdateForm newInstance() {
		return new AppointTimeUpdateForm();
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
