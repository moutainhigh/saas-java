package com.hq.storeMS.common.message.info;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class MessageResp {

	private int messageType;
	private long count;

	public static MessageResp newInstance() {
		return new MessageResp();
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

}
