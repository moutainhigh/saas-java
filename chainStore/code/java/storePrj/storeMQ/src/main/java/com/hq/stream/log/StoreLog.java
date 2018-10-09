package com.hq.stream.log;

/**
 * ClassName: AppLog <br/>
 * Function: TODO 日志传输对象. <br/>
 * 
 * @author kevin
 * @version
 * @since JDK 1.6
 */
public class StoreLog {
	// event 唯一标识
	private String tid;
	// 日志等级 LogLevelEnum
	private int logLevel;
	// 日志来源 LogFromEnum
	private int logFrom;
	// 日志内容 LogData
	private String jsonData;
	
	public LogLevelEnum getLogLevelEnum(){
		return LogLevelEnum.valueOf(this.logLevel);
	}
	
	public LogFromEnum getLogFromEnum(){
		return LogFromEnum.valueOf(this.logFrom);
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public int getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	public int getLogFrom() {
		return logFrom;
	}

	public void setLogFrom(int logFrom) {
		this.logFrom = logFrom;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
}
