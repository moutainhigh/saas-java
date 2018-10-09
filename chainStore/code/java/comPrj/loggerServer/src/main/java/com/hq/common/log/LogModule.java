package com.hq.common.log;

public enum LogModule {
	Tmp("临时"),
	StartInfo("启动信息"),
	;
	
	private String name;
	
	private LogModule(String nameP){
		this.name = nameP;
	}

	public String getName() {
		return name;
	}
	
}
