package com.hq.orderMS.common.log;

public enum LogModule {
	Tmp("临时"),
	StartInfo("启动信息"),
	MongoDao("mongo数据库操作"),
	RedisDao("redis数据库操作"),
	Order("订单"),
	OrderTmpRcd("订单临时记录"),
	;
	
	private String name;
	
	private LogModule(String nameP){
		this.name = nameP;
	}

	public String getName() {
		return name;
	}
	
}
