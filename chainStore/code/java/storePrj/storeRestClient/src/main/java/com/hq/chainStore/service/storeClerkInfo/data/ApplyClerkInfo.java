package com.hq.chainStore.service.storeClerkInfo.data;

public class ApplyClerkInfo {
	
	private long buserId;
	
	private long storeId;

	private int state;
	
	private long createTime;
	
	public static ApplyClerkInfo newInstance(long buserIdP,long storeIdP){
		ApplyClerkInfo info = new ApplyClerkInfo();
		info.buserId = buserIdP;
		info.storeId = storeIdP;
		info.createTime = System.currentTimeMillis();
		info.state = ApplyState.Pending.ordinal();
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
	

	public ApplyState getStateEnum() {
		return ApplyState.valueOf(this.state);
	}

	public void setStateEnum(ApplyState state) {
		this.state = state.ordinal();
	}
	
	
	
	
}
