package com.hq.storeManagerMS.common.log;

public enum LogModule {
	Tmp("临时"),
	StartInfo("初始化信息"),
	MongoDao("mongo数据库操作"),
	RedisDao("redis数据库操作"),
	MUser("用户管理"),
	MUserAdminRole("角色管理"),
	Img("图片管理"),
	ServerConfig("系统参数配置"),
	Sms("短信"),
	MngDevice("仪器管理"),
	BUser("账号管理"),
	VipLevel("会员等级"),
	AreaCode("区号"),
	BuserRole("商户的会员信息"),
	StoreMenu("智美通菜单"),
	Charge("收费管理"),
	VipLevelType("会员等级分类"),
	;
	
	private String name;
	
	private LogModule(String nameP){
		this.name = nameP;
	}

	public String getName() {
		return name;
	}
	
}
