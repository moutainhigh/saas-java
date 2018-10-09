package com.hq.chainStore.service.logger.data;

public enum LogLevelEnum {
	UNKNOW("未知"),
	INFO("消息"), 
	WARN("警告"), 
	ERROR("错误"),

	;

	private String remark;

	private LogLevelEnum(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public static LogLevelEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}