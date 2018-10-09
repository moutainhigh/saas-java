package com.hq.stream.event;

public class StoreEvent {
	// event 唯一标识
	private String tid;
	// 所属店铺id
	private long storeId;
	//事件类型 StoreEventType
	private int eventType;
	//事件内容 EventContent
	private String jsonData;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getEventType() {
		return eventType;
	}

	public StoreEventType getEventTypeEnum() {
		if(eventType < StoreEventType.values().length) {
			return StoreEventType.valueOf(eventType);
		}
		return StoreEventType.UnKnown;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

}
