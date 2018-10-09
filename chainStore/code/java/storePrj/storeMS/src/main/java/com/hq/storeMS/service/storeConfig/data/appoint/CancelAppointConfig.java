package com.hq.storeMS.service.storeConfig.data.appoint;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 取消预约 原因设置
 *
 */
@SynClass
public class CancelAppointConfig {
	private int id;
	// 取消预约原因
	private String reason;

	public static CancelAppointConfig newInstance() {
		CancelAppointConfig data = new CancelAppointConfig();
		return data;
	}
	
	public static CancelAppointConfig newInstance(SysInitReasonEnum sysInitReasonEnum) {
		CancelAppointConfig data = newInstance();
		data.id=sysInitReasonEnum.getIndex();
		data.reason=sysInitReasonEnum.getReason();
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
