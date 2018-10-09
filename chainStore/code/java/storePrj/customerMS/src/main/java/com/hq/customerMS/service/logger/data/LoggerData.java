package com.hq.customerMS.service.logger.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LoggerData {
	private String moduleName;// 模块 如：会员管理/项目管理/医美师管理等
	private String id;// 具体的方法 如：AppointmentHandler[findAppointmentList] 类[方法]
	private String content;// 提示内容
	private String throwable;// 异常的堆栈内容

	public static LoggerData newInstance() {
		return new LoggerData();
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

	@Override
	public String toString() {
		return "LoggerAddApiForm [moduleName=" + moduleName + ", id=" + id
				+ ", content=" + content + ", throwable=" + throwable + "]";
	}

}
