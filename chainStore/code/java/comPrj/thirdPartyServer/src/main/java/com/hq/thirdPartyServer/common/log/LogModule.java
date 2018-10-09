package com.hq.thirdPartyServer.common.log;

public enum LogModule {
	Tmp("临时"),
	StartInfo("启动信息"),
	SmsServer("发短信服务"),
	IdCard("身份证识别"),
	BusinessCard("名片识别"),
	
	;
	
	private String name;
	
	private LogModule(String nameP){
		this.name = nameP;
	}

	public String getName() {
		return name;
	}
	
}
