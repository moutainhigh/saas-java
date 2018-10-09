package com.hq.thirdPartyServer.service.common;


public class Image {
	private String dataValue;
	private int dataType;

	public static Image newInstance(String dataValue) {
		Image data = new Image();
		data.dataType = 50;
		data.dataValue = dataValue;
		return data;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
}
