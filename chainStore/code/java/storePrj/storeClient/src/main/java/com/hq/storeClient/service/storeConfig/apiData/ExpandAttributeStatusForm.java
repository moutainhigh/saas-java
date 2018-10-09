package com.hq.storeClient.service.storeConfig.apiData;

public class ExpandAttributeStatusForm {
	// 属性名 英文
	private int id;
	// 是否启用 对应LeaguerAttributeStateEnum
	private int status;
	// 是否必填 对应RequiredEnum
	private int require;

	public static ExpandAttributeStatusForm newInstance() {
		return new ExpandAttributeStatusForm();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRequire() {
		return require;
	}

	public void setRequire(int require) {
		this.require = require;
	}
}
