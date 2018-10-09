package com.hq.chainStore.service.message.data;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "buserMessage")
public class BUserMessage {
	@Id
	private long id;
	private long buserId;
	// 店铺ID
	private long storeId;
	// 店铺名称
	private String storeName;
	// 预约Id、店务流程ID、申请员工Id等
	private String messageObjId;
	// 类型 TriggerTypeEnum
	private int messageType;
	// 消息内容
	private String messageBody;
	// 是否已读 消息的状态 MessageStatusEnum
	private int status;

	private long createdTime;
	private long lastUpdateTime;
	private int ver;

	public static BUserMessage newInstance() {
		return new BUserMessage();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getMessageObjId() {
		return messageObjId;
	}

	public void setMessageObjId(String messageObjId) {
		this.messageObjId = messageObjId;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

}
