package com.hq.chainStore.service.message.data;

import javax.persistence.Table;

@Table(name = "message")
public class Message {

	private int messageType;
	private long count;

	public static Message newInstance() {
		return new Message();
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Message [messageType=" + messageType + ", count=" + count + "]";
	}

}
