package com.hq.storeManagerMS.service.common;

import com.hq.storeManagerMS.common.log.LogModule;

public class ExceptionInfo {

	
	private LogModule logModule;
	private String logId;
	private String reason; 
	private ReqResult<?> result;
	
	public static ExceptionInfo newInstance(){
		ExceptionInfo target = new ExceptionInfo();
		return target;
	}
	
	public ExceptionInfo withLogModule(LogModule logModuleP){
		this.logModule = logModuleP;
		return this;
	}
	public ExceptionInfo withLogId(String logIdP){
		this.logId = logIdP;
		return this;
	}
	public ExceptionInfo withReason(String reasonP){
		this.reason = reasonP;
		return this;
	}
	public ExceptionInfo withResult(ReqResult<?> resultP){
		this.result = resultP;
		return this;
	}

	public LogModule getLogModule() {
		return logModule;
	}

	public String getLogId() {
		return logId;
	}

	public String getReason() {
		return reason;
	}

	public ReqResult<?> getResult() {
		return result;
	}

	
	
}
