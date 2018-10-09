package com.hq.storeMS.service.storeConfig.apiData;

import com.hq.storeMS.service.storeConfig.data.appoint.CancelAppointConfig;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class CancelAppointUpdateForm {
	private int id;
	// 取消预约原因
	private String reason;

	public static CancelAppointUpdateForm newInstance() {
		return new CancelAppointUpdateForm();
	}
	
	public void updateCancelAppointConfig(CancelAppointConfig data) {
		FastBeanCopyer.getInstance().copy(this, data);
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
