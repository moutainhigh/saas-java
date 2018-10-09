package com.hq.storeClient.service.storeClerkInfo.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ApplyClerkInfo {
	private long buserId;
	private long storeId;
	private int state;
	private long createTime;

	public static ApplyClerkInfo newInstance() {
		ApplyClerkInfo info = new ApplyClerkInfo();
		return info;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
