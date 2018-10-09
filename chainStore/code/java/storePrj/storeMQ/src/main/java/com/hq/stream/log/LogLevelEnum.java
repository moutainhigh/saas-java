package com.hq.stream.log;

/**
 * ClassName: LogFormEnum <br/>
 * Function: TODO 应用的日志枚举类. <br/>
 * 
 * @author kevin
 * @version
 * @since JDK 1.6
 */
public enum LogLevelEnum {
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
