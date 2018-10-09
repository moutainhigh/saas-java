package com.hq.storeClient.service.storeConfig.data.appoint;

/**
 * 取消预约 原因设置
 *
 */
public class CancelAppointConfig {
	private int id;
	// 取消预约原因
	private String reason;

	public static CancelAppointConfig newInstance() {
		return new CancelAppointConfig();
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
