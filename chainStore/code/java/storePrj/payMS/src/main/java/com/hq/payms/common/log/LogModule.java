package com.hq.payms.common.log;

public enum LogModule {
	Tmp("临时"),
	StartInfo("启动信息"),
	MongoDao("MongoDao"), 
	RedisDao("RedisDao"),
	Wxpay("微信支付"),
	Alipay("支付宝支付"),
	BossPayInfo("BossPayInfo"),
	Img("图片"),
	ThreadTracer("线程性能跟踪"),
	;
	
	private String name;
	
	private LogModule(String nameP){
		this.name = nameP;
	}

	public String getName() {
		return name;
	}
}
