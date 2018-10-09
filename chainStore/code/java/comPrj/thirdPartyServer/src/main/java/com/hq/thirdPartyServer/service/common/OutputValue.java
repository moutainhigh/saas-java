package com.hq.thirdPartyServer.service.common;


public class OutputValue {
	private int dataType;
	private String dataValue;

	public static OutputValue newInstance() {
		return new OutputValue();
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
