package com.hq.common.restClientLog;

public interface IntfLoger {
	
	public void info(LogModule logModule, String id, String info); 
	public void error(LogModule logModule, String id, String reason) ;
	public void error(LogModule logModule, String id, String reason, Throwable throwable);

}