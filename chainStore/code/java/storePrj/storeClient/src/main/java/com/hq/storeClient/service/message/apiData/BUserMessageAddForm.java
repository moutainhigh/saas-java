package com.hq.storeClient.service.message.apiData;

public class BUserMessageAddForm {
	private long buserId;
	// 店铺ID
	private long storeId;
	// 预约Id、店务流程ID、申请员工Id等
	private String messageObjId;
	// 类型 TriggerTypeEnum
	private int messageType;
	// 消息内容
	private String messageBody;

	public static BUserMessageAddForm newInstance() {
		BUserMessageAddForm data = new BUserMessageAddForm();
		return data;
	}

	public long getBuserId() {
		return buserId;
	}

	public BUserMessageAddForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public BUserMessageAddForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public String getMessageObjId() {
		return messageObjId;
	}

	public BUserMessageAddForm setMessageObjId(String messageObjId) {
		this.messageObjId = messageObjId;
		return this;
	}

	public int getMessageType() {
		return messageType;
	}

	public BUserMessageAddForm setMessageType(int messageType) {
		this.messageType = messageType;
		return this;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public BUserMessageAddForm setMessageBody(String messageBody) {
		this.messageBody = messageBody;
		return this;
	}

}
