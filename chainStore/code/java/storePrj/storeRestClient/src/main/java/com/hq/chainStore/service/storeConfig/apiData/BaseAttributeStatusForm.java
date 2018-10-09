package com.hq.chainStore.service.storeConfig.apiData;

public class BaseAttributeStatusForm {
	// 属性名 英文
	private String attributeName;
	// 是否启用 对应LeaguerAttributeStateEnum
	private int status;
	// 是否必填 对应RequiredEnum
	private int require;

	public static BaseAttributeStatusForm newInstance() {
		return new BaseAttributeStatusForm();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public int getRequire() {
		return require;
	}

	public void setRequire(int require) {
		this.require = require;
	}
}
