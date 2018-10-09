package com.hq.thirdPartyServer.service.common;

public class Configure {
	private int dataType;
	private String dataValue;

	public static Configure newInstance(String side) {
		Configure data = new Configure();
		data.dataType = 50;
		data.dataValue = "{\"side\":\""+side+"\"}";
		return data;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
}
