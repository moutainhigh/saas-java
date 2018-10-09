package com.hq.storeMS.service.storeConfig.apiData;

import com.hq.storeMS.service.storeConfig.data.appoint.CancelAppointConfig;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class CancelAppointAddForm {
	private int id;
	// 取消预约原因
	private String reason;

	public static CancelAppointAddForm newInstance() {
		return new CancelAppointAddForm();
	}
	
	public CancelAppointConfig toCancelAppointConfig() {
		CancelAppointConfig data = CancelAppointConfig.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
