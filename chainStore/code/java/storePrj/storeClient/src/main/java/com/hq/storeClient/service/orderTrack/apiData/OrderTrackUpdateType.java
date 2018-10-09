package com.hq.storeClient.service.orderTrack.apiData;

public enum OrderTrackUpdateType {
	UpdateStatus("更新订单的物流状态"),
	;
	
	private String descript;
	
	private OrderTrackUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public static OrderTrackUpdateType valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	
}
