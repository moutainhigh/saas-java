package com.hq.storeMS.common.message.info;

public class MsgQueryForm {
	private long storeId;
	private long buserId;

	public static MsgQueryForm newInstance(long storeId, long buserId) {
		MsgQueryForm queryForm = new MsgQueryForm();
		queryForm.storeId = storeId;
		queryForm.buserId = buserId;
		return queryForm;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}
}
