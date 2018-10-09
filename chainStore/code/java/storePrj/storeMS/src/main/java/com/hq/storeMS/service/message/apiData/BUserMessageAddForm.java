package com.hq.storeMS.service.message.apiData;

import com.hq.storeMS.service.message.data.BUserMessage;
import com.zenmind.common.beanCopy.FastBeanCopyer;

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
	
	public BUserMessage toBUserMessage() {
		BUserMessage data = BUserMessage.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
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

}
