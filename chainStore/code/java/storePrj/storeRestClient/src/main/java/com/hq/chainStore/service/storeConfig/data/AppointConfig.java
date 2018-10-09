package com.hq.chainStore.service.storeConfig.data;

import java.util.HashMap;
import java.util.Map;

import com.hq.chainStore.service.storeConfig.data.appoint.AppointTimeConfig;
import com.hq.chainStore.service.storeConfig.data.appoint.CancelAppointConfig;

public class AppointConfig {
	// 预约时间段
	private AppointTimeConfig appointTimeConfig;
	// 取消预约的原因
	private int cancelAppointIndex = 0;
	private Map<Integer, CancelAppointConfig> cancelAppointConfigMap = new HashMap<Integer, CancelAppointConfig>();

	public static AppointConfig newInstance() {
		return new AppointConfig();
	}

	public AppointTimeConfig getAppointTimeConfig() {
		return appointTimeConfig;
	}

	public void setAppointTimeConfig(AppointTimeConfig appointTimeConfig) {
		this.appointTimeConfig = appointTimeConfig;
	}

	public int getCancelAppointIndex() {
		return cancelAppointIndex;
	}

	public void setCancelAppointIndex(int cancelAppointIndex) {
		this.cancelAppointIndex = cancelAppointIndex;
	}

	public Map<Integer, CancelAppointConfig> getCancelAppointConfigMap() {
		return cancelAppointConfigMap;
	}

	public void setCancelAppointConfigMap(Map<Integer, CancelAppointConfig> cancelAppointConfigMap) {
		this.cancelAppointConfigMap = cancelAppointConfigMap;
	}

}
