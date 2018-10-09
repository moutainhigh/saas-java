package com.hq.stream.log.data;

/**
 * ClassName: LogData <br/>
 * Function: TODO 日志内容. <br/>
 * 
 * @author kevin
 * @version
 * @since JDK 1.6
 */
public class LogData {
	private String moduleName;
	private String id;
	private String content;
	private String throwable;

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
