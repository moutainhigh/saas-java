package com.hq.testClass.robot.logger;

import org.apache.commons.lang3.RandomUtils;


public class LoggerRobotData {
	// 随机数标记，用来生成电话号码等信息
	private int mark;

	private int logLevel; // 日志等级 如：info/error
	private String moduleName;// 模块 如：会员管理/项目管理/医美师管理等
	private String id;// 具体的方法 如：AppointmentHandler[findAppointmentList] 类[方法]
	private String content;// 提示内容
	private String throwable;// 异常的堆栈内容

	public static LoggerRobotData newInstance(int mark) {
		LoggerRobotData data = new LoggerRobotData();
		data.mark = mark;
		data.moduleName = "moduleName-" + mark;
		data.id = "id-" + mark;
		data.content = "content-" + mark;
		data.throwable = "throwable-" + mark;
		data.logLevel = RandomUtils.nextInt(0, 3);
		return data;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getThrowable() {
		return throwable;
	}

	public void setThrowable(String throwable) {
		this.throwable = throwable;
	}


}
