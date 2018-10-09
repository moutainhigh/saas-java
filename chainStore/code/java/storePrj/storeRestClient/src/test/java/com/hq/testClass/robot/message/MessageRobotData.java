package com.hq.testClass.robot.message;


public class MessageRobotData {
	// 随机数标记，用来生成电话号码等信息
	private int mark;

	private long buserId;
	private long storeId;

	public static MessageRobotData newInstance(int mark) {
		MessageRobotData data = new MessageRobotData();
		data.mark = mark;
		return data;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
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

	@Override
	public String toString() {
		return "MessageRobotData [mark=" + mark + ", buserId=" + buserId
				+ ", storeId=" + storeId + "]";
	}

}
