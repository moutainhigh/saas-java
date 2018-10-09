package com.hq.storeClient.service.storeCardInfo.apiData;

/**
 * 修改次卡状态
 */
public class UpdProductCardStateData {

	private String id;

	private long storeId;

	private int state;

	public static UpdProductCardStateData newInstance() {
		return new UpdProductCardStateData();
	}

	public static UpdProductCardStateData newInstance(String idP, long storeIdP, int stateP) {
		UpdProductCardStateData data = new UpdProductCardStateData();
		data.id = idP;
		data.storeId = storeIdP;
		data.state = stateP;
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
