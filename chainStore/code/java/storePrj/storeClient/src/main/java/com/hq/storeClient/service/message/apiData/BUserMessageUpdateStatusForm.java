package com.hq.storeClient.service.message.apiData;

public class BUserMessageUpdateStatusForm {
	private long messageId;
	private int status;

	public static BUserMessageUpdateStatusForm newInstance() {
		return new BUserMessageUpdateStatusForm();
	}
	
	public static BUserMessageUpdateStatusForm newInstance(long messageIdP, int statusP) {
		BUserMessageUpdateStatusForm data = newInstance();
		data.messageId = messageIdP;
		data.status = statusP;
		return data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

}
