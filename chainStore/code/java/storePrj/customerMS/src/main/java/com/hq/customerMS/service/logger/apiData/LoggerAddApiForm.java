package com.hq.customerMS.service.logger.apiData;

import com.hq.customerMS.service.logger.data.LoggerData;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class LoggerAddApiForm {
	private int logLevel; // 日志等级 如：info/error
	private String moduleName;// 模块 如：会员管理/项目管理/医美师管理等
	private String id;// 具体的方法 如：AppointmentHandler[findAppointmentList] 类[方法]
	private String content;// 提示内容
	private String throwable;// 异常的堆栈内容

	public static LoggerAddApiForm newInstance() {
		return new LoggerAddApiForm();
	}
	
	public LoggerData toLoggerData(){
		LoggerData loggerData = LoggerData.newInstance();
		FastBeanCopyer.getInstance().copy(this, loggerData);
		return loggerData;
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

	public int getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	@Override
	public String toString() {
		return "LoggerAddApiForm [logLevel=" + logLevel + ", moduleName="
				+ moduleName + ", id=" + id + ", content=" + content
				+ ", throwable=" + throwable + "]";
	}

}
