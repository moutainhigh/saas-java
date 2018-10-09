package com.hq.taskMS.common.log;

public enum LogModule {
	Tmp("临时"),
	StartInfo("启动信息"),
	GenExperienceData("生成演示数据"),
	BirthdayNotice("定期生成生日提醒消息"),
	;
	
	private String name;
	
	private LogModule(String nameP){
		this.name = nameP;
	}

	public String getName() {
		return name;
	}
}
