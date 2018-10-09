package com.hq.stream.event.data;

public class EventContent {
	private String remark;

	public static EventContent newInstance() {
		EventContent data = new EventContent();
		return data;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
