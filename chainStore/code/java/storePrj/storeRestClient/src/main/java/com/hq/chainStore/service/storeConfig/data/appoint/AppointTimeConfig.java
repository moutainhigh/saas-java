package com.hq.chainStore.service.storeConfig.data.appoint;

/**
 * 预约时间段设置
 *
 */
public class AppointTimeConfig {
	// 如： 07:00 - 23:00
	private String startTime;
	private String endTime;

	public static AppointTimeConfig newInstance() {
		return new AppointTimeConfig();
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
