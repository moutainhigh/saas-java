package com.hq.storeMS.service.storeConfig.data.appoint;

import com.hq.storeMS.service.storeConfig.data.leaguer.SysInitAppointTimeEnum;
import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 预约时间段设置
 *
 */
@SynClass
public class AppointTimeConfig {
	// 如： 07:00 - 23:00
	private String startTime;
	private String endTime;

	public static AppointTimeConfig newInstance() {
		AppointTimeConfig data = new AppointTimeConfig();
		return data;
	}
	
	public static AppointTimeConfig newInstance(SysInitAppointTimeEnum appointTimeEnum) {
		AppointTimeConfig data = newInstance();
		data.startTime = appointTimeEnum.getStartTime();
		data.endTime = appointTimeEnum.getEndTime();
		return data;
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
