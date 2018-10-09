package com.hq.common.restClientLog;

public enum LogModule {
	Tmp("临时"),
	StartInfo("初始化信息"),
	Store("店铺"),
	User("用户"),
	Order("订单"),
	Employ("雇员"),
	BUser("b端用户"), 
	StoreClerkInfo("加盟店店员信息"),
	Opuser("saas管理员"),
	Saas("saas超级管理员"),
	StorePromotion("店铺推广"),
	;
	
	private String name;
	
	private LogModule(String nameP){
		this.name = nameP;
	}

	public String getName() {
		return name;
	}
	
}
