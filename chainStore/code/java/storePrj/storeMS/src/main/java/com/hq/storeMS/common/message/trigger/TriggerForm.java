package com.hq.storeMS.common.message.trigger;

import com.hq.storeMS.service.message.data.BUserMessage;

public class TriggerForm {
	//TriggerTypeEnum
	private int triggerType;
	private String storeName;
	private String id;//预约Id、店务ID、员工申请ID
	
	private long buserId;
	private long storeId;

	public static TriggerForm newInstance(long storeId, long buserId) {
		TriggerForm queryForm = new TriggerForm();
		queryForm.storeId = storeId;
		queryForm.buserId = buserId;
		return queryForm;
	}

	public BUserMessage toBUserMessage() {
		BUserMessage data = BUserMessage.newInstance();
		data.setBuserId(buserId);
		data.setStoreId(storeId);
		data.setMessageObjId(id);
		data.setMessageType(triggerType);
		return data;
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

	public int getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(int triggerType) {
		this.triggerType = triggerType;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
